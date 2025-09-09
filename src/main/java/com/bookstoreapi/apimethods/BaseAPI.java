package com.bookstoreapi.apimethods;

import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class BaseAPI {

    protected Response get(String path, Map<String,?> queryParams,Map<String,String> headers,Map<String,?> pathParams){
        return given()
                .spec(SpecBuilder.baseRequestSpec())
                .pathParams(pathParams)
                .queryParams(queryParams)
                .headers(headers)
                .when()
                .get(path);
    }
    protected Response post(String path,Object body,Map<String,String> headers){
        return given()
                .spec(SpecBuilder.baseRequestSpec())
                .headers(headers)
                .body(body)
                .when()
                .post(path);
    }
    protected Response put(String path,Object body,Map<String,String> headers,Map<String,?> pathParams){
        return given()
                .spec(SpecBuilder.baseRequestSpec())
                .pathParams(pathParams)
                .headers(headers)
                .body(body)
                .when()
                .put(path);
    }
    protected Response delete(String path,Map<String,String> headers,Map<String,?> pathParams){
        return given()
                .spec(SpecBuilder.baseRequestSpec())
                .pathParams(pathParams)
                .headers(headers)
                .when()
                .delete(path);
    }
}
