package day5_RecapPojo;

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

import static utilities.CashwiseAuthorization.getToken;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SellersPractice {

    Faker faker = new Faker();
    static String sellerID = "";


    @Test
    public void test1_createNewSeller() throws JsonProcessingException {
        String token = CashwiseAuthorization.getToken();
        String url = Config.getProperty("baseUrl") + "/api/myaccount/sellers";

        RequestBody requestBody = new RequestBody();
        /*
        "company_name": "ElizaCom",
   "seller_name": "EK",
   "email": "777@gmail.com",
   "phone_number": "+3232034033",
   "address": "LA"
         */

        requestBody.setCompany_name(faker.company().name() + " company");
        requestBody.setSeller_name(faker.funnyName().name());
        requestBody.setEmail(faker.internet().emailAddress());
        requestBody.setPhone_number(faker.phoneNumber().cellPhone());
        requestBody.setAddress(faker.address().fullAddress());

        Response response = RestAssured.given().auth().oauth2(token).contentType(ContentType.JSON).body(requestBody).post(url);

        System.out.println("Status code " + response.statusCode());
        Assert.assertEquals("status code is not correct", 201, response.statusCode());

        response.prettyPrint();

        ObjectMapper mapper = new ObjectMapper();
        CustomResponse customResponse = mapper.readValue(response.asString(), CustomResponse.class);

        sellerID = String.valueOf(customResponse.getSeller_id());
        System.out.println("My id: " + sellerID);
    }


    @Test
    public void test2_getSingleSeller() throws JsonProcessingException {
        String url = Config.getProperty("baseUrl") + "/api/myaccount/sellers/" + sellerID;
        String token = CashwiseAuthorization.getToken();

        Response response = RestAssured.given().auth().oauth2(token).get(url);
        System.out.println("my status code: " + response.statusCode());

        Assert.assertEquals(200, response.statusCode());

        ObjectMapper mapper = new ObjectMapper();
        /*
        private int seller_id;
    private String seller_name;
    private String email;
    private String address;
         */

        CustomResponse customResponse = mapper.readValue(response.asString(), CustomResponse.class);

        System.out.println(customResponse.getSeller_id());
        System.out.println(customResponse.getSeller_name());
        System.out.println(customResponse.getEmail());
        System.out.println(customResponse.getAddress());

        Assert.assertNotNull(customResponse.getSeller_id());
        Assert.assertNotNull(customResponse.getSeller_name());
        Assert.assertNotNull(customResponse.getEmail());
        Assert.assertNotNull(customResponse.getAddress());

    }

    @Test
    public void tst3_getListOfSellers() throws JsonProcessingException {
        String url = Config.getProperty("baseUrl") + "/api/myaccount/sellers/all";

        Response response = RestAssured.given()
                .auth().oauth2(getToken())
                .get(url);
        System.out.println("My status code: " + response.statusCode());
        Assert.assertEquals(200, response.statusCode());
        System.out.println("==============================");

        response.prettyPrint();

        ObjectMapper mapper = new ObjectMapper();
        CustomResponse[] customResponse = mapper.readValue(response.asString(), CustomResponse[].class);

        int sizeOfSellersList = customResponse.length;

        for (int i = 0; i < sizeOfSellersList; i++) {
            System.out.println("My id: " + customResponse[i].getSeller_id());
            Assert.assertNotNull(customResponse[i].getSeller_id());

            System.out.println("Sellers names: " + customResponse[i].getSeller_name());
            Assert.assertNotNull(customResponse[i].getSeller_name());

            System.out.println("Sellers emails: " + customResponse[i].getEmail());
            Assert.assertNotNull(customResponse[i].getEmail());
        }
    }

    @Test
    public void test4DeleteSELLERById() {
        String url = Config.getProperty("baseUrl") + "/api/myaccount/sellers/" + sellerID;
        Response response = RestAssured.delete(url);
        response.prettyPrint();
        System.out.println("successfully deleted");
    }
}