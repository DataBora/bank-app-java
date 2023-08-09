package main.test;

import java.math.BigDecimal;
import java.sql.SQLException;

import main.java.package1.AccountFromDBGetter;
import main.java.package1.BankAccount;
import main.java.package1.Withdrawer;

public class WithdrawerTester {

     public static void janeWithdrawTest() {
        try {
            // Fetch the test accounts from the database
            BankAccount jane = AccountFromDBGetter.getAccountByAccountNumberFromDB(1002);
            // Test  withdraw
            testWithdraw(jane, new BigDecimal("200.00"));
            System.out.println("It should deduct 200 from Jane's account.");

            jane = AccountFromDBGetter.getAccountByAccountNumberFromDB(1002);
            // Test getBalance for John's and Jane's accounts
            testGetBalance(jane);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

     /**
     * Method for testing Withdrawal
     * @param account
     * @param amount
     */
    public static void testWithdraw(BankAccount account, BigDecimal amount) {
        System.out.println("Testing withdraw...");
        System.out.println("Initial balance: " + account.getBalance());

        Withdrawer.withdraw(account, amount);
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
