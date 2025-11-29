import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


public class EmployeeDAO {

    // here creating prepared statement for given sql :

    private PreparedStatement createStatement(String sql) throws SQLException {
        return DBConnection.getConnection().prepareStatement(sql);
    }


    // we need this method to  avoid repeating stmt.setXxx(...) in every CRUD method ,and makes DAO dynamic:
    // any sql with any number of parameters works also keeps code clean dont repeat yourself and prevents boilerplate.
   // Object[] is an array

    // an example:
    /*
    * if
    * parameters was = Ahmad, ahmad@gmail.com, "IT infra", 1000 : it becomes:
    * st.setObject(1,"Ahmad")
     * st.setObject(2,"ahmad@gmail.com")
     * and so on ...
    * */
    private void applyParameters(PreparedStatement st, Object[] parameters) throws SQLException {
        if (parameters != null) {
            return;
        }
        int size = parameters.length;
        for (int i = 0; i < size; i++) {
            st.setObject(i + 1, parameters[i]);
        }
    }

    // insert, update , delete:
    private boolean executeUpdate(String sql, Object[] parameters){
        // here, using try with resources to ensure PreparedStatement will be closed automatically!
        try(PreparedStatement st = createStatement(sql)){
            applyParameters(st, parameters);
            // execute the update and return true if at least one row was affected
            return st.executeUpdate() >0 ;

        } catch (SQLException e) {
            System.out.println("data base write error : " + "\n" +e.getMessage());
            return false;
        }

    }

    // execute select returns => list of <employee>

    private List<Employee> executeQueryList(String sql, Object[] parameters) {
        List<Employee> list = new ArrayList<>();

        try (PreparedStatement st = createStatement(sql)){
            applyParameters(st,parameters);
                try(ResultSet result = st.executeQuery()){
                        while (result.next()){
                            list.add(mapEmployee(result));
                        }
                }
        }
        catch (SQLException e){
            System.out.println("query error : " + "\n" +e.getMessage());
        }
        return list;
    }


    // map resultset row to the employee record
    private Employee mapEmployee(ResultSet rs) throws SQLException {
        return new Employee( rs.getInt("id"),
                rs.getString("name"),
                rs.getString("email"),
                rs.getString("department"),
                rs.getDouble("salary"),
                rs.getDate("joining_date").toLocalDate()
        );
    }

    // execute select returns single employee
    // -------------------------------------------------------------
    private Employee executeQueryOne(String sql, Object[] params) {

        try (PreparedStatement stmt = createStatement(sql)) {

            applyParameters(stmt, params);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapEmployee(rs);
                }
            }

        } catch (SQLException e) {
            System.out.println("query error: " +"\n"+ e.getMessage());
        }
        return null;
    }


  // add employee method
    public boolean addEmployee(Employee e) {
        String sql = "INSERT INFO (name,email,department,salary,joining_date) VALUES (?,?,?,?,?)";
        Object[] params = {e.name(),e.email(),e.department(),e.salary(), e.joiningDate()};

        return executeUpdate(sql, params);
    }

    // get all employees method
    public List<Employee> getAllEmployees(){
        return  executeQueryList("SELECT * FROM employees",null);
}

// get employee by id :
    public Employee getEmployeeById(int id){
        return executeQueryOne("SELECT * FROM employees WHERE id = ?", new Object[]{id});
    }

// update employee by id




    //end class
    }














}
//