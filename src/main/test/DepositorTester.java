package main.test;

import java.math.BigDecimal;
import java.sql.SQLException;

import main.java.package1.AccountFromDBGetter;
import main.java.package1.BankAccount;

import main.java.package1.Depositor;

public class DepositorTester {
   
     public static void  johnDepositTest() {
        try {
            // Fetch Jane's account from the database
            BankAccount john = AccountFromDBGetter.getAccountByAccountNumberFromDB(1001);
            // Test deposit
            testDeposit(john, new BigDecimal("250.00"));
            System.out.println("It should add 250 to John's account.");


            john = AccountFromDBGetter.getAccountByAccountNumberFromDB(1001);
            // Test getBalance
            testGetBalance(john);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

       /**
     * Method for testing Deposit
     * @param account
     * @param amount
     */
    public static void testDeposit(BankAccount account, BigDecimal amount) {
        System.out.println("Testing deposit...");
        System.out.println("Initial balance: " + account.getBalance());

        Depositor.deposit(account, amount);
    }

     /**
     * Method for testing Balance retrival
     * @param account
     */
    public static void testGetBalance(BankAccount account) {
        // Test getBalance
        System.out.println("Testing getBalance...");
        System.out.println("Current balance: " + account.getBalance());
        System.out.println();
    }

}
