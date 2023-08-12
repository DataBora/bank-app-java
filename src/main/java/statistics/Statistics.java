package main.java.statistics;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.time.LocalDate;

import main.java.package1.BankAccount;
import main.java.package1.Transaction;

public class Statistics implements Methods {

    private List<BankAccount> accounts;
    private List<Transaction> transactions;

    public Statistics(List<BankAccount> accounts, List<Transaction> transactions) {
        this.accounts = accounts;
        this.transactions = transactions;
    }
    
//////////////------------------------------------------------------------/////////////////////

    //this method calculated Average
    @Override
    public double average(List<BankAccount> accounts) {
        double sum = 0;
        int count = 0;

        for (BankAccount account : accounts) {
            sum += account.getBalance().doubleValue();
            count++;
        }
        return sum / count;
      
    }

//////////////------------------------------------------------------------/////////////////////

    //This method return number of accounts
    @Override
    public int count(List<BankAccount> accounts) {
        return accounts.size();
    }

//////////////------------------------------------------------------------/////////////////////

    //This method returns number of transactions per Day
    @Override
    public int dailyTransactionCount(List<Transaction> transactions) {

        Map<LocalDate, Integer> dailyTransactionCountMap = new HashMap<>();

         // Iterate through the transactions and update the map
         for (Transaction transaction : transactions) {
            LocalDate date = transaction.getTransactionDate().toLocalDateTime().toLocalDate();
            dailyTransactionCountMap.put(date, dailyTransactionCountMap.getOrDefault(date, 0) + 1);
        }
        
         // Calculate the maximum count of transactions for a single day
         int maxDailyCount = dailyTransactionCountMap.values().stream().mapToInt(Integer::intValue).max().orElse(0);

         return maxDailyCount;
    }
//////////////------------------------------------------------------------/////////////////////

    //this method is sorting Balances in descending order
    @Override
    public List<BankAccount> sortByName(List<BankAccount> accounts) {
        List<BankAccount> sortedAccounts = new ArrayList<>(accounts);
        sortedAccounts.sort(createNameComparator());
        return sortedAccounts;
    }

//////////////------------------------------------------------------------/////////////////////

    //this method is sorting Names in natural order order
    @Override
    public List<BankAccount> sortByBalance(List<BankAccount> accounts) {
        List<BankAccount> sortedAccounts = new ArrayList<>(accounts);
        sortedAccounts.sort(createBalanceComparator());
        return sortedAccounts;
    }
//////////////------------------------------------------------------------/////////////////////  

    //this methos is for finding anomalies in bank accounts
    private static final double IQR_MULTIPLIER = 1.5;

    @Override
    public boolean anomaly(double balance) {
        double[] balances = accounts.stream()
                .mapToDouble(account -> account.getBalance().doubleValue())
                .toArray();

        double q1 = calculatePercentile(balances, 25);
        double q3 = calculatePercentile(balances, 75);
        double iqr = q3 - q1;

        double lowerBound = q1 - IQR_MULTIPLIER * iqr;
        double upperBound = q3 + IQR_MULTIPLIER * iqr;

        return balance < lowerBound || balance > upperBound;
    }

    private double calculatePercentile(double[] data, int percentile) {
        Arrays.sort(data);
        double index = (percentile / 100.0) * (data.length - 1);
        int lowerIndex = (int) Math.floor(index);
        int upperIndex = (int) Math.ceil(index);

        if (lowerIndex == upperIndex) {
            return data[lowerIndex];
        }

        double lowerValue = data[lowerIndex];
        double upperValue = data[upperIndex];
        return lowerValue + (upperValue - lowerValue) * (index - lowerIndex);
    }

    //////////////------------------------------------------------------------/////////////////////  

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
    
        sb.append("Average balance: ").append(average(accounts)).append("\n");
        sb.append("Number of accounts: ").append(count(accounts)).append("\n");
        sb.append("Max daily transactions: ").append(dailyTransactionCount(transactions)).append("\n");
        sb.append("Sorted by name: \n");
        for (BankAccount account : sortByName(accounts)) {
            sb.append(account).append("\n");
        }
        sb.append("Sorted by balance: \n");
        for (BankAccount account : sortByBalance(accounts)) {
            sb.append(account).append("\n");
        }
    
        sb.append("Anomalies:\n");
        for (BankAccount account : accounts) {
            boolean isAnomaly = anomaly(account.getBalance().doubleValue());
            sb.append("Account ").append(account.getAccountNumber())
              .append(": ").append(isAnomaly ? "Anomaly" : "Normal")
              .append("\n");
        }
    
        return sb.toString();
    }

    

}
