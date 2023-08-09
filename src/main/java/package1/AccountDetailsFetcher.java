package main.java.package1;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDetailsFetcher {
       /**
     * Method to fetch account details from database
     * @throws SQLException
     */
    public static void fetchAccountDetails(BankAccount bankAccount) throws SQLException{
        String query = "SELECT owner_name, balance FROM accounts WHERE account_number = ?";
        try (Connection conn = JDBCConn.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setInt(1, bankAccount.getAccountNumber());
            ResultSet rs = stmt.executeQuery();

            if (rs.next()){
                String ownerName = rs.getString("owner_name");
                BigDecimal balance = rs.getBigDecimal("balance");

                bankAccount.setOwnerName(ownerName);
                bankAccount.setBalance(balance);
            }
        }
    }
}
