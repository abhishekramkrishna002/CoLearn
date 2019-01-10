package com.quotesapp.listeners;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.quotesapp.Constants;
import com.quotesapp.services.Book;
import com.quotesapp.services.Chapter;

public class ChapterListener extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent receivedIntent) {

        switch (receivedIntent.getAction()) {
            case Constants.Actions.GET_CHAPTERS:
                Intent intent = new Intent(context, Chapter.class);
                intent.putExtra(Constants.IntentFields.BOOK_ID, receivedIntent.getStringExtra(Constants.IntentFields.BOOK_ID));
                intent.putExtra(Constants.IntentFields.INTERNAL_ID, receivedIntent.getLongExtra(Constants.IntentFields.INTERNAL_ID, -1));
                Chapter.enqueueWork(context, intent);
                break;
        }

    }
}
