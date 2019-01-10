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
import com.quotesapp.domains.Chapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import io.realm.Sort;

public class Quote extends JobIntentService {

    static final int JOB_ID = 2000;

    public static void enqueueWork(Context context, Intent work) {
        enqueueWork(context, Quote.class, JOB_ID, work);
    }

    @Override
    protected void onHandleWork(@NonNull Intent intent) {
        String bookId = intent.getStringExtra(Constants.IntentFields.BOOK_ID);
        String chapterId = intent.getStringExtra(Constants.IntentFields.CHAPTER_ID);
        Long internalId = intent.getLongExtra(Constants.IntentFields.INTERNAL_ID, -1);


        // fetch from db
        Realm realm = Realm.getDefaultInstance();
        RealmQuery<com.quotesapp.domains.Quote> realmQuery = realm
                .where(com.quotesapp.domains.Quote.class)
                .equalTo(com.quotesapp.dto.Quote.FIELDS.bookId, bookId)
                .equalTo(com.quotesapp.dto.Quote.FIELDS.chapterId, chapterId);
        ;
        realmQuery = internalId != -1 ? realmQuery.greaterThan(com.quotesapp.dto.Quote.FIELDS.quoteOrderId, internalId) : realmQuery;
        realmQuery = realmQuery.sort(com.quotesapp.dto.Quote.FIELDS.quoteOrderId, Sort.ASCENDING)
                .limit(Constants.PAGINATION.PAGE_SIZE);
        RealmResults<com.quotesapp.domains.Quote> quoteResults = realmQuery.findAll();
        List<com.quotesapp.dto.Quote> dbQuotes = new ArrayList<>();
        quoteResults.listIterator().forEachRemaining(quote -> dbQuotes.add(com.quotesapp.dto.Quote.getQuote(quote)));

        if (dbQuotes.size() > 0) {
            notify(dbQuotes);

            return;
        }


        Firebase.getQuotes(internalId, bookId, chapterId, (snapshot, e) -> {

            List<com.quotesapp.dto.Quote> quotes = snapshot.getDocuments()
                    .stream()
                    .map(documentSnapshot -> com.quotesapp.dto.Quote.getQuote(documentSnapshot.getId(), bookId, chapterId, documentSnapshot.getData()))
                    .collect(Collectors.toList());

            notify(quotes);
            writeToDB(quotes);

        });
    }

    public void writeToDB(List<com.quotesapp.dto.Quote> quotes) {
        // write to db
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        quotes.forEach(dto -> com.quotesapp.domains.Quote.fromDTO(realm, dto));
        realm.commitTransaction();
    }

    public void notify(List<com.quotesapp.dto.Quote> quotes) {
        WritableArray payload = Arguments.createArray();
        List<WritableMap> quoteMaps = quotes.stream()
                .map(chapter -> chapter.toWritableMap()).collect(Collectors.toList());
        quoteMaps.stream().forEach(quoteMap -> payload.pushMap(quoteMap));

        ((MainApplication) getApplicationContext())
                .getReactNativeHost()
                .getReactInstanceManager()
                .getCurrentReactContext()
                .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                .emit(Constants.Events.RECEIVED_QUOTES, payload);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
