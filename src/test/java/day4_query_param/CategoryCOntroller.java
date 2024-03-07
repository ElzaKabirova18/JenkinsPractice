package day4_query_param;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import pojo.CustomResponse;
import pojo.RequestBody;
import utilities.Config;
import static utilities.CashwiseAuthorization.getToken;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CategoryCOntroller {
    Faker faker = new Faker();
    static String categoryId="";

    @Test
    public void test1_createNewCategory() {
        //https://backend.cashwise.us/api/myaccount/categories
        String url = Config.getProperty("baseUrl") +"/api/myaccount/categories";

        String category = faker.commerce().department();
        String description = category + " company";

        RequestBody requestBody = new RequestBody();
        requestBody.setCategory_title(category);
        requestBody.setCategory_description(description);
        requestBody.setFlag(false);

        /*
        {
    "category_title": "Repare",
    "category_description": "Technic repare",
    "flag": false
}
         */
        Response response = RestAssured.given()
                .auth().oauth2(getToken())
                .contentType(ContentType.JSON)
                .body(requestBody)
                .post(url);

        response.prettyPrint();

        categoryId = response.jsonPath().getString("category_id");
    }

    @Test
    public void test3_getSingleCategory() throws JsonProcessingException {
        //https://backend.cashwise.us/api/myaccount/categories/853

        String url = Config.getProperty("baseUrl")+"/api/myaccount/categories/" + categoryId;
        System.out.println(url);

        Response response = RestAssured.given().auth().oauth2(getToken()).get(url);
        System.out.println("Status code " + response.statusCode());

        System.out.println("===============================================");
        System.out.println(response.jsonPath().getString("category_id"));
        System.out.println(response.jsonPath().getString("category_title"));
        System.out.println(response.jsonPath().getString("category_description"));
        System.out.println("===============================================");


        //deserialization - convert Json object to JAVA
        ObjectMapper mapper = new ObjectMapper();

        System.out.println("======dedicating category class=====");
        CustomResponse customResponse = mapper.readValue(response.asString(), CustomResponse.class);
        System.out.println(customResponse.getCategory_id());
        System.out.println(customResponse.getCategory_title());
        System.out.println(customResponse.getCategory_description());

        System.out.println("=====test started=======");
        Assert.assertNotNull(customResponse.getCategory_id());
        Assert.assertNotNull(customResponse.getCategory_title());
        Assert.assertNotNull(customResponse.getCategory_description());
        System.out.println("=====test finished=======");


    }
}
