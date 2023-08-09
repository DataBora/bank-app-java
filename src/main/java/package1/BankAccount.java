package main.java.package1;

import java.math.BigDecimal;
import java.sql.SQLException;

public class BankAccount {

//-------------------------Variables------------------------\\
    private Integer accountNumber;
    private String ownerName;
    private BigDecimal balance;

//-------------------------Constructors------------------------\\

    public BankAccount(Integer accountNumber, String ownerName, BigDecimal initialBalance) {
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        this.balance = initialBalance;
        try {
            AccountBalanceUpdater.updateAccountBalance(this);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public BankAccount(Integer accountNumber) throws SQLException {
        this.accountNumber = accountNumber;
        // Pass the current BankAccount instance
        AccountDetailsFetcher.fetchAccountDetails(this);
    }

//-------------------------Setters and Getters------------------------\\

    public Integer getAccountNumber(){
        return accountNumber;
    }

    public void setAccountNumber(Integer accountNumber){
        this.accountNumber = accountNumber;
    }
    
    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance){
        this.balance = balance;
    }

   //-------------------------toString Method------------------------\\

   @Override
   public String toString() {
       return "BankAccount{" +
               "accountNumber='" + accountNumber + '\'' +
               ", ownerName='" + ownerName + '\'' +
               ", balance=" + balance +
               '}';
   }
}
