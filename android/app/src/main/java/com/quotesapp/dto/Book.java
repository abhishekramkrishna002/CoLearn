package com.quotesapp.dto;

import android.os.Parcel;
import android.os.Parcelable;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;

import java.util.Map;

public class Book {

    String id;
    String title;
    String imageUrl;
    String description;
    String mimeType;
    String author;
    String templateType;
    String categoryId;
    Long internalId;

    public static final class FIELDS {
        public static final String id = "id";
        public static final String title = "title";
        public static final String imageUrl = "imageUrl";
        public static final String description = "description";
        public static final String mimeType = "mimeType";
        public static final String author = "author";
        public static final String displayProperties = "displayProperties";
        public static final String categoryId = "categoryId";
        public static final String templateType = "templateType";
        public static final String internalId = "internalId";

    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    public Book() {
        this.id = "";
        this.title = "";
        this.imageUrl = "";
        this.description = "";
        this.mimeType = "";
        this.author = "";
        this.templateType = "";
        this.categoryId = "";
        this.internalId = 0L;
    }

    public Book(Parcel in) {
        this.id = in.readString();
        this.title = in.readString();
        this.imageUrl = in.readString();
        this.description = in.readString();
        this.mimeType = in.readString();
        this.author = in.readString();
        this.templateType = in.readString();
        this.categoryId = in.readString();
        this.internalId = in.readLong();
    }

    public Book(String id, String title, String imageUrl, String description, String mimeType, String author, String templateType, String categoryId, Long internalId) {
        this.id = id;
        this.title = title;
        this.imageUrl = imageUrl;
        this.description = description;
        this.mimeType = mimeType;
        this.author = author;
        this.templateType = templateType;
        this.categoryId = categoryId;
        this.internalId = internalId;
    }

    public String getId() {
        return id;
    }

    public Book setId(String id) {
        this.id = id;

        return this;
    }

    public String getTitle() {
        return title;
    }

    public Book setTitle(String title) {
        this.title = title;

        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Book setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;

        return this;
    }

    public String getDescription() {
        return description;
    }

    public Book setDescription(String description) {
        this.description = description;

        return this;
    }

    public String getMimeType() {
        return mimeType;
    }

    public Book setMimeType(String mimeType) {
        this.mimeType = mimeType;

        return this;
    }

    public String getAuthor() {
        return author;
    }

    public Book setAuthor(String author) {
        this.author = author;

        return this;
    }

    public String getTemplateType() {
        return templateType;
    }

    public Book setTemplateType(String templateType) {
        this.templateType = templateType;

        return this;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public Long getInternalId() {
        return internalId;
    }

    public Book setInternalId(Long internalId) {
        this.internalId = internalId;

        return this;
    }

    public Book setCategoryId(String categoryId) {
        this.categoryId = categoryId;

        return this;
    }

    public static Parcelable.Creator getCREATOR() {
        return CREATOR;
    }

    public WritableMap toWritableMap() {
        WritableMap map = Arguments.createMap();
        map.putString(FIELDS.id, this.getId());
        map.putString(FIELDS.title, this.getTitle());
        map.putString(FIELDS.imageUrl, this.getImageUrl());
        map.putString(FIELDS.description, this.getDescription());
        map.putString(FIELDS.mimeType, this.getMimeType());
        map.putString(FIELDS.author, this.getAuthor());
        map.putString(FIELDS.templateType, this.getTemplateType());
        map.putString(FIELDS.categoryId, this.getCategoryId());
        map.putString(FIELDS.internalId, this.getInternalId() + "");

        return map;
    }

    public static Book getBook(String id, Map<String, Object> data) {
        Map<String, Object> displayProperties = (Map<String, Object>) data.get(FIELDS.displayProperties);
        return (new Book()
                .setId(id)
                .setTitle((String) data.get(FIELDS.title))
                .setDescription((String) data.get(FIELDS.description))
                .setImageUrl((String) data.get(FIELDS.imageUrl))
                .setMimeType((String) displayProperties.get(FIELDS.mimeType))
                .setTemplateType((String) displayProperties.get(FIELDS.templateType))
                .setAuthor((String) data.get(FIELDS.author))
                .setCategoryId((String) data.get(FIELDS.categoryId))
                .setInternalId((Long) data.get(FIELDS.internalId)));
    }

    public static Book getBook(com.quotesapp.domains.Book book) {
        return (new Book()
                .setId(book.getId())
                .setTitle(book.getTitle())
                .setDescription(book.getDescription())
                .setImageUrl(book.getImageUrl())
                .setMimeType(book.getMimeType())
                .setTemplateType(book.getTemplateType())
                .setAuthor(book.getAuthor())
                .setCategoryId(book.getCategoryId())
                .setInternalId(book.getInternalId()));
    }
}