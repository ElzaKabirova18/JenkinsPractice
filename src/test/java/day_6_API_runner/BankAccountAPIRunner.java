package day_6_API_runner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apiguardian.api.API;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import pojo.CustomResponse;
import pojo.RequestBody;
import utilities.APIRunner;
import utilities.CashwiseAuthorization;
import utilities.Config;

import static utilities.CashwiseAuthorization.getToken;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BankAccountAPIRunner {

    Faker faker = new Faker();
    static String bankID="";

//    @Test
//    public void test1_getListOfBankAccounts() throws JsonProcessingException {
//
//        //https://backend.cashwise.us/api/myaccount/bankaccount
//
//        String url = Config.getProperty("baseUrl") + "/api/myaccount/bankaccount";
//        String token = getToken();
//        Response response = RestAssured.given().auth().oauth2(token)
//                .get(url);
//
//        ObjectMapper mapper = new ObjectMapper();
//        CustomResponse[] customResponse = mapper.readValue(response.asString(), CustomResponse[].class);
//
//        int sizeOfAccounts = customResponse.length;
//
//        for (int i = 0; i < sizeOfAccounts; i++) {
//            System.out.println(customResponse[i].getId());
//            System.out.println(customResponse[i].getBank_account_name());
//            System.out.println(customResponse[i].getBalance());
//
//            Assert.assertNotNull(customResponse[i].getId());
//            Assert.assertNotNull(customResponse[i].getBank_account_name());
//            Assert.assertNotNull(customResponse[i].getBalance());
//
//        }
//    }

@Test
    public void test2_getListOfBankWithAPIRunner() {
        String path ="/api/myaccount/bankaccount";

    APIRunner.runGET(path);
    APIRunner.getCustomResponseArray();
    CustomResponse [] customResponses = APIRunner.getCustomResponseArray();

    int sizeOFBanks = customResponses.length;

    for (int i = 0; i < sizeOFBanks; i++) {
        System.out.println(customResponses[i].getId());
        System.out.println(customResponses[i].getBank_account_name());
        Assert.assertNotNull(customResponses[i].getId());
        Assert.assertNotNull(customResponses[i].getBank_account_name());
    }
}

//    @Test
//    public void test_3_createNewBankAccount() throws JsonProcessingException {
//
//        // https://backend.cashwise.us   /api/myaccount/bankaccount    // STEP -==> set up your URL
//        String url = Config.getProperty("baseUrl") + "/api/myaccount/bankaccount";
//
//        RequestBody requestBody = new RequestBody();
//        requestBody.setType_of_pay("CASH");
//
//        requestBody.setBank_account_name(  faker.company().name()+ " bank"  );
//        requestBody.setDescription( faker.commerce().department()+ " company" );
//        requestBody.setBalance( faker.number().numberBetween(200, 15000)  );
//
//        Response response = RestAssured.given()
//                .auth().oauth2(   getToken()  )
//                .contentType( ContentType.JSON )
//                .body( requestBody )
//                .post(url );
//
//        ObjectMapper mapper = new ObjectMapper();
//        CustomResponse customResponse = mapper.readValue(  response.asString(), CustomResponse.class  );
//
//        bankID = String.valueOf(customResponse.getId());
//        response.prettyPrint();
//    }

    @Test
    public void test4_createBankAccountUseAPIRunner() {
        //// https://backend.cashwise.us   /api/myaccount/bankaccount

        String path ="/api/myaccount/bankaccount";

        RequestBody requestBody = new RequestBody();
        requestBody.setType_of_pay("CASH");
        requestBody.setBank_account_name(faker.company().name() + " Bank");
        requestBody.setDescription(faker.commerce().department() + "company");
        requestBody.setBalance(faker.number().numberBetween(100,10000));


        System.out.println(APIRunner.getCustomResponse().getBank_account_name());
        System.out.println(APIRunner.getCustomResponse().getId());
        System.out.println(APIRunner.getCustomResponse().getBalance());

        bankID = String.valueOf(APIRunner.getCustomResponse().getId());
    }

@Test
    public void test5_deleteBankAccountUsingIDAPIRunner() {
 String path ="/api/myaccount/bankaccount/" + bankID;
 APIRunner.runDELETE(path);
}

}
