package main.java.package1;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountFromDBGetter {
      /**
     * Method to retrieve account information
     * @param accountNumber retreival
     * @return account
     * @throws SQLException
     */
    public static BankAccount getAccountByAccountNumberFromDB(Integer accountNumber) throws SQLException {
        BankAccount account = null;
        String query = "SELECT account_number, owner_name, balance FROM accounts WHERE account_number = ?";
        try (Connection conn = JDBCConn.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, accountNumber);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    String ownerName = rs.getString("owner_name");
                    BigDecimal balance = rs.getBigDecimal("balance");
                    account = new BankAccount(accountNumber, ownerName, balance);
                }
            }
            return account;
    }

}
