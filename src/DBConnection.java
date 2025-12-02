import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// DBConnection class follows singleton class design patterns
public class DBConnection {

    //filling user and password and url, and make them private static final
    private static final String user = "root";
    private static final String password = "123456";
    private static final String url = "jdbc:mysql://localhost:3306/company_db";

    // singleton instance
    private static Connection connection;

    // creating private constructor to prevent instantiation from outside the class,
    // ensuring that this class cannot be created using 'new' and enforcing the Singleton pattern
    private DBConnection() {
    }

    //this is (singleton patterns)
    //now we will create a public method to get ONE SINGLE connection :
    public static Connection getConnection() throws SQLException {

        // it returns the single shard database connection instance, if the connection has not been created yet
        // or if it was closed , and this method initializes new connection using URL and username and password,
        //otherwise it simply returns the existing one.

        // this ensures that only one connection object is used throughout the application

        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(url, user, password);
        }
        return connection;

    }


}
