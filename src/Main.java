import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        // create an instance of the main system controller
        // this class contains all program logic and interaction with the user.
        EmployeeManagementSystem system = new EmployeeManagementSystem();
        system.startProgram();
    }
}