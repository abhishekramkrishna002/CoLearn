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

public class Category extends JobIntentService {

    static final int JOB_ID = 3000;

    public static void enqueueWork(Context context, Intent work) {
        enqueueWork(context, Category.class, JOB_ID, work);
    }

    @Override
    protected void onHandleWork(@NonNull Intent intent) {
        Long internalId = intent.getLongExtra(Constants.IntentFields.INTERNAL_ID, -1);

        // fetch from db
        Realm realm = Realm.getDefaultInstance();
        RealmQuery<com.quotesapp.domains.Category> realmQuery = realm.where(com.quotesapp.domains.Category.class);
        realmQuery = internalId != -1 ? realmQuery.greaterThan(com.quotesapp.dto.Category.FIELDS.orderId, internalId) : realmQuery;
        realmQuery = realmQuery.sort(com.quotesapp.dto.Category.FIELDS.orderId, Sort.ASCENDING)
                .limit(Constants.PAGINATION.PAGE_SIZE);
        RealmResults<com.quotesapp.domains.Category> categoryResults = realmQuery.findAll();
        List<com.quotesapp.dto.Category> dbCategories = new ArrayList<>();
        categoryResults.listIterator().forEachRemaining(category -> dbCategories.add(com.quotesapp.dto.Category.getCategory(category)));

        if (dbCategories.size() > 0) {
            notify(dbCategories);

            return;
        }

        Firebase.getCategories(internalId, (snapshot, e) -> {

            List<com.quotesapp.dto.Category> categories = snapshot.getDocuments()
                    .stream()
                    .map(documentSnapshot -> com.quotesapp.dto.Category.getCategory(documentSnapshot.getId(), documentSnapshot.getData()))
                    .collect(Collectors.toList());

            notify(categories);
            writeToDB(categories);

        });
    }

    public void notify(List<com.quotesapp.dto.Category> categories) {
        List<WritableMap> categoryMaps = categories.stream()
                .map(category -> category.toWritableMap()).collect(Collectors.toList());

        WritableArray payload = Arguments.createArray();
        categoryMaps.stream().forEach(categoryMap -> payload.pushMap(categoryMap));

        ((MainApplication) getApplicationContext())
                .getReactNativeHost()
                .getReactInstanceManager()
                .getCurrentReactContext()
                .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                .emit(Constants.Events.RECEIVED_CATEGORIES, payload);
    }

    public void writeToDB(List<com.quotesapp.dto.Category> categories) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        categories.forEach(dto -> com.quotesapp.domains.Category.fromDTO(realm, dto));
        realm.commitTransaction();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
