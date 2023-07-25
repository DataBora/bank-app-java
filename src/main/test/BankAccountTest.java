package main.test;
import main.java.package1.BankAccount;

import java.math.BigDecimal;
import java.sql.SQLException;

public class BankAccountTest {

    public static void main(String[] args) {
        // Test deposit, withdraw, and transfer operations
        testBankAccountOperations();
    }

    public static void testBankAccountOperations() {
        // Create a test BankAccount
        try {
            // Fetch the test accounts from the database
            BankAccount john = new BankAccount("1234567890");
            BankAccount jane = new BankAccount("9876543210");

            // Test deposit
            testDeposit(john, new BigDecimal("2500.50"));

            // Test withdraw
            testWithdraw(john, new BigDecimal("300.25"));

            // Test transfer
            testTransfer(john, jane, new BigDecimal("500.00"));

            // Test getBalance
            testGetBalance(john);
            testGetBalance(jane);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    

    public static void testDeposit(BankAccount account, BigDecimal amount) {
        System.out.println("Testing deposit...");
        System.out.println("Initial balance: " + account.getBalance());
        account.deposit(amount);
        System.out.println("After deposit: " + account.getBalance());
        System.out.println();
    }

    public static void testWithdraw(BankAccount account, BigDecimal amount) {
        System.out.println("Testing withdraw...");
        System.out.println("Initial balance: " + account.getBalance());
        account.withdraw(amount);
        System.out.println("After withdraw: " + account.getBalance());
        System.out.println();
    }

    public static void testTransfer(BankAccount senderAccount, BankAccount receiverAccount, BigDecimal amount) {
        System.out.println("Testing transfer...");
        System.out.println("Sender initial balance: " + senderAccount.getBalance());
        System.out.println("Receiver initial balance: " + receiverAccount.getBalance());

        senderAccount.transfer(receiverAccount.getAccountNumber(), amount);

        System.out.println("After transfer - Sender balance: " + senderAccount.getBalance());
        System.out.println("After transfer - Receiver balance: " + receiverAccount.getBalance());
        System.out.println();
    }

    public static void testGetBalance(BankAccount account) {
        // Test getBalance
        System.out.println("Testing getBalance...");
        System.out.println("Current balance: " + account.getBalance());
        System.out.println();
    }
}
