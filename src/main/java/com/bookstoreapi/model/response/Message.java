package com.bookstoreapi.model.response;

public record Message(String message) {
    public static final String SCHEMA="schemas/Message.json";
}
