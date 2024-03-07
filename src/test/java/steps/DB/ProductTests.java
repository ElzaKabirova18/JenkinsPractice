package steps.DB;

import io.cucumber.java.en.*;
import utilities.DBUtilities;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductTests {

    private static Connection connection;
    private static ResultSet resultSet;


    @Given("i set up connection to database")
    public void i_set_up_connection_to_database() throws SQLException {
        connection = DBUtilities.getConnection();
    }
    @Given("i retrieve all product prices")
    public void i_retrieve_all_product_prices() throws SQLException {
        String query = "select_id, price, title from products";
       resultSet = DBUtilities.executeQuery(connection, query);

    }
    @Then("i verify prices are between {int} and {int}")
    public void i_verify_prices_are_between_and(Integer lowerLimit, Integer upperLimit) throws SQLException {
          while (resultSet.next()) {
             int price = resultSet.getInt("price");
              if (price >lowerLimit || price < upperLimit) {
                  System.out.println("price in the limits");
              }
              else {
                  System.out.println(resultSet.getInt("price"));
                  System.out.println(resultSet.getInt("select_id"));
                  System.out.println(resultSet.getString("title"));
              }
          }
    }
}
