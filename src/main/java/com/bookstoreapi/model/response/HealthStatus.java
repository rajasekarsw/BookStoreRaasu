package com.bookstoreapi.model.response;

public record HealthStatus(String status) {
    public static final String SCHEMA="schemas/GetHealth.json";
}
