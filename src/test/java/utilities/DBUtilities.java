package utilities;

import java.sql.*;

public class DBUtilities {

    private static final String url = Config.getProperty("dbUrl");
    private static final String username = Config.getProperty("dbUsername");
    private static final String password = Config.getProperty("dbPassword");


    /*
    set up connection with cashwise database
    @return
    @throws SQLExeptions
     */

      public static Connection getConnection() throws SQLException {
          return DriverManager.getConnection(url, username, password);
      }

      /*
      closes open SQL connection
      @param connection

       */
      public static void closeConnection(Connection connection) {
          try{
              if (connection !=null) {
                  connection.close();
              }
          }catch (SQLException e) {
              e.printStackTrace();
          }
      }

/*
executes select Statement
@param connection to Cashwise Database
@param query to be executed
@return ResultSet with data
@throws SQLexception
 */

    public static ResultSet executeQuery (Connection connection, String query) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        return preparedStatement.executeQuery();
    }


}
