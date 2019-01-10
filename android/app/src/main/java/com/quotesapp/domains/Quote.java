package com.quotesapp.domains;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Quote extends RealmObject {

    @PrimaryKey
    String compositeId;
    String id;
    String quote;
    String author;
    String quoteNumber;
    String notes;
    Long quoteOrderId;
    String bookId;
    String chapterId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getQuoteNumber() {
        return quoteNumber;
    }

    public void setQuoteNumber(String quoteNumber) {
        this.quoteNumber = quoteNumber;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Long getQuoteOrderId() {
        return quoteOrderId;
    }

    public void setQuoteOrderId(Long quoteOrderId) {
        this.quoteOrderId = quoteOrderId;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getChapterId() {
        return chapterId;
    }

    public void setChapterId(String chapterId) {
        this.chapterId = chapterId;
    }

    public String getCompositeId() {
        return compositeId;
    }

    public void setCompositeId(String compositeId) {
        this.compositeId = compositeId;
    }

    public static Quote fromDTO(Realm realm, com.quotesapp.dto.Quote quoteDTO) {
        String compositeId = quoteDTO.getId()+" "+quoteDTO.getBookId()+ " "+quoteDTO.getChapterId();

        Quote quote = new Quote();
        quote.setId(quoteDTO.getId());
        quote.setAuthor(quoteDTO.getAuthor());
        quote.setNotes(quoteDTO.getNotes());
        quote.setQuote(quoteDTO.getQuote());
        quote.setQuoteNumber(quoteDTO.getQuoteNumber());
        quote.setQuoteOrderId(quoteDTO.getQuoteOrderId());
        quote.setBookId(quoteDTO.getBookId());
        quote.setChapterId(quoteDTO.getChapterId());
        quote.setCompositeId(compositeId);

        realm.copyToRealmOrUpdate(quote);

        return quote;
    }
}
