package main.java.package1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AccountBalanceUpdater {
     /**
     * Method to update account Balance
     * @throws SQLException
     */
    public static void updateAccountBalance(BankAccount bankAccount) throws SQLException {
        String query = "UPDATE accounts SET balance = ? WHERE account_number = ?";
        try (Connection conn = JDBCConn.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setBigDecimal(1, bankAccount.getBalance());
                stmt.setInt(2, bankAccount.getAccountNumber());

                int rowsUpdated = stmt.executeUpdate();
                System.out.println("Rows updated: " + rowsUpdated); // Add this line for debugging
        }
    }
}
