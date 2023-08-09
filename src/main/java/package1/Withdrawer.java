package main.java.package1;

import java.math.BigDecimal;
import java.sql.SQLException;

public class Withdrawer {
    /**
     * Withdraw Method, which also writes to transaction table
     * @param bankAccount from which money is withdrawn
     * @param amount of withdrawal
     */
    public static void withdraw(BankAccount bankAccount, BigDecimal amount) {

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
        throw new IllegalArgumentException("Amount to withdraw must be greater than zero.");
    }
        BigDecimal balance = bankAccount.getBalance();

        if (balance.compareTo(amount) < 0) {
            throw new IllegalArgumentException("Insufficient balance for the withdrawal.");
    }

        balance = balance.subtract(amount);

        try {
          
            bankAccount.setBalance(balance);  // Update the balance of the BankAccount instance
            AccountBalanceUpdater.updateAccountBalance(bankAccount); // Update the account balance in the database

            TransactionSaver.saveWithdrawTransaction(bankAccount, amount);

        } catch (SQLException e) {
            e.printStackTrace();
    }
}
}
