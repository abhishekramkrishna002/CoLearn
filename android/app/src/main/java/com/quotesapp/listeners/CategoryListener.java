package com.quotesapp.listeners;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.quotesapp.Constants;
import com.quotesapp.MainApplication;
import com.quotesapp.services.Category;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CategoryListener extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent receivedIntent) {

        switch (receivedIntent.getAction()) {
            case Constants.Actions.GET_CATEGORIES:
                Intent intent = new Intent(context, Category.class)
                        .putExtra(Constants.IntentFields.INTERNAL_ID, receivedIntent.getLongExtra(Constants.IntentFields.INTERNAL_ID, -1));
                Category.enqueueWork(context, intent);
                break;
        }

    }
}
