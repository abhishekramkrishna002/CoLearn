package com.quotesapp.domains;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Category extends RealmObject {

    @PrimaryKey
    String id;
    String title;
    String categoryId;
    Long orderId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public static Category fromDTO(Realm realm, com.quotesapp.dto.Category dto) {
        Category category = new Category();
        category.setId(dto.getId());
        category.setTitle(dto.getTitle());
        category.setCategoryId(dto.getCategoryId());

        realm.copyToRealmOrUpdate(category);
        return category;
    }
}
