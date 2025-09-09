package com.bookstoreapi.testcases;

import com.bookstoreapi.apimethods.ResponseExtractor;
import com.bookstoreapi.data.UserData;
import com.bookstoreapi.apimethods.BookStoreAPI;
import com.bookstoreapi.model.response.Detail;
import com.bookstoreapi.model.response.InvalidTokenResponse;
import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.util.Iterator;
import java.util.Map;

public class TokenTestcases {

    @Test(dataProvider ="provideInvalidToken",description = "Invalid token test")
    public void invalidTokens(InvalidTokenResponse tokenResponse){
        Response response= new BookStoreAPI()
                .getAllTheBooks(Map.of(),tokenResponse.token());
        ResponseExtractor.extract(response, Detail.class,tokenResponse.responseCode(),Map.of("detail",tokenResponse.responseMessage()));
    }

    @DataProvider
    public Iterator<InvalidTokenResponse> provideInvalidToken(){
      return UserData
              .getInvalidTokens(LoginForAccessToken.getAccessToken()
              .getValue().split(" ")[0]+" ").iterator();
    }

}
