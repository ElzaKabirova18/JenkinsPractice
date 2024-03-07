package day_6_API_runner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import lombok.Data;
import org.junit.Assert;
import org.junit.Test;
import pojo.CustomResponse;
import utilities.APIRunner;
import utilities.CashwiseAuthorization;
import utilities.Config;

import static utilities.CashwiseAuthorization.getToken;

public class GetSingleData {
    /**
     * Get single bank account
     * Create Object mapper
     * Create CustomResponse
     * GET bankID in class level
     * https://backend.cashwise.us   /api/myaccount/bankaccount/  892
     */

    String bankID = "";


    @Test
    public void test_1_getSingleBankAccount() throws JsonProcessingException {
        bankID = "892";
        System.out.println("====REGULAR method==============================");
        //Step - 1   // baseUrl                            path
        String url = Config.getProperty("baseUrl") + "/api/myaccount/bankaccount/" + bankID;

        //Step - 2 Hit GET request
        Response response = RestAssured.given()
                .auth().oauth2(getToken())
                .get(url);

        //response.prettyPrint();

        //Step - 3 create ObjectMapper
        ObjectMapper mapper = new ObjectMapper();

        //Step - 4 create CustomResponse class and Handle exception
        CustomResponse customResponse = mapper.readValue(response.asString(), CustomResponse.class);

        System.out.println("My ID: " + customResponse.getId());
        System.out.println("Bank account Name: " + customResponse.getBank_account_name());
        System.out.println("Balance: " + customResponse.getBalance());


    }

    @Test
    public void test_2_getSingleBankAccount() {
        // https://backend.cashwise.us  /api/myaccount/bankaccount/892
        System.out.println("===========APIRunner =================================================");
        String path = "/api/myaccount/bankaccount/892";

        APIRunner.runGET(path);

        int bankId = APIRunner.getCustomResponse().getId();
        String bankAccountName = APIRunner.getCustomResponse().getBank_account_name();
        double balance = APIRunner.getCustomResponse().getBalance();

        System.out.println("My ID: " + bankId);
        System.out.println("Bank account Name: " + bankAccountName);
        System.out.println("Balance: " + balance);


    }


    /**
     * TASK
     * Get single Seller
     * Create Object mapper
     * Create CustomResponse
     * GET seller_id
     * seller_name
     * https://backend.cashwise.us   /api/myaccount/sellers/3445
     */

    @Test
    public void test_3_getSingleSeller() throws JsonProcessingException {
        // https://backend.cashwise.us   /api/myaccount/sellers/3445
        String url = Config.getProperty("baseUrl") + "/api/myaccount/sellers/3445";

        Response response = RestAssured.given()
                .auth().oauth2(getToken())
                .get(url);

        ObjectMapper mapper = new ObjectMapper();

        CustomResponse customResponse = mapper.readValue(response.asString(), CustomResponse.class);

        System.out.println("Seller id: " + customResponse.getSeller_id());
        System.out.println("Seller name: " + customResponse.getSeller_name());
    }


    @Test
    public void test4_getSingleSeller2() {

        String path= "/api/myaccount/sellers/3445";
        APIRunner.runGET(path);

        int sellerID = APIRunner.getCustomResponse().getSeller_id();
        String nameSeller = APIRunner.getCustomResponse().getSeller_name();
        System.out.println(sellerID + " " + nameSeller);

    }

    @Test
    public void test5_getSingleProduct() {
        //https://backend.cashwise.us/api/myaccount/products/1116

        String path = "/api/myaccount/products/1116";

        APIRunner.runGET(path);

        int product_id = APIRunner.getCustomResponse().getProduct_id();
        String productTitle = APIRunner.getCustomResponse().getProduct_title();

        Assert.assertNotNull(productTitle);
        Assert.assertNotNull(product_id);
    }

}