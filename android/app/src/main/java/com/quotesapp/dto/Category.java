package com.quotesapp.dto;

import android.os.Parcel;
import android.os.Parcelable;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;

import java.util.Map;

public class Category implements Parcelable {

    String id;
    String title;
    String categoryId;
    Long orderId;

    public static final class FIELDS {
        public static final String title = "title";
        public static final String categoryId = "categoryId";
        public static final String orderId = "orderId";
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Category createFromParcel(Parcel in) {
            return new Category(in);
        }

        public Category[] newArray(int size) {
            return new Category[size];
        }
    };

    public Category() {
        this.id = "";
        this.title = "";
        this.categoryId = "";
    }

    public Category(Parcel in) {
        this.id = in.readString();
        this.title = in.readString();
        this.categoryId = in.readString();
    }

    public Category(String id, String title, String categoryId) {
        this.id = id;
        this.title = title;
        this.categoryId = categoryId;
    }

    public String getTitle() {
        return title;
    }

    public Category setTitle(String title) {
        this.title = title;

        return this;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public Category setCategoryId(String categoryId) {
        this.categoryId = categoryId;

        return this;
    }

    public String getId() {
        return id;
    }

    public Category setId(String id) {
        this.id = id;

        return this;
    }

    public Long getOrderId() {
        return orderId;
    }

    public Category setOrderId(Long orderId) {
        this.orderId = orderId;

        return this;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(title);
        dest.writeString(categoryId);
        dest.writeLong(orderId);
    }

    public WritableMap toWritableMap() {
        WritableMap categoryMap = Arguments.createMap();
        categoryMap.putString(FIELDS.title, this.getTitle());
        categoryMap.putString(FIELDS.categoryId, this.getCategoryId());
        categoryMap.putString(FIELDS.orderId, this.getOrderId() + "");

        return categoryMap;
    }

    public static Category getCategory(String id, Map<String, Object> data) {
        return (new Category().setId(id).setTitle((String) data.get(FIELDS.title))
                .setCategoryId((String) data.get(FIELDS.categoryId))
                .setOrderId((Long) data.get(FIELDS.orderId)));
    }

    public static Category getCategory(com.quotesapp.domains.Category category) {
        return (new Category().setId(category.getId()).setTitle(category.getTitle())
                .setCategoryId(category.getCategoryId())
                .setOrderId(category.getOrderId()));
    }
}
