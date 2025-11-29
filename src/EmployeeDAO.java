import java.sql.SQLException;
import java.time.LocalDate;
import java.sql.PreparedStatement;

public class EmployeeDAO {

    // here creating prepared statement for given sql :

    private PreparedStatement creatStatement(String sql) throws SQLException {
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
        try(PreparedStatement st = creatStatement(sql)){
            applyParameters(st, parameters);
            // execute the update and return true if at least one row was affected
            return st.executeUpdate() >0 ;

        } catch (SQLException e) {
            System.out.println("data base write error : " + "\n" +e.getMessage());
            return false;
        }

    }



}
//