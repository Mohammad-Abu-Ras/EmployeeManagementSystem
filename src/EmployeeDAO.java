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

    private void applyParameter(PreparedStatement st, Object[] parameters) throws SQLException {
        if (parameters != null) {
            return;
        }
        int size = parameters.length;
        for (int i = 0; i < size; i++) {
            st.setObject(i + 1, parameters[i]);
        }
    }


}
//