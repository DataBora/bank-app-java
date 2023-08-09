package main.java.package1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class TransactionSaver {
    /**
     * Method to save a transaction to the database
     * @param transaction from sender to receiver
     * @throws SQLException
     */
    public static void saveTransaction(Transaction transaction) throws SQLException {
        String query = "INSERT INTO transactions (transaction_date, sender_account_number, receiver_account_number, amount) VALUES (?, ?, ?, ?)";
        try (Connection conn = JDBCConn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setTimestamp(1, transaction.getTransactionDate());
            stmt.setInt(2, transaction.getSenderAccountNumber());
            stmt.setInt(3, transaction.getReceiverAccountNumber());
            stmt.setBigDecimal(4, transaction.getAmount());
            stmt.executeUpdate();
        }
    }

    /**
     * Transaction saver for Deposit method
     * @param account
     * @param amount
     * @throws SQLException
     */
    public static void saveDepositTransaction(BankAccount account, BigDecimal amount) throws SQLException {
        Transaction depositTransaction = new Transaction(Timestamp.valueOf(LocalDateTime.now()), account.getAccountNumber(), account.getAccountNumber(), amount);
        saveTransaction(depositTransaction);
    }

    /**
     * Transaction saver for Withdraw method
     * @param account
     * @param amount
     * @throws SQLException
     */
    public static void saveWithdrawTransaction(BankAccount account, BigDecimal amount) throws SQLException {
        Transaction withdrawTransaction = new Transaction(Timestamp.valueOf(LocalDateTime.now()), account.getAccountNumber(), account.getAccountNumber(), amount.negate());
        saveTransaction(withdrawTransaction);
    }

    /**
     * Transacion asver for Transfer method
     * @param senderAccount
     * @param receiverAccount
     * @param amount
     * @throws SQLException
     */
    public static void saveTransferTransaction(BankAccount senderAccount, BankAccount receiverAccount, BigDecimal amount) throws SQLException {
        LocalDateTime localDateTime = LocalDateTime.now();
        Timestamp timestamp = Timestamp.valueOf(localDateTime);

        Transaction transaction = new Transaction(timestamp, senderAccount.getAccountNumber(), receiverAccount.getAccountNumber(), amount);
        saveTransaction(transaction);
    }
}
