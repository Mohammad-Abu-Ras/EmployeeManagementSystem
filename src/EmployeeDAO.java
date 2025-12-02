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
        try (Connection connection = DBConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            //execute the query and process the result
            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) { //If a matching employee exists convert the row into an employee object
                    return mapEmployee(result);
                }
            }
        } catch (SQLException e) {
            System.out.println("Failed to search employee: " + e.getMessage());
        }
        // return null if no employee was found
        return null;
    }

    // update employee method
    // update existing employee's information in the data base.
    // it returns true if the update was successful, otherwise returns false.
    public boolean updateEmployee(Employee employee) {

        //UPDATE statement dynamically it just take the name of table
        String sql = sqlUpdateQuery("employees");
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            // setting the updated values in the prepared statement
            statement.setString(1, employee.name());
            statement.setString(2, employee.email());
            statement.setString(3, employee.department());
            statement.setDouble(4, employee.salary());
            //id is used in the WHERE clause to update the correct employee
            statement.setInt(5, employee.id());

            //// Execute the update query.
            //if at least one row was affected, the update was successful.
            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("\nError: " + e.getMessage());
            return false; // update failed
        }
    }


    /*
     *  delete an employee from the database by their ID.
     *  returns true if the deletion was successful or false if no rows were affected or an error occurred.
     * */
    // delete employee method:
    public boolean deleteEmployee(int id) {

        String sql = sqlDeleteQuery("employees");
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            // set the employee id in the WHERE clause
            statement.setInt(1, id);
            //execute the DELETE statement.
            //if at least one row was removed, the deletion was successful
            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("\nError: " + e.getMessage());
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

    // dynamic method to returns sql INSERT statement for adding new record into the given table:
    private String sqlAddQuery(String nameOfTable) {
        return "INSERT INTO " + nameOfTable + " (name, email, department, salary, joining_date) VALUES (?, ?, ?, ?, ?)";
    }

    // dynamic method to returns sql SELECT statement that retrieves all rows from the given table.
    private String sqlGetAllQuery(String nameOfTable) {
        return "SELECT * FROM " + nameOfTable;
    }

    // dynamic method to returns sql SELECT statement that retrieves single row by ID.
    private String sqlGetByIdQuery(String nameOfTable) {
        return "SELECT * FROM " + nameOfTable + " WHERE id=?";
    }

    //this method returns sql UPDATE statement to modify an existing row identified by id
    //only the name and email and department and salary fields are updated
    // note: we dont need update id or joining date
    private String sqlUpdateQuery(String nameOfTable) {
        return "UPDATE " + nameOfTable + " SET name=?, email=?, department=?, salary=? WHERE id=?";
    }

    // dynamic method returns sql DELETE statement to remove a row identified by id
    private String sqlDeleteQuery(String nameOfTable) {
        return "DELETE FROM " + nameOfTable + " WHERE id=?";
    }

}







