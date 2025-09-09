package com.bookstoreapi.model.response;

import com.bookstoreapi.model.request.Book;
import java.util.List;

public record AllTheBooks(List<Book> books) {
    public static final String SCHEMA="schemas/AllBookSchema.json";
}
