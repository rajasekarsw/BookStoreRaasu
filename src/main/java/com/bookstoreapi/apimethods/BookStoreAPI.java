package com.bookstoreapi.apimethods;

import com.bookstoreapi.apiendpoints.APIEndpoints;
import io.restassured.response.Response;
import java.util.Map;

public class BookStoreAPI extends BaseAPI{

    public Response checkServer(){
        return get(APIEndpoints.HEALTH,Map.of(),Map.of(),Map.of());
    }
    public Response userSignUp(Object body){
        return post(APIEndpoints.SIGNUP,body,Map.of());
    }
    public Response loginUsingNewUser(Object body){
        return post(APIEndpoints.LOGIN,body,Map.of());
    }
    public Response getAllTheBooks(Map<String,?> queryParams,Map<String,String> headers){
       return get(APIEndpoints.GET_ALL_BOOKS,queryParams,headers,Map.of());
    }
    public Response createBook(Object body,Map<String,String> headers){
        return post(APIEndpoints.CREATE_BOOK,body,headers);
    }
    public Response verifyAddedBookById(Map<String,String> headers,Map<String,?> pathParams){
        return get(APIEndpoints.GET_BOOK,Map.of(),headers,pathParams);
    }

    public Response updateBookById(Object body,Map<String,String> headers,Map<String,?> pathParams){
        return put(APIEndpoints.UPDATE_BOOK,body,headers,pathParams);
    }

    public Response deleteBookById(Map<String,String> headers,Map<String,?> pathParams){
        return delete(APIEndpoints.DELETE_BOOK,headers,pathParams);
    }

}
