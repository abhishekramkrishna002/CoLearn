package com.quotesapp.dto;

import android.os.Parcel;
import android.os.Parcelable;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;

import java.util.Map;

public class Chapter {

    String id;
    String title;
    Long chapterOrderId;
    String bookId;

    public static final class FIELDS {
        public static final String id = "id";
        public static final String title = "title";
        public static final String chapterOrderId = "chapterOrderId";
        public static final String bookId = "bookId";
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Chapter createFromParcel(Parcel in) {
            return new Chapter(in);
        }

        public Chapter[] newArray(int size) {
            return new Chapter[size];
        }
    };

    public Chapter() {
        this.id = "";
        this.title = "";
        this.chapterOrderId = 0L;
    }

    public Chapter(Parcel in) {
        this.id = in.readString();
        this.title = in.readString();
        this.chapterOrderId = in.readLong();
    }

    public Chapter(String id, String title, Long chapterOrderId) {
        this.id = id;
        this.title = title;
        this.chapterOrderId = chapterOrderId;
    }

    public String getId() {
        return id;
    }

    public Chapter setId(String id) {
        this.id = id;

        return this;
    }

    public String getTitle() {
        return title;
    }

    public Chapter setTitle(String title) {
        this.title = title;

        return this;
    }

    public Long getChapterOrderId() {
        return chapterOrderId;
    }

    public Chapter setChapterOrderId(Long chapterOrderId) {
        this.chapterOrderId = chapterOrderId;

        return this;
    }

    public String getBookId() {
        return bookId;
    }

    public Chapter setBookId(String bookId) {
        this.bookId = bookId;

        return this;
    }

    public static Parcelable.Creator getCREATOR() {
        return CREATOR;
    }

    public WritableMap toWritableMap() {
        WritableMap map = Arguments.createMap();
        map.putString(FIELDS.id, this.getId());
        map.putString(FIELDS.title, this.getTitle());
        map.putString(FIELDS.chapterOrderId, this.getChapterOrderId() + "");

        return map;
    }

    public static Chapter getChapter(String id, String bookId, Map<String, Object> data) {
        return (new Chapter()
                .setId(id)
                .setTitle((String) data.get(FIELDS.title))
                .setBookId(bookId)
                .setChapterOrderId((Long) data.get(FIELDS.chapterOrderId)));
    }

    public static Chapter getChapter(com.quotesapp.domains.Chapter chapter) {
        return (new Chapter()
                .setId(chapter.getId())
                .setTitle(chapter.getTitle())
                .setBookId(chapter.getBookId())
                .setChapterOrderId(chapter.getChapterOrderId()));
    }
}