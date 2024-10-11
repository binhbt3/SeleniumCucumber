package helpers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseHelpers {
    public DataBaseHelpers() {
        super();
    }

    public static Connection getMySQLConnection(String hostName, String dbName, String userName, String password) throws SQLException, SQLException {

        // Ví dụ: jdbc:mysql://localhost:3306/saleserp
        String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName;

        Connection conn = DriverManager.getConnection(connectionURL, userName, password);

        return conn;
    }

//    hostName = "mysql.app.dev.eu-central-1";
//    dbName = "FDB-API dev";
//    userName = "root";
//    password= ""
//    port="3306";
}
