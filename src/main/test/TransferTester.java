package main.test;

import java.math.BigDecimal;
import java.sql.SQLException;

import main.java.package1.AccountFromDBGetter;
import main.java.package1.BankAccount;
import main.java.package1.Transferer;

public class TransferTester {

     public static void papaMilaTransferTest() {
        try {

            // Fetch the test accounts from the database
            BankAccount papa = AccountFromDBGetter.getAccountByAccountNumberFromDB(1003);
            BankAccount mila = AccountFromDBGetter.getAccountByAccountNumberFromDB(1004);
    
            // Test  withdraw
            testTransfer(papa, mila, new BigDecimal("425.00"));
            System.out.println("425 should be deducted from Papa's account and added to Mila's account.");;
           
            // Fetch updated account balances
            papa = AccountFromDBGetter.getAccountByAccountNumberFromDB(1003);
            mila = AccountFromDBGetter.getAccountByAccountNumberFromDB(1004);
            // Test getBalance for John's and Jane's accounts
            testGetBalance(papa);
            testGetBalance(mila);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

       /**
     * Method for testing Transfer
     * @param senderAccount
     * @param receiverAccount
     * @param amount
     */
    public static void testTransfer(BankAccount senderAccount, BankAccount receiverAccount, BigDecimal amount) {
        System.out.println("Testing transfer...");
        System.out.println("Sender initial balance: " + senderAccount.getBalance());
        System.out.println("Receiver initial balance: " + receiverAccount.getBalance());
    
        Transferer.transfer(senderAccount, receiverAccount.getAccountNumber(), amount); 
    
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
