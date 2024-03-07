package HOMEWORK;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import pojo.CustomResponse;
import pojo.RequestBody;
import utilities.CashwiseAuthorization;
import utilities.Config;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CreateNewProduct {

    Faker faker = new Faker();
    static String product_id = "";

    @Test
    public void test1_createNewProduct() throws JsonProcessingException {
        String token = CashwiseAuthorization.getToken();
        String url = Config.getProperty("baseUrl") + "/api/myaccount/products";

        RequestBody requestBody = new RequestBody();
        /*
        {
    "product_title": "{{product_title}}",
    "product_price": {{product_price}},
    "service_type_id": {{service_type_id}},
    "category_id": {{category_id}},
    "product_description": "{{product_description}}"
}
         */
        requestBody.setProduct_title(faker.name().title());
        requestBody.setProduct_price(faker.number().numberBetween(100,500));
        requestBody.setCategory_id(805);
        requestBody.setService_type_id(2);
        requestBody.setProduct_description(faker.name().title() + " Company");

        Response response = RestAssured.given().auth().oauth2(token).contentType(ContentType.JSON).body(requestBody).post(url);

        System.out.println("Status code " + response.statusCode());
        Assert.assertEquals("status code is not correct", 201, response.statusCode());

        response.prettyPrint();

        ObjectMapper mapper = new ObjectMapper();
        CustomResponse customResponse = mapper.readValue(response.asString(), CustomResponse.class);

        product_id = String.valueOf(customResponse.getProduct_id());
        System.out.println("My id: " + product_id);
    }


    @Test
    public void test2_getSingleProduct() throws JsonProcessingException {
        String url = Config.getProperty("baseUrl") + "/api/myaccount/products/" + product_id;
        String token = CashwiseAuthorization.getToken();

        Response response = RestAssured.given().auth().oauth2(token).get(url);
        System.out.println("my status code: " + response.statusCode());

        Assert.assertEquals(200, response.statusCode());

        ObjectMapper mapper = new ObjectMapper();
        /*
        private String product_title;
        private int product_id;
         */

        CustomResponse customResponse = mapper.readValue(response.asString(), CustomResponse.class);

        System.out.println(customResponse.getProduct_title());
        System.out.println(customResponse.getProduct_id());

        Assert.assertNotNull(customResponse.getProduct_title());
        Assert.assertNotNull(customResponse.getProduct_id());

    }
}
