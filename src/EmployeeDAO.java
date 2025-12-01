import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {

    // Add new employee to the database:
    public boolean addEmployee(Employee employee) {

        // sql query loaded from separate method for cleaner code and reusability
        String sql = sqlAddQuery("employees");

        // try with resources ensures the connection and prepared statement are closed automatically:
        try (Connection connection = DBConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {

            // setting the sql parameters using the employee object's fields
            statement.setString(1, employee.name());
            statement.setString(2, employee.email());
            statement.setString(3, employee.department());
            statement.setDouble(4, employee.salary());
            statement.setDate(5, Date.valueOf(employee.joiningDate()));

      //executing the insert statement and checking if rows were affected or not:
            int rows = statement.executeUpdate();
      //return true if insert was successful
            return rows > 0;

        } catch (SQLException e) {
            System.out.println(" Failed to add employee! " + e.getMessage());
            return false;
        }
    }


    private String sqlAddQuery(String nameOfTable) {
        return "INSERT INTO " + nameOfTable + " employees (name, email, department, salary, joining_date) VALUES (?, ?, ?, ?, ?)";
    }

}







