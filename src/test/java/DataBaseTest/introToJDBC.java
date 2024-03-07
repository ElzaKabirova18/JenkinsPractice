package DataBaseTest;

import org.junit.Assert;

import java.sql.*;

public class introToJDBC {
    public static void main(String[] args) {

        //Cashwise database
        //host: 18.159.52.24
        //port: 5434
        //database: postgres
        //username: cashwiseuser
        //password: cashwisepass

        String url = "jdbc:postgresql://18.159.52.24:5434/postgres";
        String username = "cashwiseuser";
        String password = "cashwisepass";

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try{
            //1.create a connection with database
            connection = DriverManager.getConnection(url, username, password);
            //2. create statement and execute query
            statement = connection.createStatement();
            String sqlQuery = "select * from clients where client_name = \\John Doe\\;";
            //3. execute query and store the results
            resultSet = statement.executeQuery(sqlQuery);
            //=================create a preparedStatement=============================

//            String sql = "select * from clients where client_name = ?";
//            PreparedStatement preparedStatement = connection.prepareStatement(sql);
//
//            preparedStatement.setString(1, "John Doe");
//            preparedStatement.executeQuery();




            int numOfRec = 0;
            while (resultSet.next()) {
                numOfRec++;
//                System.out.println(resultSet.getString("address"));
//                System.out.println(resultSet.getString("client_id"));
//                System.out.println(resultSet.getArray(1));

                Assert.assertNotNull(resultSet.getString("client_id"));
                Assert.assertNotNull(resultSet.getString("client_id") + " is missing client name"
                , resultSet.getString("client_name"));

            }

            Assert.assertEquals(3867, numOfRec);
            System.out.println("correct");

        }catch(SQLException e){
             e.printStackTrace();

        }

    }
}
