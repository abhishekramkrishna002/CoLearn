package com.quotesapp.bridges;

import android.content.Intent;
import android.util.Log;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.quotesapp.Constants;
import com.quotesapp.listeners.BookListener;
import com.quotesapp.listeners.CategoryListener;

public class BookModule extends ReactContextBaseJavaModule {

    public BookModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    public String getName() {
        return Constants.Modules.BOOK_MODULE;
    }

    @ReactMethod
    public void fetch(Double startAfterInternalId, String categoryId) {
        Intent broadcastIntent = new Intent(getReactApplicationContext(), BookListener.class)
                .setAction(Constants.Actions.GET_BOOKS)
                .putExtra(Constants.IntentFields.CATEGORY_ID, categoryId)
                .putExtra(Constants.IntentFields.INTERNAL_ID, startAfterInternalId.longValue());

        getReactApplicationContext().sendBroadcast(broadcastIntent);
    }

}
