package main.java.package1;

import java.math.BigDecimal;
import java.sql.SQLException;

public class Transferer {
        
    /**
     * Transfer method which also writes to transaction table
     * @param senderAccount which wires the money
     * @param receiverAccountNumber which receives the money
     * @param amount of transfer
     */
    public static void transfer(BankAccount senderAccount, Integer receiverAccountNumber, BigDecimal amount){

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount to transfer must be greater than zero.");
        }

        BigDecimal senderBalance = senderAccount.getBalance();
        BankAccount receiverAccount = null;

        try {
            receiverAccount = AccountFromDBGetter.getAccountByAccountNumberFromDB(receiverAccountNumber);

            if (receiverAccount == null) {
                throw new IllegalArgumentException("Receiving Account not found.");
            }

            if (senderBalance.compareTo(amount) < 0) {
                throw new IllegalArgumentException("Insufficient balance for the transfer");
            }

            try {

                senderBalance = senderBalance.subtract(amount);
                BigDecimal receiverBalance = receiverAccount.getBalance().add(amount);
                
                senderAccount.setBalance(senderBalance); // Update sender's balance
                receiverAccount.setBalance(receiverBalance); // Update receiver's balance

                AccountBalanceUpdater.updateAccountBalance(senderAccount);
                AccountBalanceUpdater.updateAccountBalance(receiverAccount);
               
                //Log transaction to database
                TransactionSaver.saveTransferTransaction(senderAccount, receiverAccount, amount);
                

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
