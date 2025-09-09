package com.bookstoreapi.apimethods;

import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.testng.Assert;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Map;

import static com.bookstoreapi.apimethods.SpecBuilder.basicResponseSpec;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class ResponseExtractor {

    private static<T> T doExtract(Response response, TypeRef<T> typeRef,int statusCode,String schema){

        T actual;
        actual = response
                .then()
                .statusCode(statusCode)
                .spec(basicResponseSpec())
                .body(matchesJsonSchemaInClasspath(schema))
                .extract()
                .as(typeRef);

        return actual;
    }

    private static <T> T doExtract(Response response, Class<T> clazz, int statuscode, T expectedObject, Map<String,String> expectedMessage){

        try {
          String schema= (String) clazz.getField("SCHEMA").get(null);

            T actual = response
                    .then()
                    .statusCode(statuscode)
                    .spec(basicResponseSpec())
                    .body(matchesJsonSchemaInClasspath(schema))
                    .extract()
                    .as(clazz);

            if(expectedObject!=null)
                Assert.assertEquals(actual,expectedObject,clazz.getSimpleName()+" mismatch");
            if(expectedMessage!=null ) {
                for(Map.Entry<String,String> entry:expectedMessage.entrySet()){
                    Field field=clazz.getDeclaredField(entry.getKey());
                    field.setAccessible(true);
                    Assert.assertEquals(String.valueOf(field.get(actual)), String.valueOf(entry.getValue()), "Message doesn't match");
                }
            }
            return actual;
        }
         catch (NoSuchFieldException | IllegalAccessException ex){
            throw new RuntimeException("Schema not defined or extraction failed for "+ clazz.getSimpleName());
         }
    }


    public static  <T> T extract(Response response,Class<T> clazz,int expectedStatus,T expectedObject){
        return  doExtract(response,clazz,expectedStatus,expectedObject,null);
    }

    public static <T> T extract(Response response, Class<T> clazz, int expectedStatus, Map<String,String> expectedmessage){
        return doExtract(response, clazz, expectedStatus, null, expectedmessage);
    }
    public static <T> T extract(Response response,Class<T> clazz,int expectedStatus){
        return doExtract(response,clazz,expectedStatus,null,null);
    }

    public static<T> T extract(Response response, TypeRef<T> typeRef,int statusCode,String schema) {
     return doExtract(response,typeRef,statusCode,schema);
    }
}
