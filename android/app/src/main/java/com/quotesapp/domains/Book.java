package com.quotesapp.domains;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Book extends RealmObject {

    @PrimaryKey
    private String compositeId;
    private String id;
    private String title;
    private String imageUrl;
    private String description;
    private String mimeType;
    private String author;
    private String templateType;
    private String categoryId;
    private Long internalId;

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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTemplateType() {
        return templateType;
    }

    public void setTemplateType(String templateType) {
        this.templateType = templateType;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public Long getInternalId() {
        return internalId;
    }

    public void setInternalId(Long internalId) {
        this.internalId = internalId;
    }

    public String getCompositeId() {
        return compositeId;
    }

    public void setCompositeId(String compositeId) {
        this.compositeId = compositeId;
    }

    public static Book fromDTO(Realm realm, com.quotesapp.dto.Book bookDTO) {
        String compositeId = bookDTO.getId()+" "+bookDTO.getCategoryId();

        Book book = new Book();
        book.setId(bookDTO.getId());
        book.setTitle(bookDTO.getTitle());
        book.setAuthor(bookDTO.getAuthor());
        book.setDescription(bookDTO.getDescription());
        book.setImageUrl(bookDTO.getImageUrl());
        book.setMimeType(bookDTO.getMimeType());
        book.setTemplateType(bookDTO.getTemplateType());
        book.setCategoryId(bookDTO.getCategoryId());
        book.setInternalId(bookDTO.getInternalId());
        book.setCompositeId(compositeId);

        realm.copyToRealmOrUpdate(book);

        return book;
    }
}
