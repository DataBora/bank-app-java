package main.java.statistics;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import main.java.package1.BankAccount;
import main.java.package1.JDBCConn;
import main.java.package1.Transaction;

public class ImportData {

     public static List<BankAccount> importAccountsFromDatabase() {

        List<BankAccount> accounts = new ArrayList<>();

        try (Connection conn = JDBCConn.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT account_number, owner_name, balance FROM accounts");
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Integer accountNumber = rs.getInt("account_number");
                String ownerName = rs.getString("owner_name");
                BigDecimal balance = rs.getBigDecimal("balance");

                BankAccount account = new BankAccount(accountNumber, ownerName, balance);
                accounts.add(account);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return accounts;
    
}


    public static List<Transaction> importTransactionsFromDatabase(){
         List<Transaction> transactions = new ArrayList<>();

         try(Connection conn = JDBCConn.getConnection();
                PreparedStatement stmt = conn.prepareStatement("SELECT transaction_date, sender_account_number, receiver_account_number, amount FROM transactions");
                ResultSet rs = stmt.executeQuery()){


                while(rs.next()){
                    Timestamp transactionDate = rs.getTimestamp("transaction_date");
                    Integer senderAccountNumber = rs.getInt("sender_account_number");
                    Integer receiverAccountNumber = rs. getInt("receiver_account_number");
                    BigDecimal amount = rs.getBigDecimal("amount");

                    Transaction transaction = new Transaction(transactionDate, senderAccountNumber, receiverAccountNumber, amount);
                    transactions.add(transaction);
                }
            } catch (SQLException e){
                e.printStackTrace();
            }
            return transactions;
    }
}
