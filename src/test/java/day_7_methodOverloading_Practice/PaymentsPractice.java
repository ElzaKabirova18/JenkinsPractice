package day_7_methodOverloading_Practice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;
import pojo.CustomResponse;
import utilities.CashwiseAuthorization;
import utilities.Config;

import static utilities.CashwiseAuthorization.getToken;

public class PaymentsPractice {

    @Test
    public void test_1_getSinglePayments() throws JsonProcessingException {
        //https://backend.cashwise.us/api/myaccount/payments/212

        String url = Config.getProperty("baseUrl") +"/api/myaccount/payments/212";

        Response response = RestAssured.given()
                .auth().oauth2(getToken()).get(url);

        response.prettyPrint();

        ObjectMapper mapper = new ObjectMapper();
        CustomResponse customResponse =mapper.readValue(response.asString(), CustomResponse.class);

        System.out.println(customResponse.getResponse().getInvoice_title());
        System.out.println(customResponse.getResponse().getTotal_pay());

        System.out.println(customResponse.getCategory_response()[0].getCategory_id());
        System.out.println(customResponse.getCategory_response()[0].getCategory_title());

    }
}
