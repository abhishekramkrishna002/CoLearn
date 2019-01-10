package com.quotesapp.listeners;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.quotesapp.Constants;
import com.quotesapp.services.Book;
import com.quotesapp.services.Category;

public class BookListener extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent receivedIntent) {

        switch (receivedIntent.getAction()) {
            case Constants.Actions.GET_BOOKS:
                Intent intent = new Intent(context, Book.class);
                intent.putExtra(Constants.IntentFields.CATEGORY_ID, receivedIntent.getStringExtra(Constants.IntentFields.CATEGORY_ID));
                intent.putExtra(Constants.IntentFields.INTERNAL_ID, receivedIntent.getLongExtra(Constants.IntentFields.INTERNAL_ID, -1));
                Book.enqueueWork(context, intent);
                break;
        }

    }
}
