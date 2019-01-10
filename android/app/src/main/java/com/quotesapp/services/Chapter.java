package com.quotesapp.services;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.JobIntentService;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.quotesapp.Constants;
import com.quotesapp.MainApplication;
import com.quotesapp.domains.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import io.realm.Sort;

public class Chapter extends JobIntentService {

    static final int JOB_ID = 2000;

    public static void enqueueWork(Context context, Intent work) {
        enqueueWork(context, Chapter.class, JOB_ID, work);
    }

    @Override
    protected void onHandleWork(@NonNull Intent intent) {
        String bookId = intent.getStringExtra(Constants.IntentFields.BOOK_ID);
        Long internalId = intent.getLongExtra(Constants.IntentFields.INTERNAL_ID, -1);

        // fetch from db
        Realm realm = Realm.getDefaultInstance();
        RealmQuery<com.quotesapp.domains.Chapter> realmQuery = realm.where(com.quotesapp.domains.Chapter.class)
                .equalTo(com.quotesapp.dto.Chapter.FIELDS.bookId, bookId);
        realmQuery = internalId != -1 ? realmQuery.greaterThan(com.quotesapp.dto.Chapter.FIELDS.chapterOrderId, internalId) : realmQuery;
        realmQuery = realmQuery.sort(com.quotesapp.dto.Chapter.FIELDS.chapterOrderId, Sort.ASCENDING)
                .limit(Constants.PAGINATION.PAGE_SIZE);
        RealmResults<com.quotesapp.domains.Chapter> chapterResults = realmQuery.findAll();
        List<com.quotesapp.dto.Chapter> dbChapters= new ArrayList<>();
        chapterResults.listIterator().forEachRemaining(chapter -> dbChapters.add(com.quotesapp.dto.Chapter.getChapter(chapter)));

        if(dbChapters.size() > 0){
            notify(dbChapters);

            return;
        }

        Firebase.getChapters(internalId, bookId, (snapshot, e) -> {

            List<com.quotesapp.dto.Chapter> chapters = snapshot.getDocuments()
                    .stream()
                    .map(documentSnapshot -> com.quotesapp.dto.Chapter.getChapter(documentSnapshot.getId(), bookId, documentSnapshot.getData()))
                    .collect(Collectors.toList());

            notify(chapters);

            // write to db
            writeToDB(chapters);

        });
    }

    public void writeToDB(List<com.quotesapp.dto.Chapter> chapters){
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        chapters.forEach(dto -> com.quotesapp.domains.Chapter.fromDTO(realm, dto));
        realm.commitTransaction();
    }

    public void notify(List<com.quotesapp.dto.Chapter> chapters){

        WritableArray payload = Arguments.createArray();
        List<WritableMap> chapterMaps = chapters.stream()
                .map(chapter -> chapter.toWritableMap()).collect(Collectors.toList());
        chapterMaps.stream().forEach(bookMap -> payload.pushMap(bookMap));

        ((MainApplication) getApplicationContext())
                .getReactNativeHost()
                .getReactInstanceManager()
                .getCurrentReactContext()
                .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                .emit(Constants.Events.RECEIVED_CHAPTERS, payload);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
