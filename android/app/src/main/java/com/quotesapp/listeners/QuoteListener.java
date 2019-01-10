package com.quotesapp.listeners;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.quotesapp.Constants;
import com.quotesapp.services.Chapter;
import com.quotesapp.services.Quote;

public class QuoteListener extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent receivedIntent) {

        switch (receivedIntent.getAction()) {
            case Constants.Actions.GET_QUOTES:
                Intent intent = new Intent(context, Quote.class);
                intent.putExtra(Constants.IntentFields.BOOK_ID, receivedIntent.getStringExtra(Constants.IntentFields.BOOK_ID));
                intent.putExtra(Constants.IntentFields.CHAPTER_ID, receivedIntent.getStringExtra(Constants.IntentFields.CHAPTER_ID));
                intent.putExtra(Constants.IntentFields.INTERNAL_ID, receivedIntent.getLongExtra(Constants.IntentFields.INTERNAL_ID, -1));

                Quote.enqueueWork(context, intent);
                break;
        }

    }
}
