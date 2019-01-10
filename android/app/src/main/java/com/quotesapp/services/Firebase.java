package com.quotesapp.services;

import android.util.Log;

import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.quotesapp.Constants;
import com.quotesapp.dto.Book;
import com.quotesapp.dto.Category;
import com.quotesapp.dto.Chapter;
import com.quotesapp.dto.Quote;

public class Firebase {

    public static void getCategories(Long internalID, EventListener<QuerySnapshot> listener) {
        Query query = FirebaseFirestore.getInstance()
                .collection("Categories")
                .orderBy(Category.FIELDS.orderId);
        if(internalID != -1){
            query.startAfter(internalID);
        }

        query.limit(Constants.PAGINATION.PAGE_SIZE).addSnapshotListener(listener);
    }

    public static void getBooks(Long internalID, String categoryId, EventListener<QuerySnapshot> listener) {
        Query query = FirebaseFirestore.getInstance()
                .collection("Books")
                .whereEqualTo(Book.FIELDS.categoryId, categoryId)
                .orderBy(Book.FIELDS.internalId);

        if (internalID != -1) {
            query = query.startAfter(internalID);
        }

        query.limit(Constants.PAGINATION.PAGE_SIZE).addSnapshotListener(listener);
    }

    public static void getChapters(Long internalID, String bookId, EventListener<QuerySnapshot> listener) {
        Query query = FirebaseFirestore.getInstance()
                .collection("Books/" + bookId + "/chapters")
                .orderBy(Chapter.FIELDS.chapterOrderId);

        if (internalID != -1) {
            query = query.startAfter(internalID);
        }

        query.limit(Constants.PAGINATION.PAGE_SIZE).addSnapshotListener(listener);
    }

    public static void getQuotes(Long internalID, String bookId, String chapterId, EventListener<QuerySnapshot> listener) {
        Query query = FirebaseFirestore.getInstance()
                .collection("Books/" + bookId + "/chapters/" + chapterId + "/quotes")
                .orderBy(Quote.FIELDS.quoteOrderId);

        if (internalID != -1) {
            query = query.startAfter(internalID);
        }

        query.limit(Constants.PAGINATION.PAGE_SIZE).addSnapshotListener(listener);
    }
}
