import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


// DBConnection class follows singleton class design patterns
public class DBConnection {

    private static final String username = "";
    private static final String password = "";
    private static final String url = "";

    // singleton instance
    private static Connection connection;

    // crating private constructor to..
    private DBConnection() {

    }

    //now we will create a public method to get ONE SINGLE connection
    public static Connection getConnection() throws SQLException {

        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(url, username, password);
        }
        return connection;

    }

    // this function to close the connection
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
    }


}
