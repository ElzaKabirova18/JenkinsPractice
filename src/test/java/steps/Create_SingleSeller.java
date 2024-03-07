package steps;

import com.github.javafaker.Faker;
import io.cucumber.java.en.*;
import org.junit.Assert;
import pojo.RequestBody;
import utilities.APIRunner;
public class Create_SingleSeller {

    static String seller_id="";
    Faker faker = new Faker();

    static String phone_number;
    static String seller_name;
    static String address;
    static String email;
    RequestBody requestBody = new RequestBody();

    @Given("path {string}")
    public void path(String path) {

        phone_number = faker.phoneNumber().cellPhone();
        seller_name = faker.name().fullName();
        address = faker.address().fullAddress();
        email = faker.internet().emailAddress();

        requestBody.setPhone_number(phone_number);
        requestBody.setSeller_name(seller_name);
        requestBody.setAddress(address);
        requestBody.setEmail(email);

        APIRunner.runPOST(path,requestBody);
    }
    @When("user add variables for creating a new single seller")
    public void user_add_variables_for_creating_a_new_single_seller() {
        System.out.println("========================");

    }

    @When("user creates seller_id, should be able to get seller_id")
    public void user_creates_seller_id_should_be_able_to_get_seller_id() {
        seller_id= String.valueOf((APIRunner.getCustomResponse().getSeller_id()));

        System.out.println((APIRunner.getCustomResponse().getSeller_id()));
        System.out.println(APIRunner.getCustomResponse().getSeller_name());
        System.out.println(APIRunner.getCustomResponse().getEmail());
        System.out.println(APIRunner.getCustomResponse().getAddress());
        System.out.println(APIRunner.getCustomResponse().getPhone_number());

    }

    @Then("user should be able get this seller using seller_id and using path {string}")
        public void user_should_be_able_get_this_seller_using_seller_id_and_using_path(String path) {
        String path2 = path + seller_id;
        APIRunner.runGET(path2);

//        String actualPhoneNum = APIRunner.getCustomResponse().getPhone_number();
//        String actualSellerName = APIRunner.getCustomResponse().getSeller_name();
//        String actualSellerAddress = APIRunner.getCustomResponse().getAddress();
//        String actualEmail = APIRunner.getCustomResponse().getEmail();
        }

    @Then("shoul be able validate all variables from response like: seller_name, email, address, phone_number")
    public void shoul_be_able_validate_all_variables_from_response_like_seller_name_email_address_phone_number() {
                Assert.assertNotNull(APIRunner.getCustomResponse().getSeller_id());
                Assert.assertNotNull(APIRunner.getCustomResponse().getSeller_name());
                Assert.assertNotNull(APIRunner.getCustomResponse().getEmail());
                Assert.assertNotNull(APIRunner.getCustomResponse().getAddress());

    }

//    @Then("delete same product by id")
//    public void delete_same_product_by_id() {
//        String path ="/api/myaccount/sellers";
//        String path2 = path +"/"+ seller_id;
//        APIRunner.runDELETE( path2 );
//        System.out.println("====== PRODUCT DELETED ===============");
//    }

}


