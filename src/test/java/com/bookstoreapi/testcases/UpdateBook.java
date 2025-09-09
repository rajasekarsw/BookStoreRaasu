package com.bookstoreapi.testcases;

import com.bookstoreapi.apimethods.ResponseExtractor;
import com.bookstoreapi.data.UserData;
import com.bookstoreapi.apimethods.BookStoreAPI;
import com.bookstoreapi.apimethods.SpecBuilder;
import com.bookstoreapi.model.request.Book;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.Map;
import static com.bookstoreapi.testcases.LoginForAccessToken.getAccessToken;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class UpdateBook {
    @Test(description = "Update book by id")
    public void testUpdateBook(){
        int requiredBookId = GetAllTheBooks.getRandomBookId();
        Book updatedBook=UserData.getUpdatedBook(requiredBookId);
        Response response = new BookStoreAPI()
                .updateBookById(updatedBook, Map.ofEntries(getAccessToken()),Map.of("bookId", requiredBookId));
        ResponseExtractor.extract(response,Book.class,200,updatedBook);
    }
}
