package HOMEWORK;

import com.github.javafaker.Faker;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import pojo.RequestBody;
import utilities.APIRunner;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductAPIRunnerHW {

    Faker faker = new Faker();
    static String product_id ="";

    @Test
    public void createProductWithAPIRunner() {
        //https://backend.cashwise.us/api/myaccount/products
        String path="/api/myaccount/products";
        RequestBody requestBody = new RequestBody();

        requestBody.setProduct_title(faker.name().title());
        requestBody.setProduct_description(faker.commerce().productName());
        requestBody.setProduct_price(faker.number().numberBetween(100,500));
        requestBody.setService_type_id(2);
        requestBody.setCategory_id(805);



        System.out.println(APIRunner.getCustomResponse().getProduct_id());
        System.out.println(APIRunner.getCustomResponse().getProduct_title());

        product_id = String.valueOf(APIRunner.getCustomResponse().getProduct_id());
    }

    @Test
    public void test2_getSingleProductUseAPIRunner() {
        String path = "/api/myaccount/products/" + product_id;

        APIRunner.runGET(path);

        int product_id = APIRunner.getCustomResponse().getProduct_id();
        String productTitle = APIRunner.getCustomResponse().getProduct_title();

        Assert.assertNotNull(productTitle);
        Assert.assertNotNull(product_id);

    }

    @Test
    public void test3_deleteSingleProductUseAPIRunner() {
        String path = "/api/myaccount/products/" + product_id;

        APIRunner.runDELETE(path);
    }
}
