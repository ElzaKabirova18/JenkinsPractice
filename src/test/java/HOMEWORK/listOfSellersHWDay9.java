package HOMEWORK;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import utilities.CashwiseAuthorization;
import utilities.Config;

public class listOfSellersHWDay9 {

    @Test
    public void get_list_of_sellers() {
        String token = CashwiseAuthorization.getToken();
        String url = Config.getProperty("baseUrl") + "/api/myaccount/sellers/all";

        System.out.println(token);
        System.out.println(url);

        Response response = RestAssured.given().auth().oauth2(token).get(url);
        System.out.println("my status code " + response.statusCode());

//        response.prettyPrint();

        int amountOfSellers = response.jsonPath().getList("").size();
        System.out.println("size of Sellers: " + amountOfSellers);

        for (int i = 0; i < amountOfSellers; i++) {

            System.out.println("=====================");

            String id = response.jsonPath().getString("[" + i + "].seller_id");
            String companyName = response.jsonPath().getString("[" + i + "].company_name");
            String sellerName = response.jsonPath().getString("[" +i+ "].seller_name");
//            String surname = response.jsonPath().getString("[" + i + "].seller_surname");

            System.out.println("id is: " + id);
            System.out.println("company name is: " +companyName);
            System.out.println("seller name is: " + sellerName);
//            System.out.println("seller surname " + surname);

            Assert.assertNotNull(id);
            Assert.assertNotNull(companyName);
            Assert.assertNotNull(sellerName);
//            Assert.assertNotNull(surname);

        }


    }
}
