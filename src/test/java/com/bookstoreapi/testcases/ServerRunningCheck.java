package com.bookstoreapi.testcases;

import com.bookstoreapi.apimethods.BookStoreAPI;
import com.bookstoreapi.apimethods.ResponseExtractor;
import com.bookstoreapi.apimethods.SpecBuilder;
import com.bookstoreapi.model.response.HealthStatus;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import java.util.Map;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;

public class ServerRunningCheck {

    @Test(description = "Checking the server is running")
    public void testCheckServer(){
        ResponseExtractor.extract(new BookStoreAPI().checkServer(), HealthStatus.class,200,Map.of("status","up"));
    }
}
