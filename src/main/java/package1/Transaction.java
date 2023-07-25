package main.java.package1;

import java.math.BigDecimal;
import java.sql.Timestamp;


public class Transaction {
    private Timestamp transactionDate;
    private String senderAccountNumber;
    private String receiverAccountNumber;
    private BigDecimal amount;

    public Transaction(Timestamp transactionDate, String senderAccountNumber, String receiverAccountNumber,BigDecimal amount){

        this.transactionDate = transactionDate;
        this.senderAccountNumber = senderAccountNumber;
        this.receiverAccountNumber = receiverAccountNumber;
        this.amount = amount;
    }

    // Getter methods for the fields...
    public Timestamp getTransactionDate(){
        return transactionDate;
    }

    public String getSenderAccountNumber(){
        return senderAccountNumber;
    }

    public String getReceiverAccountNumber(){
        return receiverAccountNumber;
    }

    public BigDecimal getAmount(){
        return amount;
    }
}
