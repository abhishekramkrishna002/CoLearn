package com.quotesapp.bridges;

import android.content.Intent;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.quotesapp.Constants;
import com.quotesapp.listeners.ChapterListener;
import com.quotesapp.listeners.QuoteListener;

public class QuoteModule extends ReactContextBaseJavaModule {

    public QuoteModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    public String getName() {
        return Constants.Modules.QUOTE_MODULE;
    }

    @ReactMethod
    public void fetch(Double internalId, String bookId, String chapterId) {
        Intent broadcastIntent = new Intent(getReactApplicationContext(), QuoteListener.class)
                .setAction(Constants.Actions.GET_QUOTES)
                .putExtra(Constants.IntentFields.BOOK_ID, bookId)
                .putExtra(Constants.IntentFields.CHAPTER_ID, chapterId)
                .putExtra(Constants.IntentFields.INTERNAL_ID, internalId.longValue());
        getReactApplicationContext().sendBroadcast(broadcastIntent);
    }

}
