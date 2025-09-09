package com.bookstoreapi.testcases;

import com.bookstoreapi.apimethods.ResponseExtractor;
import com.bookstoreapi.data.UserData;
import com.bookstoreapi.apimethods.BookStoreAPI;
import com.bookstoreapi.apimethods.SpecBuilder;
import com.bookstoreapi.model.response.Detail;
import com.bookstoreapi.model.response.Message;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import java.util.Map;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;

public class UserSignUpClass {

    @Test(description = "User sign up")
    public void testUserSignUp(){
        Response response=new BookStoreAPI()
                .userSignUp(UserData.signUp);
        ResponseExtractor.extract(response, Message.class,200,Map.of("message","User created successfully"));
    }

    @Test(dependsOnMethods = "testUserSignUp",description = "User sign up with existing users")
    public void testSignUpWithExistingUser(){
       Response response=  new BookStoreAPI()
                .userSignUp(UserData.signUp);
       ResponseExtractor.extract(response, Detail.class,400,Map.of("detail","Email already registered"));

    }
}
