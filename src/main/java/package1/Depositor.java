package main.java.package1;

import java.math.BigDecimal;
import java.sql.SQLException;

public class Depositor {
    /**
     * Deposit method, which also writes to transaction table
     * @param bankAccount to deposit to
     * @param amount od deposit
     */
    public static void deposit(BankAccount bankAccount, BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount to deposit must be greater than zero.");
        }

        BigDecimal balance = bankAccount.getBalance();
        balance = balance.add(amount);
        try {
           
            bankAccount.setBalance(balance); // Update the balance of the BankAccount instance
            AccountBalanceUpdater.updateAccountBalance(bankAccount); // Update the account balance in the database

            TransactionSaver.saveDepositTransaction(bankAccount, amount);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
