package main.java.statistics;

import java.util.List;
import java.util.Comparator;

import main.java.package1.BankAccount;
import main.java.package1.Transaction;

public interface Methods {
     //Calculationg Average accounts balance
    double average(List<BankAccount> accounts);

    //calculates number of accounts
    int count(List<BankAccount> accounts);

    //calculates daily transaction number
    int dailyTransactionCount(List<Transaction> transactions);

    //sorting accounts by name
   List<BankAccount> sortByName(List<BankAccount> accounts);

    //sorting accounts by Balance Descending
   List<BankAccount> sortByBalance(List<BankAccount> accounts);


    //Anomaly detection per percentile
    boolean anomaly(double balance);

    //Making my custom Comparator for comparing balances and sorting them in descending order
    default Comparator<BankAccount> createBalanceComparator() {
        return (account1, account2) -> account2.getBalance().compareTo(account1.getBalance());
    }

    //Making my custom comparator for comparing names ans sorting them in natural order
    default Comparator<BankAccount> createNameComparator() {
        return Comparator.comparing(BankAccount::getOwnerName);
    }

    
}
