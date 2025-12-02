import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {

    // Add new employee to the database:
    public boolean addEmployee(Employee employee) {

        //sql query loaded from separate method for cleaner code and reusability
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
            System.out.println("\nError: " + e.getMessage());
            return false;
        }
    }

    //  View all employees
    public List<Employee> getAllEmployees() {

        // I used List (data str) to store all retrieved employee
        List<Employee> list = new ArrayList<>();
        // this method cleaner code and reusability
        String sql = sqlGetAllQuery("employees");

        // since we used three resources in this method, we have to ensures these resources are closed automatically by separating them by ";"
        try (Connection connection = DBConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet result = statement.executeQuery()) {

            // loop through the result set and convert each row into employee object
            while (result.next()) {
                //mapEmployee() maps ResultSet data into an Employee object
                list.add(mapEmployee(result));
            }
        } catch (SQLException e) {
            System.out.println("Failed to fetch employees: " + e.getMessage());
        }
        return list;
    }

    //search employee by id
    public Employee getEmployeeById(int id) {

        String sql = sqlGetByIdQuery("employees");
        // automatically close the connection and statement
        try (Connection connection = DBConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)){

            statement.setInt(1, id);
            //execute the query and process the result
            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) { //If a matching employee exists convert the row into an employee object
                    return mapEmployee(result);
                }
            }
        } catch (SQLException e) {
            System.out.println("failed to search employee: " + e.getMessage());
        }
        // return null if no employee was found
        return null;
    }


    // update employee
    public boolean updateEmployee(Employee employee) {
        String sql = sqlUpdateQuery("employees");
        try (Connection connection = DBConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, employee.name());
            statement.setString(2, employee.email());
            statement.setString(3, employee.department());
            statement.setDouble(4, employee.salary());
            statement.setInt(5, employee.id());

            return statement.executeUpdate() > 0;

         }catch (SQLException e) {
            System.out.println("failed to update employee: " + e.getMessage());
            return false;
        }
    }

    // delete employee
    public boolean deleteEmployee(int id) {

        String sql = sqlDeleteQuery("employees");
        try (Connection connection = DBConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println(" Failed to delete employee: " + e.getMessage());
            return false;
        }
    }


    // this method convert result set row to employee object
    private Employee mapEmployee(ResultSet rs) throws SQLException {

        return new Employee(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("email"),
                rs.getString("department"),
                rs.getDouble("salary"),
                rs.getDate("joining_date").toLocalDate()
        );
    }


    private String sqlAddQuery(String nameOfTable) {
        return "INSERT INTO " + nameOfTable + " (name, email, department, salary, joining_date) VALUES (?, ?, ?, ?, ?)";
    }

    private String sqlGetAllQuery(String nameOfTable) {
        return "SELECT * FROM " + nameOfTable;
    }

    private String sqlGetByIdQuery(String nameOfTable) {
        return "SELECT * FROM " + nameOfTable + " WHERE id=?";
    }

    private String sqlUpdateQuery(String nameOfTable) {
        return "UPDATE " + nameOfTable + " SET name=?, email=?, department=?, salary=? WHERE id=?";
    }

    private String sqlDeleteQuery(String nameOfTable) {
        return "DELETE FROM " + nameOfTable + " WHERE id=?";
    }


}







