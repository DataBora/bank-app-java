package main.java.package1;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class DBAccountInserter {
    /**
     * Method for inserting account into daatabease which uses generateAccountNumber to auto-increment acc.nums.
     * @param ownerName
     * @param initialBalance
     * @throws SQLException
     */
    public static void insertAccount(String ownerName, BigDecimal initialBalance) throws SQLException {

        int accountNumber = AccounNumberGenerator.generateAccountNumber();
        String query = "INSERT INTO accounts (account_number, owner_name, balance) VALUES (?, ?, ?)";
        
        try (Connection conn = JDBCConn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setInt(1, accountNumber);
            stmt.setString(2, ownerName);
            stmt.setBigDecimal(3, initialBalance);
            
            stmt.executeUpdate();

            System.out.println("Inserted account:");
            System.out.println("Account Number: " + accountNumber);
            System.out.println("Owner Name: " + ownerName);
            System.out.println("Initial Balance: " + initialBalance);
            System.out.println();
        }
    }
}
