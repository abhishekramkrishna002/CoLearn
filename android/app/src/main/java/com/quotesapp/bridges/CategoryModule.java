package com.quotesapp.bridges;

import android.content.Intent;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.quotesapp.Constants;
import com.quotesapp.listeners.CategoryListener;

public class CategoryModule extends ReactContextBaseJavaModule {

    public CategoryModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    public String getName() {
        return Constants.Modules.CATEGORY_MODULE;
    }

    @ReactMethod
    public void fetch(Double internalId) {
        Intent broadcastIntent = new Intent(getReactApplicationContext(), CategoryListener.class)
                .setAction(Constants.Actions.GET_CATEGORIES)
                .putExtra(Constants.IntentFields.INTERNAL_ID, internalId.longValue());
        getReactApplicationContext().sendBroadcast(broadcastIntent);
    }

}
