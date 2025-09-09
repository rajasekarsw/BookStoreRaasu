package com.bookstoreapi.testcases;

import com.bookstoreapi.apimethods.BookStoreAPI;
import com.bookstoreapi.apimethods.ResponseExtractor;
import com.bookstoreapi.model.request.Book;
import com.bookstoreapi.model.response.Detail;
import com.bookstoreapi.model.response.Message;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import java.util.Map;
import static com.bookstoreapi.testcases.LoginForAccessToken.getAccessToken;

public class DeleteBook {
    private static int requiredBookId;
    @Test(priority = 1,description = "Delete book by id")
    public void testDeleteBookById(){
                requiredBookId=GetAllTheBooks.getRandomBookId();
             Response response=   new BookStoreAPI()
                .deleteBookById(Map.ofEntries(getAccessToken()),Map.of("bookId",requiredBookId));
             ResponseExtractor.extract(response, Message.class,200,Map.of("message","Book deleted successfully"));
    }

    @Test(priority = 2,description = "Delete book by id which is already deleted")
    public void testDeleteBookWhichIsAlreadyDeleted(){
       Response response= new BookStoreAPI()
                .deleteBookById(Map.ofEntries(getAccessToken()),Map.of("bookId",requiredBookId));
       ResponseExtractor.extract(response, Detail.class,404,Map.of("detail","Book not found"));
    }

    @Test(priority = 3,description = "Delete All books")
    public void testDeleteAllBooks(){
                GetAllTheBooks.getAllBooks()
                        .stream()
                        .map(Book::id)
                        .forEach( bookId->
                            ResponseExtractor.extract(new BookStoreAPI().deleteBookById(Map.ofEntries(getAccessToken()),Map.of("bookId",bookId)),Message.class,200,
                        Map.of("message","Book deleted successfully")));
    }
}
