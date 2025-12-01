import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


// DBConnection class follows singleton class design patterns
public class DBConnection {

    // filling user and password and url, and make them private static final
    private static final String user = "root";
    private static final String password = "123456";
    private static final String url = "jdbc:mysql://localhost:3306/company_db";

    // singleton instance
    private static Connection connection;

    // creating private constructor to prevent instantiation from outside the class,
    // ensuring that this class cannot be created using 'new' and enforcing the Singleton pattern
    private DBConnection() {

    }

    //now we will create a public method to get ONE SINGLE connection
    public static Connection getConnection() throws SQLException {

        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(url, user, password);
        }
        return connection;

    }

   /* // this function to close the connection
    // please note that is "static" to call it when we need it, an example I think to call it after user press exit
    public static void closeConnection() {

        try{
            if (connection != null && connection.isClosed()) {
                connection.close();
                System.out.println("data base connection closed");
            }
        } catch (SQLException e){
            System.out.println("could not close connection");
        }
    }*/

}
