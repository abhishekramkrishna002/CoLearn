package com.quotesapp.services;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.JobIntentService;
import android.util.Log;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.quotesapp.Constants;
import com.quotesapp.MainApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import io.realm.Sort;

public class Book extends JobIntentService {

    static final int JOB_ID = 2000;

    public static void enqueueWork(Context context, Intent work) {
        enqueueWork(context, Book.class, JOB_ID, work);
    }

    @Override
    protected void onHandleWork(@NonNull Intent intent) {
        String categoryId = intent.getStringExtra(Constants.IntentFields.CATEGORY_ID);
        Long internalId = intent.getLongExtra(Constants.IntentFields.INTERNAL_ID, -1);

        // fetch from db
        Realm realm = Realm.getDefaultInstance();
        RealmQuery<com.quotesapp.domains.Book> realmQuery = realm.where(com.quotesapp.domains.Book.class)
                .equalTo(com.quotesapp.dto.Book.FIELDS.categoryId, categoryId);
        realmQuery = internalId != -1 ? realmQuery.greaterThan(com.quotesapp.dto.Book.FIELDS.internalId, internalId) : realmQuery;
        realmQuery = realmQuery.sort(com.quotesapp.dto.Book.FIELDS.internalId, Sort.ASCENDING)
                        .limit(Constants.PAGINATION.PAGE_SIZE);
        RealmResults<com.quotesapp.domains.Book> booksResults = realmQuery.findAll();
        List<com.quotesapp.dto.Book> dbBooks= new ArrayList<>();
        booksResults.listIterator().forEachRemaining(book -> dbBooks.add(com.quotesapp.dto.Book.getBook(book)));

        if(dbBooks.size() > 0){
            // notify component
            notify(dbBooks);

            return;
        }

        Firebase.getBooks(internalId, categoryId, (snapshot, e) -> {

            if(snapshot.isEmpty() || snapshot.size() == 0)
                return;

            // retrieve records from query snapshot & transform to DTO
            List<com.quotesapp.dto.Book> books = snapshot.getDocuments()
                    .stream()
                    .map(documentSnapshot -> com.quotesapp.dto.Book.getBook(documentSnapshot.getId(), documentSnapshot.getData()))
                    .collect(Collectors.toList());

            // notify component
            notify(books);

            // write to db
            writeToDB(books);

        });
    }

    public void writeToDB(List<com.quotesapp.dto.Book> books){
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        books.forEach(bookDTO -> com.quotesapp.domains.Book.fromDTO(realm, bookDTO));
        realm.commitTransaction();
    }

    public void notify(List<com.quotesapp.dto.Book> books){
        WritableArray payload = Arguments.createArray();
        List<WritableMap> bookMaps = books.stream()
                .map(book -> book.toWritableMap()).collect(Collectors.toList());
        bookMaps.stream().forEach(bookMap -> payload.pushMap(bookMap));


        // notify component
        ((MainApplication) getApplicationContext())
                .getReactNativeHost()
                .getReactInstanceManager()
                .getCurrentReactContext()
                .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                .emit(Constants.Events.RECEIVED_BOOKS, payload);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
