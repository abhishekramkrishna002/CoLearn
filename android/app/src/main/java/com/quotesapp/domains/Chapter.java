package com.quotesapp.domains;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Chapter extends RealmObject {

    @PrimaryKey
    String compositeId;
    String id;
    String title;
    Long chapterOrderId;
    String bookId;

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

    public Long getChapterOrderId() {
        return chapterOrderId;
    }

    public void setChapterOrderId(Long chapterOrderId) {
        this.chapterOrderId = chapterOrderId;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getCompositeId() {
        return compositeId;
    }

    public void setCompositeId(String compositeId) {
        this.compositeId = compositeId;
    }

    public static Chapter fromDTO(Realm realm, com.quotesapp.dto.Chapter chapterDTO) {
        String compositeId = chapterDTO.getId()+" "+chapterDTO.getBookId();

        Chapter chapter = new Chapter();
        chapter.setId(chapterDTO.getId());
        chapter.setTitle(chapterDTO.getTitle());
        chapter.setChapterOrderId(chapterDTO.getChapterOrderId());
        chapter.setBookId(chapterDTO.getBookId());
        chapter.setCompositeId(compositeId);

        realm.copyToRealmOrUpdate(chapter);

        return chapter;
    }
}
