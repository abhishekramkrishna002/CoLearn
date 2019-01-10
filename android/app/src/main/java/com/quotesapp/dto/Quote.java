package com.quotesapp.dto;

import android.os.Parcel;
import android.os.Parcelable;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;

import java.util.Map;

public class Quote {

    String id;
    String quote;
    String author;
    String quoteNumber;
    String notes;
    Long quoteOrderId;
    String bookId;
    String chapterId;

    public static final class FIELDS {
        public static final String id = "id";
        public static final String quote = "quote";
        public static final String notes = "notes";
        public static final String author = "author";
        public static final String quoteNumber = "quoteNumber";
        public static final String quoteOrderId = "quoteOrderId";
        public static final String bookId = "bookId";
        public static final String chapterId = "chapterId";
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Quote createFromParcel(Parcel in) {
            return new Quote(in);
        }

        public Quote[] newArray(int size) {
            return new Quote[size];
        }
    };

    public Quote() {
        this.id = "";
        this.quote = "";
        this.author = "";
        this.quoteNumber = "";
        this.notes = "";
        this.quoteOrderId = 0L;
    }

    public Quote(Parcel in) {
        this.id = in.readString();
        this.quote = in.readString();
        this.author = in.readString();
        this.quoteNumber = in.readString();
        this.notes = in.readString();
        this.quoteOrderId = in.readLong();
    }

    public Quote(String id, String quote, String author, String quoteNumber, String notes, Long quoteOrderId) {
        this.id = id;
        this.quote = quote;
        this.author = author;
        this.quoteNumber = quoteNumber;
        this.notes = notes;
        this.quoteOrderId = quoteOrderId;
    }

    public String getId() {
        return id;
    }

    public Quote setId(String id) {
        this.id = id;

        return this;
    }

    public String getQuote() {
        return quote;
    }

    public Quote setQuote(String quote) {
        this.quote = quote;

        return this;
    }

    public String getAuthor() {
        return author;
    }

    public Quote setAuthor(String author) {
        this.author = author;

        return this;
    }

    public String getQuoteNumber() {
        return quoteNumber;
    }

    public Quote setQuoteNumber(String quoteNumber) {
        this.quoteNumber = quoteNumber;

        return this;
    }

    public String getNotes() {
        return notes;
    }

    public Quote setNotes(String notes) {
        this.notes = notes;

        return this;
    }

    public Long getQuoteOrderId() {
        return quoteOrderId;
    }

    public Quote setQuoteOrderId(Long quoteOrderId) {
        this.quoteOrderId = quoteOrderId;

        return this;
    }

    public String getBookId() {
        return bookId;
    }

    public Quote setBookId(String bookId) {
        this.bookId = bookId;

        return this;
    }

    public String getChapterId() {
        return chapterId;
    }

    public Quote setChapterId(String chapterId) {
        this.chapterId = chapterId;

        return this;
    }

    public static Parcelable.Creator getCREATOR() {
        return CREATOR;
    }

    public WritableMap toWritableMap() {
        WritableMap map = Arguments.createMap();
        map.putString(FIELDS.id, this.getId());
        map.putString(FIELDS.quote, this.getQuote());
        map.putString(FIELDS.author, this.getAuthor());
        map.putString(FIELDS.quoteNumber, this.getQuoteNumber());
        map.putString(FIELDS.notes, this.getNotes());
        map.putString(FIELDS.quoteOrderId, this.getQuoteOrderId() + "");

        return map;
    }

    public static Quote getQuote(String id, String bookId, String chapterId, Map<String, Object> data) {
        return (new Quote()
                .setId(id)
                .setQuote((String) data.get(FIELDS.quote))
                .setAuthor((String) data.get(FIELDS.author))
                .setQuoteNumber((String) data.get(FIELDS.quoteNumber))
                .setQuoteOrderId((Long) data.get(FIELDS.quoteOrderId))
                .setNotes((String) data.get(FIELDS.notes))
                .setBookId(bookId)
                .setChapterId(chapterId));
    }

    public static Quote getQuote(com.quotesapp.domains.Quote quote) {
        return (new Quote()
                .setId(quote.getId())
                .setQuote(quote.getQuote())
                .setAuthor(quote.getAuthor())
                .setQuoteNumber(quote.getQuoteNumber())
                .setQuoteOrderId(quote.getQuoteOrderId())
                .setNotes(quote.getNotes())
                .setBookId(quote.getBookId())
                .setChapterId(quote.getChapterId()));
    }
}