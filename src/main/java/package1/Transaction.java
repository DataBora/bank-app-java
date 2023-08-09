package main.java.package1;

import java.math.BigDecimal;
import java.sql.Timestamp;


public class Transaction {

    /**
     * Initiation of variables that match databalse table
     */
    private Timestamp transactionDate;
    private Integer senderAccountNumber;
    private Integer receiverAccountNumber;
    private BigDecimal amount;

    /**
     * Construktor for Transaction
     * @param transactionDate filed for timestamp
     * @param senderAccountNumber field for Sender account number
     * @param receiverAccountNumber field for Receiver account number
     * @param amount filed for amount within transaction
     */
    public Transaction(Timestamp transactionDate, Integer senderAccountNumber, Integer receiverAccountNumber,BigDecimal amount){

        this.transactionDate = transactionDate;
        this.senderAccountNumber = senderAccountNumber;
        this.receiverAccountNumber = receiverAccountNumber;
        this.amount = amount;
    }

    /**
     * Getter methods for the fields...
     * @return getter methods
     */
    public Timestamp getTransactionDate(){
        return transactionDate;
    }

    public Integer getSenderAccountNumber(){
        return senderAccountNumber;
    }

    public Integer getReceiverAccountNumber(){
        return receiverAccountNumber;
    }

    public BigDecimal getAmount(){
        return amount;
    }
}
