package day_1;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class ReqresinTest {

    @Test
    public void test_1_getSingleUser() {
        String url = "https://reqres.in/api/users/2";
        Response response = RestAssured.get(url);
        int statusCode = response.statusCode();
        System.out.println("Status code: " + statusCode);

        response.prettyPrint();

    }

    @Test
    public void test_2_getSingleUser() {
        String url = "https://reqres.in/api/users";

        Map<String, Object> params = new HashMap<>();
        params.put("page", 2);

        Response response = RestAssured.given().params(params).get(url);
        System.out.println("Status code: " + response.statusCode());
        response.prettyPrint();

    }

    @Test
    public void test_3_createSingleUser() {
        String url = "https://reqres.in/api/users";

        String requestBody = "{\n" +
                "    \"email\": \"elzaKabirova@gmail.com\", \n" +
                "    \"password\" : \"123456789\"\n" +
                "}";

        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .post(url);

        System.out.println("Status code: " + response.statusCode());
        response.prettyPrint();

    }
}
