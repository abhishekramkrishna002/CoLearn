package com.quotesapp;

public class Constants {

    public static class PAGINATION{
        public static final Long PAGE_SIZE = 5l;
    }

    public static class Actions {
        public static final String GET_CATEGORIES = "GET_CATEGORIES";
        public static final String GET_BOOKS = "GET_BOOKS";
        public static final String GET_CHAPTERS = "GET_CHAPTERS";
        public static final String GET_QUOTES = "GET_QUOTES";
    }

    public static class Events {
        public static final String RECEIVED_CATEGORIES = "RECEIVED_CATEGORIES";
        public static final String RECEIVED_BOOKS = "RECEIVED_BOOKS";
        public static final String RECEIVED_CHAPTERS = "RECEIVED_CHAPTERS";
        public static final String RECEIVED_QUOTES = "RECEIVED_QUOTES";
    }

    public static class Modules {
        public static final String CATEGORY_MODULE = "CategoryModule";
        public static final String BOOK_MODULE = "BookModule";
        public static final String CHAPTER_MODULE = "ChapterModule";
        public static final String QUOTE_MODULE = "QuoteModule";
    }

    public static class IntentFields {
        public static final String CATEGORY_ID = "categoryId";
        public static final String BOOK_ID = "bookId";
        public static final String CHAPTER_ID = "chapterId";
        public static final String PAGE_NUMBER = "pageNumber";
        public static final String INTERNAL_ID = "internalID";
    }
}
