package main.java.package1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConn {
    /**
     * JDBC connection
     */
    private static final String JDBC_URL =  "jdbc:mysql://localhost:3306/bank_app";
    private static final String JDBC_USER = "databora";
    private static final String JDBC_PASSWORD = "!Djavolak1";

    
    public static String getJdbcUrl() {
        return JDBC_URL;
    }
    public static String getJdbcUser() {
        return JDBC_USER;
    }
    public static String getJdbcPassword() {
        return JDBC_PASSWORD;
    }


       /**
     * Method to establish database connection
     * @return connection
     * @throws SQLException 
     */
    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(JDBCConn.getJdbcUrl(), JDBCConn.getJdbcUser(), JDBCConn.getJdbcPassword());
    }


}
