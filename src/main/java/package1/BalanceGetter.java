package main.java.package1;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BalanceGetter {
    /**
     * Get balance method for retriving balance info
     * @param bankAccount
     * @return balance
     * Return a default value if there is an error or account not found.
     */
    public BigDecimal getBalance(BankAccount bankAccount) {
        try (Connection conn = JDBCConn.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT balance FROM accounts WHERE account_number = ?");
        ) {
            stmt.setInt(1, bankAccount.getAccountNumber());
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getBigDecimal("balance");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return BigDecimal.ZERO; // Return a default value if there is an error or account not found.
    }
}
