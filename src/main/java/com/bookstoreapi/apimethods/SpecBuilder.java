package com.bookstoreapi.apimethods;

import com.bookstoreapi.utils.ConfigReader;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.util.Map;

public class SpecBuilder {

    private static RequestSpecification requestSpecBasic;
    private static ResponseSpecification responseSpecBasic;
    public static RequestSpecification baseRequestSpec(){
      if(requestSpecBasic==null)
        requestSpecBasic= new RequestSpecBuilder()
                .setBaseUri(ConfigReader.getConfigValue(ConfigReader.getConfigValue("env").toLowerCase()+".url"))
                .log(LogDetail.ALL)
                .setContentType(ContentType.JSON)
                .build();
      return requestSpecBasic;
    }

    public static ResponseSpecification basicResponseSpec(){
        if(responseSpecBasic==null)
            responseSpecBasic =new ResponseSpecBuilder()
                .expectContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();
        return responseSpecBasic;
    }

}
