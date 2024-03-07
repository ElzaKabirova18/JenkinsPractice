package steps;

import com.github.javafaker.Faker;
import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import org.junit.Assert;
import pojo.CustomResponse;
import pojo.RequestBody;
import utilities.APIRunner;
import utilities.Config;

import java.sql.*;

public class clientJDBC {
    @Given("new client is created using API")
    public void new_client_is_created_using_api() {

        Faker faker = new Faker();
        RequestBody requestBody = new RequestBody();

        requestBody.setCompany_name(faker.company().name());
        requestBody.setClient_name(faker.name().fullName());
        requestBody.setEmail(faker.internet().emailAddress());
        requestBody.setPhone_number(faker.phoneNumber().cellPhone());
        requestBody.setAddress(faker.address().fullAddress());
        requestBody.setTagId(new int []{50});

        APIRunner.runPOST("/api/myaccount/clients", requestBody);
        System.out.println(APIRunner.getCustomResponse().getClient_name());


    }
    @Then("varify client exist in database")
    public void varify_client_exist_in_database() throws SQLException {
        String sqlQvery = "select client_id, company_name, client_name" +
                " email, phone number, address from clients where client_id = "
                + APIRunner.getCustomResponse().getClient_id();
        String url = "jdbc:postgresql://18.159.5224:5434/postgres";
        String username = "cashwiseuser";
        String password = "cashwisepass";
        Connection connection = DriverManager.getConnection(url, username, password);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sqlQvery);

        CustomResponse response = APIRunner.getCustomResponse();
        while (resultSet.next()) {
            resultSet.getString(1);

            Assert.assertEquals(response.getClient_id(), resultSet.getInt("client_id"));
            Assert.assertEquals(response.getCompanyName(), resultSet.getString("company_name"));
            Assert.assertEquals(response.getEmail(), resultSet.getString("email"));
            Assert.assertEquals(response.getClient_name(), resultSet.getString("client_name"));

        }
    }

    @Then("delete client in database")
    public void delete_client_in_database() throws SQLException {
        String url = "jdbc:postgresql://18.159.5224:5434/postgres";
        String username = "cashwiseuser";
        String password = "cashwisepass";

        Connection connection = DriverManager.getConnection(url, username, password);
        Statement statement = connection.createStatement();

        int client_id = Integer.parseInt(APIRunner.getCustomResponse().getClient_id());

        String query1 = "delete from client_tag where client_id =" + client_id;
        String query2 = "delete from clients where client_id =" + client_id;

        statement.executeQuery(query1);
        statement.executeQuery(query2);

    }
    @Then("varidy client does not exist using API")
    public void varidy_client_does_not_exist_using_api() {
        String client_id = APIRunner.getCustomResponse().getClient_id();

        APIRunner.runGET("/api/myaccount/clients");
        Assert.assertNull(client_id);
    }
}
