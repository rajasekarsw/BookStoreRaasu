package com.bookstoreapi.testcases;

import com.bookstoreapi.apimethods.ResponseExtractor;
import com.bookstoreapi.data.UserData;
import com.bookstoreapi.apimethods.BookStoreAPI;
import com.bookstoreapi.model.request.Book;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import java.util.Map;
import static com.bookstoreapi.testcases.LoginForAccessToken.getAccessToken;

public class CreateBook {
   private static int bookId;
    @Test(description = "Create new Book")
    public void testCreateBook(){
        Response response=new BookStoreAPI()
                .createBook(UserData.book,Map.ofEntries(getAccessToken()));
        Book book=ResponseExtractor.extract(response,Book.class,200,UserData.book);
            bookId=book.id();
    }

    @Test(description = "Verifying newly added book")
    public void testVerifyAddedBook(){
       Response response= new BookStoreAPI()
                .verifyAddedBookById(Map.ofEntries(getAccessToken()),Map.of("bookId",bookId));
       ResponseExtractor.extract(response, Book.class,200,UserData.book);
    }
}
