package utilities;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import pojo.RequestBody;

public class CashwiseAuthorization {
    private static String token; //encapsulation

    public static String getToken() {

        String url= "https://backend.cashwise.us/api/myaccount/auth/login";
        RequestBody requestBody = new RequestBody();
        requestBody.setEmail(Config.getProperty("email"));
        requestBody.setPassword(Config.getProperty("password"));




//        String requestBody = "{\n" +
//                "    \"email\": \"elzaKabirova@gmail.com\", \n" +
//                "    \"password\" : \"123456789\"\n" +
//                "}";

//        Map<String, Object> requestBody = new HashMap<>();
//        requestBody.put("email", Config.getProperty("email"));
//        requestBody.put("password", Config.getProperty("password"));

        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .post(url);

//        response.prettyPrint();

        //x.jwt_token
        token = response.jsonPath().getString("jwt_token");
//        System.out.println("My token " + token);

        return token;
    }
}
