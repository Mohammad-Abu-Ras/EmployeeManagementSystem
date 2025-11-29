import java.sql.SQLException;
import java.time.LocalDate;
import java.sql.PreparedStatement;

public class EmployeeDAO {

    // here creating prepared statement for given sql:

    private PreparedStatement creatStatement (String sql) throws SQLException{
        return DBConnection.getConnection().prepareStatement(sql);
    }

}
//