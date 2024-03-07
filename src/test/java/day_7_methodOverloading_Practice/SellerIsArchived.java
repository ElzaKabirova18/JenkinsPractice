package day_7_methodOverloading_Practice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import pojo.CustomResponse;
import utilities.APIRunner;
import utilities.Config;
import java.util.HashMap;
import java.util.Map;

import static utilities.CashwiseAuthorization.getToken;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SellerIsArchived {

    @Test
    public void test_1GetALLisArchivedSellers() throws JsonProcessingException {
        //https://backend.cashwise.us/api/myaccount/sellers?isArchived=false&page=1&size=4

        String url = Config.getProperty("baseUrl") +"/api/myaccount/sellers";

        Map<String, Object> params = new HashMap<>();
        params.put("isArchived", false);
        params.put("page", 1);
        params.put("size", 4);

        Response response = RestAssured.given().auth().oauth2(getToken())
                .contentType(ContentType.JSON).params(params).get(url);

        response.prettyPrint();

        ObjectMapper mapper = new ObjectMapper();
        CustomResponse customResponses = mapper.readValue(response.asString(), CustomResponse.class);

        int sizeOfArray = customResponses.getResponses().length;

        for (int i = 0; i < sizeOfArray; i++) {
            System.out.println("Seller id " + customResponses.getResponses()[i].getSeller_id());
            System.out.println("Seller name " + customResponses.getResponses()[i].getSeller_name());

            Assert.assertNotNull(customResponses.getResponses()[i].getSeller_id());
            Assert.assertNotNull(customResponses.getResponses()[i].getSeller_name());

        }
    }

@Test
    public void tst2_getAllisArchivedSellersAPIRunner() {
        String path ="/api/myaccount/sellers";

        Map<String, Object> params = new HashMap<>();
        params.put("isArchived", false);
        params.put("page", 1);
        params.put("size", 4);

        APIRunner.runGET(path,params);

//    int sizeOfArray = APIRunner.getCustomResponse().getResponses().length;

    CustomResponse[] customResponseArrayOfSellers = APIRunner.getCustomResponse().getResponses();
    int sizeOfArray = customResponseArrayOfSellers.length;

    for (int i = 0; i < sizeOfArray; i++) {
        customResponseArrayOfSellers[i].getSeller_id();
        Assert.assertNotNull(customResponseArrayOfSellers[i].getSeller_id());

        customResponseArrayOfSellers[i].getSeller_name();
        Assert.assertNotNull(customResponseArrayOfSellers[i].getSeller_name());
    }
}

@Test
    public void test3_getAllSelleresArchivedTrue() {
    String path ="/api/myaccount/sellers";

    Map<String, Object> params = new HashMap<>();
    params.put("isArchived", true);
    params.put("page", 1);
    params.put("size", 2);

    APIRunner.runGET(path,params);

    int sizeOfArray = APIRunner.getCustomResponse().getResponses().length;


    for (int i = 0; i < sizeOfArray; i++) {
        APIRunner.getCustomResponse().getResponses()[i].getSeller_id();
        Assert.assertNotNull(APIRunner.getCustomResponse().getResponses()[i].getSeller_id());

        APIRunner.getCustomResponse().getResponses()[i].getSeller_name();
        Assert.assertNotNull(APIRunner.getCustomResponse().getResponses()[i].getSeller_name());
    }


}

}
