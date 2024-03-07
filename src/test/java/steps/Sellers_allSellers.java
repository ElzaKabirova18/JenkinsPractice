package steps;

import io.cucumber.java.en.*;
import org.junit.Assert;
import utilities.APIRunner;

public class Sellers_allSellers {

    @Given("a path {string}")
    public void a_path(String path) {
        APIRunner.runGET(path);
        System.out.println(path);

    }
    @When("get their names, print and validate they are not Null")
    public void get_their_names_print_and_validate_they_are_not_null() {

        int sizeOfListSellers = APIRunner.getCustomResponseArray().length;

        for (int i = 0; i < sizeOfListSellers; i++) {
            System.out.println("Seller name:  " + APIRunner.getCustomResponseArray()[i].getSeller_name());
            Assert.assertNotNull(APIRunner.getCustomResponseArray()[i].getSeller_name());
        }
    }
}
