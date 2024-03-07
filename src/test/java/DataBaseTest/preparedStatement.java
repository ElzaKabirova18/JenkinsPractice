package DataBaseTest;

import org.junit.Test;
import utilities.DBUtilities;

import java.sql.*;

public class preparedStatement {
    /*
    print client_name of all clients whose company name = "Google"
     */
    public static void main(String[] args) throws SQLException {
//        printClients("Google");
        printClient2("Codewise");
    }

    public static void printClients(String companyName) throws SQLException {
        Connection connection = DBUtilities.getConnection();

        String query = "select client_name from clients where " +
                "company_name = '" + companyName + "' ";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        while (resultSet.next()) {
            System.out.println(resultSet.getString(1));
        }

    }


    public static void printClient2(String companyName) throws SQLException {
        Connection connection = DBUtilities.getConnection();
        String query = "select client_name from clients where company_name = ? and address = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);

        preparedStatement.setString(1, companyName);
        ResultSet resultSet = preparedStatement.executeQuery();


        while (resultSet.next()) {
            System.out.println(resultSet.getString(1));
        }
    }

    /*
    executes select statements
    @param connection to Cashwise database
    @param query to be executed
    @return resultSet with data
    @trows SQLexeption

     */

    public static ResultSet executeQuery (Connection connection, String query) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        return preparedStatement.executeQuery();
    }

    public static int executeUpdate(Connection connection, String query) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        return preparedStatement.executeUpdate();

    }

}
