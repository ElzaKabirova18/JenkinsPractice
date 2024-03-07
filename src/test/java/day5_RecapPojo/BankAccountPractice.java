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
import utilities.Config;

import static utilities.CashwiseAuthorization.getToken;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BankAccountPractice {

    Faker faker = new Faker();
    static String bankID="";

    @Test
    public void test_1_createNewBankAccount() throws JsonProcessingException {
        getToken();
        //https://backend.cashwise.us/api/myaccount/bankaccount
        String url = Config.getProperty("baseUrl") + "/api/myaccount/bankaccount";

        //serialization, we convert our java to json object
        //request body only for POST PUT PATCH
        RequestBody requestBody = new RequestBody(); // go to req.body class and declare your variables
        /* create private variables in RequestBody class
{
    "type_of_pay": "CASH",
    "bank_account_name": "bank of postman17",
    "description": "financial",
    "balance": 5000000
  }
 */
         requestBody.setType_of_pay("CASH");
         requestBody.setBank_account_name(faker.company().name() + " bank");
         requestBody.setDescription(faker.commerce().department() + " company");
         requestBody.setBalance(faker.number().numberBetween(200,30000000));

         //hit API with REstAssured (POST)
        Response response = RestAssured.given()
                .auth().oauth2(getToken()).contentType(ContentType.JSON) // content type only for POST, PUT, PATCH
                .body(requestBody).post(url); // body only for POST, PUT, PATCH

        //printout status code and make sure it's not any mistake
        System.out.println("status code : " + response.statusCode());

        //assert your status code
        Assert.assertEquals("status code is not correct", 201, response.statusCode());

        System.out.println("=======print response body=========");
        response.prettyPrint();

        System.out.println("=======use Object Mapper and get ID=== DESEARILAZION======");
        ObjectMapper mapper = new ObjectMapper();
        // go inside custom response class and specified your variables you want to read(featch data)
        CustomResponse customResponse = mapper.readValue(response.asString(), CustomResponse.class);

        bankID = String.valueOf(customResponse.getId());
        System.out.println("My ID " + bankID);
    }

    @Test
    public void test2_getSingleBankAccount() throws JsonProcessingException {
        String url =Config.getProperty("baseUrl") + "/api/myaccount/bankaccount/"+ bankID;

        Response response = RestAssured.given()
                .auth().oauth2(getToken())
                .get(url);
        System.out.println("My status code: " + response.statusCode());

        Assert.assertEquals(200, response.statusCode());
        System.out.println("==============================");

        ObjectMapper mapper = new ObjectMapper();
        /*
        private int id;
    private String bank_account_name;
    private double balance;
         */

        CustomResponse customResponse = mapper.readValue(response.asString(), CustomResponse.class);

        System.out.println("test started");
        System.out.println(customResponse.getId());
        System.out.println(customResponse.getBank_account_name());
        System.out.println(customResponse.getBalance());
        System.out.println("test passed");


        Assert.assertNotNull(customResponse.getId());
        Assert.assertNotNull(customResponse.getBank_account_name());
        Assert.assertNotNull(customResponse.getBalance());
    }

    @Test
    public void tst3_getListOfBankAccount() throws JsonProcessingException {
        String url = Config.getProperty("baseUrl") + "/api/myaccount/bankaccount";

        Response response = RestAssured.given()
                .auth().oauth2(getToken())
                .get(url);
        System.out.println("My status code: " + response.statusCode());
        Assert.assertEquals(200, response.statusCode());
        System.out.println("==============================");

        response.prettyPrint();

        ObjectMapper mapper = new ObjectMapper();
        CustomResponse[] customResponse = mapper.readValue(response.asString(), CustomResponse[].class);

        int sizeOfBankAcc = customResponse.length;

        for (int i = 0; i < sizeOfBankAcc; i++) {
            System.out.println("My id: " + customResponse[i].getId());
            Assert.assertNotNull(customResponse[i].getId());

            System.out.println("Bank account name: " + customResponse[i].getBank_account_name());
            Assert.assertNotNull(customResponse[i].getBank_account_name());
        }

    }
}
