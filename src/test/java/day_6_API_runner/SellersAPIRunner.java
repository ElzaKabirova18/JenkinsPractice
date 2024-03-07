package day_6_API_runner;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import org.apiguardian.api.API;
import org.junit.Assert;
import org.junit.Test;
import pojo.CustomResponse;
import pojo.RequestBody;
import utilities.APIRunner;

public class SellersAPIRunner {
    Faker faker = new Faker();

    @Test
    public void test1_getALLSellers(){

        //https://backend.cashwise.us/api/myaccount/sellers/all
        String path ="/api/myaccount/sellers/all";

        APIRunner.runGET(path);
        APIRunner.getCustomResponseArray();

        CustomResponse[] customResponses = APIRunner.getCustomResponseArray();

        int sizeOfSellers= customResponses.length;

        for (int i = 0; i < sizeOfSellers; i++) {
            System.out.println(customResponses[i].getSeller_id());
            System.out.println(customResponses[i].getSeller_name());
            Assert.assertNotNull(customResponses[i].getSeller_id());
            Assert.assertNotNull(customResponses[i].getSeller_name());
        }
    }

    @Test
    public void test2_createNewSellerAPIRunner() {
      String path ="/api/myaccount/sellers";

        RequestBody requestBody = new RequestBody();
        requestBody.setSeller_name(faker.name().fullName());
        requestBody.setAddress(faker.address().fullAddress());
        requestBody.setEmail(faker.internet().emailAddress());


        System.out.println(APIRunner.getCustomResponse().getSeller_id());
        System.out.println(APIRunner.getCustomResponse().getSeller_name());
        System.out.println(APIRunner.getCustomResponse().getEmail());
        System.out.println(APIRunner.getCustomResponse().getAddress());

        Assert.assertNotNull(APIRunner.getCustomResponse().getSeller_id());
        Assert.assertNotNull(APIRunner.getCustomResponse().getSeller_name());
        Assert.assertNotNull(APIRunner.getCustomResponse().getEmail());
        Assert.assertNotNull(APIRunner.getCustomResponse().getAddress());

    }
}
