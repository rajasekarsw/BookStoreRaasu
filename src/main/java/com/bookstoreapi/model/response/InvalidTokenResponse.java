package com.bookstoreapi.model.response;

import java.util.Map;

public record InvalidTokenResponse(Map<String,String> token, int responseCode, String responseMessage) {
}
