package com.bookstoreapi.testcases;

import com.bookstoreapi.apimethods.BookStoreAPI;
import com.bookstoreapi.apimethods.ResponseExtractor;
import com.bookstoreapi.apimethods.SpecBuilder;
import com.bookstoreapi.model.request.Book;
import com.bookstoreapi.model.response.AllTheBooks;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import java.util.List;
import java.util.Map;
import static com.bookstoreapi.testcases.LoginForAccessToken.getAccessToken;

public class GetAllTheBooks {

    @Test(description = "Get book list")
    public void testGetAllTheBooks(){
       getAllBooks();
    }

    public static List<Book> getAllBooks(){
        Response response= new BookStoreAPI()
                .getAllTheBooks(Map.of(),Map.ofEntries(getAccessToken()));
        return   ResponseExtractor.extract(response, new TypeRef<>(){},200,AllTheBooks.SCHEMA);
    }

    public static int getRandomBookId(){
       return getAllBooks().stream().map(Book::id).findAny().orElseThrow();
    }
}
