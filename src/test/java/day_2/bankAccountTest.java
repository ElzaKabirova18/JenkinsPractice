package day_2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import utilities.CashwiseAuthorization;
import utilities.Config;

import java.util.HashMap;
import java.util.Map;


public class bankAccountTest {

    @Test
    public void tokenGenerator() {

        String url = "https://backend.cashwise.us/api/myaccount/auth/login";
        String requestBody = "{\n" +
                "    \"email\": \"elzaKabirova@gmail.com\", \n" +
                "    \"password\" : \"123456789\"\n" +
                "}";

        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .post(url);

        response.prettyPrint();

        //x.jwt_token
        String token = response.jsonPath().getString("jwt_token");
        System.out.println("My token " + token);
    }

    @Test
    public void test2_create_new_bankAccount() {

 String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJlbHphS2FiaXJvdmFAZ21haWwuY29tIiwiZXhwIjoxNzA3MjU4NzY3LCJpYXQiOjE3MDY2NTM5Njd9.4_lSBCfiMD1qWhyPhzqmnVDNxaaHW8xIv3-2XDkXhfT5LeaX5W_zlOGNe5WiETWB9uK_6jSVwXWM8PBDcNifvQ";
 String url = "https://backend.cashwise.us/api/myaccount/bankaccount";

// String requestBody = "{\n" +
//         "    \"type_of_pay\": \"CASH\",\n" +
//         "    \"bank_account_name\": \"bank of postman17\",\n" +
//         "    \"description\": \"financial\",\n" +
//         "    \"balance\": 5000000\n" +
//         "  }";

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("type_of_pay", "BANK" );
        requestBody.put("bank_account_name", "Intellij Bank EZLA" );
        requestBody.put("description", "Financial company");
        requestBody.put("balance", 905 );

        Response response = RestAssured.given().
                auth().oauth2(token)
                .contentType(ContentType.JSON)
                .body(requestBody)
                .post(url);

        System.out.println(response.statusCode());

    }

    @Test
    public void test3_get_listOf_bankAccount() {
        String token = CashwiseAuthorization.getToken();

        //https://backend.cashwise.us/api/myaccount/bankaccount/
        String url = Config.getProperty("baseUrl") + "/api/myaccount/bankaccount/";
        System.out.println(token);
        System.out.println(url);

        Response response = RestAssured.given()
                .auth().oauth2(token)
                .get(url);

        System.out.println("My status code " + response.statusCode());
//        response.prettyPrint();    // this print you all the bodies, after u copy
        // and paste it to Jason pathfinder so from there you can get info
        //using below methods

        int sizeOfBankAccounts = response.jsonPath().getList("").size();
        System.out.println("amount of Banks : " + sizeOfBankAccounts);

        for (int i = 0; i < sizeOfBankAccounts; i++) {
            System.out.println("==============================");

            String id = response.jsonPath().getString("["+ i + "].id");
            System.out.println(id + " ID");

            String bankName = response.jsonPath().getString("[" + i + "].bank_account_name");
            System.out.println("bank acc name from pathFinder: " + bankName);

            String description = response.jsonPath().getString("[" + i + "].description");
            System.out.println("Description " + description);

            String typeOfPay = response.jsonPath().getString("[" + i + "].type_of_pay");
            System.out.println("Type of pay: " + typeOfPay);

            String balance = response.jsonPath().getString("[" + i + "].balance");
            System.out.println("Balance: " + balance);
            System.out.println("==============================");

            Assert.assertNotNull("ID is null " + id);
            Assert.assertNotNull("Bank account is null " + bankName);
            Assert.assertNotNull("Description is null " + description);
            Assert.assertNotNull("Type of payment is null " + typeOfPay);
            Assert.assertNotNull("Balance is null " + balance);

        }
    }
    @Test
    public void test4_get_single_bankAccount() {
        String url = Config.getProperty("baseUrl") + "/api/myaccount/bankaccount/892";
        String token = CashwiseAuthorization.getToken();
//        System.out.println(url);

        Response response = RestAssured.given()
                .auth().oauth2(token)
                .get(url);

        response.prettyPrint();

        //x.bank_account_name
        System.out.println("===========test started==========");
        String bankAccName = response.jsonPath().getString("bank_account_name");
        Assert.assertNotNull(bankAccName); // will print if the name will be null

        System.out.println(bankAccName);

        System.out.println("===========test passed==========");
    }
}
