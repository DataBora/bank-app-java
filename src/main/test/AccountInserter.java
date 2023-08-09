package main.test;

import java.math.BigDecimal;
import java.sql.SQLException;

import main.java.package1.DBAccountInserter;

public class AccountInserter {
    /**
     * This class is inserting Accounts that I will use fro testing
     * @param args
     */
    public static void main(String[] args) {

        try{

        DBAccountInserter.insertAccount("John Doe", new BigDecimal("1750.00")); //1001
        DBAccountInserter.insertAccount("Jane Smith", new BigDecimal("3200.00"));  //1002
        DBAccountInserter.insertAccount("Papa Joe", new BigDecimal("7425.00"));  //1003
        DBAccountInserter.insertAccount("Mila Coco", new BigDecimal("500.00"));  //1004
        DBAccountInserter.insertAccount("Bob Ashton", new BigDecimal("10450.00"));  //1005
        DBAccountInserter.insertAccount("Kelly Nolan", new BigDecimal("777.00"));  //1006
        DBAccountInserter.insertAccount("Samantha Russell", new BigDecimal("102580.00"));  //1007

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
