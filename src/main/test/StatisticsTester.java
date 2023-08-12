package main.test;

import java.util.ArrayList;
import java.util.List;

import main.java.package1.BankAccount;
import main.java.package1.Transaction;
import main.java.statistics.ImportData;
import main.java.statistics.Statistics;

public class StatisticsTester {
    public static void main(String[] args) {
        List<BankAccount> accounts = ImportData.importAccountsFromDatabase();
        List<Transaction> transactions = ImportData.importTransactionsFromDatabase();

        // Now you have the list of BankAccount instances from the database
        for (BankAccount account : accounts) {
            System.out.println(account);
        }

        System.out.println("\n");

          // Create a Statistics object
          Statistics statistics = new Statistics(new ArrayList<>(accounts), transactions);

               // Test the methods
               testAverage(statistics, accounts);
               testCount(statistics, accounts);
               testDailyTransactionCount(statistics, transactions);
               testSortByName(statistics, accounts);
               testSortByBalance(statistics, accounts);
               testAnomaly(statistics, accounts);

               // Print the final statistics
            System.out.println("Final Statistics:\n" + statistics);
    }


    private static void testAverage(Statistics statistics, List<BankAccount> accounts) {
        double avgBalance = statistics.average(accounts);
        // System.out.println("Average balance: " + avgBalance);
    }

    private static void testCount(Statistics statistics, List<BankAccount> accounts) {
        int accountCount = statistics.count(accounts);
        // System.out.println("Number of accounts: " + accountCount);
    }

    private static void testDailyTransactionCount(Statistics statistics, List<Transaction> transactions) {
        int maxDailyTransactions = statistics.dailyTransactionCount(transactions);
        // System.out.println("Max daily transactions: " + maxDailyTransactions);
    }

    private static void testSortByName(Statistics statistics, List<BankAccount> accounts) {
        List<BankAccount> sortedByName = statistics.sortByName(accounts);
        // System.out.println("Sorted by name: " + sortedByName);
    }

    private static void testSortByBalance(Statistics statistics, List<BankAccount> accounts) {
        List<BankAccount> sortedByBalance = statistics.sortByBalance(accounts);
        // System.out.println("Sorted by balance: " + sortedByBalance);
    }

    private static void testAnomaly(Statistics statistics, List<BankAccount> accounts) {
        for (BankAccount account : accounts) {
            boolean isAnomaly = statistics.anomaly(account.getBalance().doubleValue());
            // System.out.println("Anomaly for account " + account.getAccountNumber() + ": " + isAnomaly);
        }
    }
}
