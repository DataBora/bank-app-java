package main.java.package1;

import java.math.BigDecimal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.time.LocalDateTime;
import java.sql.Timestamp;


public class BankAccount {
    private String accountNumber;
    private BigDecimal balance;

    //JDB connection
    private static final String JDBC_URL =  "jdbc:mysql://localhost:3306/bank_app";
    private static final String JDBC_USER = "databora";
    private static final String JDBC_PASSWORD = "!Djavolak1";

    //Konstruktori
    public BankAccount(){
        accountNumber = "";
        balance = BigDecimal.ZERO;
    }

    public BankAccount(BigDecimal initialBalance, String accountNumber){
        balance = initialBalance;
        this.accountNumber = accountNumber;
    }

    //getter for account number
    public String getAccountNumber(){
        return accountNumber;
    }
    //setter for account number
    public void setAccountNumber(String accountNumber){
        this.accountNumber = accountNumber;
    }

    //construktor for fetching account from database
    public BankAccount(String accountNumber) throws SQLException{
        this.accountNumber = accountNumber;
        fetchAccountDetails();
    }

    //helper method to establish database connection
    private static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
    }

    //helper method to fetsh account details from database
    private void fetchAccountDetails() throws SQLException{
        String query = "SELECT balance FROM accounts WHERE account_number = ?";
        try (Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setString(1, accountNumber);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()){
                balance = rs.getBigDecimal("balance");
            }
        }
    }
    //////////////////////////////////////////////////////////////////////////////
    //Methods for CRUD operation

    public void updateAccountBalance() throws SQLException {
        String query = "UPDATE accounts SET balance = ? WHERE account_number = ?";
        try (Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setBigDecimal(1, balance);
                stmt.setString(2, accountNumber);
                stmt.executeUpdate();
        }
    }

    // Method to save a transaction to the database

    public static void saveTransaction(Transaction transaction) throws SQLException {
        String query = "INSERT INTO transactions (transaction_date, sender_account_number, receiver_account_number, amount) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setTimestamp(1, transaction.getTransactionDate());
                stmt.setString(2, transaction.getSenderAccountNumber());
                stmt.setString(3, transaction.getReceiverAccountNumber());
                stmt.setBigDecimal(4,transaction.getAmount());
                stmt.executeUpdate();
            }
    }

    ///////////////////////////////////////////////////////////////////////////////////
    //Method to retrieve account information
    private static BankAccount getAccountByAccountNumberFromDB(String accountNumber) throws SQLException {
        BankAccount account = null;
        String query = "SELECT account_number, balance FROM accounts WHERE account_number = ?";
        try (Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, accountNumber);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    String receivedAccountNumber = rs.getString("account_number");
                    BigDecimal balance = rs.getBigDecimal("balance");
                    account = new BankAccount(balance, receivedAccountNumber);
                }
            }
            return account;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    //--METHODS--//

    //Deposit method
// Deposit Method
    public void deposit(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount to deposit must be greater than zero.");
        }

        balance = balance.add(amount);
        try {
            updateAccountBalance(); // Update the account balance in the database

            // Record the deposit transaction in the transactions table
            Transaction depositTransaction = new Transaction(Timestamp.valueOf(LocalDateTime.now()), accountNumber, accountNumber, amount);
            saveTransaction(depositTransaction);

        } catch (SQLException e) {
            e.printStackTrace();
        // Optionally, you can handle the exception or throw a custom exception for deposit failure.
        // For example:
        // throw new RuntimeException("Failed to update account balance during deposit.", e);
        }
    }
    
// Withdraw Method
    public void withdraw(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
        throw new IllegalArgumentException("Amount to withdraw must be greater than zero.");
    }

        if (balance.compareTo(amount) < 0) {
            throw new IllegalArgumentException("Insufficient balance for the withdrawal.");
    }

    balance = balance.subtract(amount);
        try {
            updateAccountBalance(); // Update the account balance in the database

             // Record the withdraw transaction in the transactions table
            Transaction withdrawTransaction = new Transaction(Timestamp.valueOf(LocalDateTime.now()), accountNumber, accountNumber, amount.negate());
            saveTransaction(withdrawTransaction);

        } catch (SQLException e) {
            e.printStackTrace();
        // Optionally, you can handle the exception or throw a custom exception for withdrawal failure.
        // For example:
        // throw new RuntimeException("Failed to update account balance during withdrawal.", e);
    }
}

    public BigDecimal getBalance() {
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT balance FROM accounts WHERE account_number = ?");
        ) {
            stmt.setString(1, accountNumber);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getBigDecimal("balance");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return BigDecimal.ZERO; // Return a default value if there is an error or account not found.
    }


    
    //Transfer method
    public void transfer(String receivingAccountNumber, BigDecimal amount){
        if(amount.compareTo(BigDecimal.ZERO) <= 0){
            throw new IllegalArgumentException("Amount to transfer must be grater then ZERO!");
        }
        if (balance.compareTo(amount) < 0 ){
            throw new IllegalArgumentException("Insufficient balance for the transfer");
        }

        try {
            //Fetsh receiving account from the database
            BankAccount receivingAccount = getAccountByAccountNumberFromDB(receivingAccountNumber);

            if (receivingAccount == null){
                throw new IllegalArgumentException("Receiving Account not found.");
            }

            balance = balance.subtract(amount);
            receivingAccount.balance = receivingAccount.balance.add(amount);

            // Update the account balances in the database
            updateAccountBalance();
            receivingAccount.updateAccountBalance();

            LocalDateTime localDateTime = LocalDateTime.now();
            Timestamp timestamp = Timestamp.valueOf(localDateTime);

             //Lof the transaction in the database
            Transaction transaction = new Transaction(timestamp, getAccountNumber(), receivingAccountNumber, amount);
            System.out.println("Saving transaction: " + transaction); // Debug statement
            saveTransaction(transaction);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
