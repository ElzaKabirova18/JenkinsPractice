package day4_query_param;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import utilities.CashwiseAuthorization;
import utilities.Config;

import java.util.HashMap;
import java.util.Map;

import static utilities.CashwiseAuthorization.getToken;

public class SellerController {

    Faker faker = new Faker();
    static String sellerId="";

    @Test
    public void test1_createNewSeller() {
        //https://backend.cashwise.us
        String url = Config.getProperty("baseUrl") + "/api/myaccount/sellers";
        System.out.println(url);
        System.out.println(getToken());

        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("company_name", faker.company().name());
        responseBody.put("seller_name", faker.name().fullName());
        responseBody.put("email", faker.internet().emailAddress());
        responseBody.put("phone_number", faker.phoneNumber().cellPhone());
        responseBody.put("address", faker.address().fullAddress());

        /*
        {
    "company_name": "ElizaCom",
    "seller_name": "EK",
    "email": "777@gmail.com",
    "phone_number": "+3232034033",
    "address": "LA"
  }
         */
        Response response = RestAssured.given()
                .auth().oauth2(getToken())
                .contentType(ContentType.JSON)
                .body(responseBody)
                .post(url);

        Assert.assertEquals("Status code is not correct", 201, response.getStatusCode());
        response.prettyPrint();
        sellerId = response.jsonPath().getString("seller_id");
        System.out.println("seller ID " + sellerId);
    }

    @Test
    public void test2_getArchivedSellers() {
        //https://backend.cashwise.us /api/myaccount/sellers ?isArchived=false&page=1&size=4

        String url =Config.getProperty("baseUrl" + "/api/myaccount/sellers");
        Map<String, Object> param = new HashMap<>();
        param.put("isArchived", false);
        param.put("page", 1);
        param.put("size", 4);

        Response response = RestAssured.given()
                .auth().oauth2(getToken())
                .params(param)
                .get(url);

        response.prettyPrint();
    }

    @Test
    public void test_temporary() {
        String token = CashwiseAuthorization.getToken();
        System.out.println("My token: " + token);
    }
}
