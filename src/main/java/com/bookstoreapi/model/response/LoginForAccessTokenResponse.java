package com.bookstoreapi.model.response;

public record LoginForAccessTokenResponse(String access_token,String token_type) {
    public static final String SCHEMA="schemas/AccessToken.json";
}
