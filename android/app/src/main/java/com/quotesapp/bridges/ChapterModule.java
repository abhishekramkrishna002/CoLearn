package com.quotesapp.bridges;

import android.content.Intent;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.quotesapp.Constants;
import com.quotesapp.listeners.BookListener;
import com.quotesapp.listeners.ChapterListener;

public class ChapterModule extends ReactContextBaseJavaModule {

    public ChapterModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    public String getName() {
        return Constants.Modules.CHAPTER_MODULE;
    }

    @ReactMethod
    public void fetch(Double startAfterInternalId, String bookId) {
        Intent broadcastIntent = new Intent(getReactApplicationContext(), ChapterListener.class)
                .setAction(Constants.Actions.GET_CHAPTERS)
                .putExtra(Constants.IntentFields.BOOK_ID, bookId)
                .putExtra(Constants.IntentFields.INTERNAL_ID, startAfterInternalId);
        getReactApplicationContext().sendBroadcast(broadcastIntent);
    }

}
