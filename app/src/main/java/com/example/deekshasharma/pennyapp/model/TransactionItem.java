package com.example.deekshasharma.pennyapp.model;

public class TransactionItem {

//    private int dateIcon;
    private String transactionName;
    private String transactionDate;
    private String amount;
    private int transactionIcon;
    private int transactionId;


    public TransactionItem(String date, String transactionName, String amount)
    {
        this.transactionDate = date;
        this.transactionName = transactionName;
        this.amount = amount;
    }

//    public String getDateIcon()
//    {
//        return transactionDate;
//    }


    public String getTransactionDate() {
        return transactionDate;
    }

    public String getTransactionName() {
        return transactionName;
    }

    public String getAmount() {
        return amount;
    }

//    public int getTransactionIcon() {
//        return transactionIcon;
//    }

//    public void setTransactionId(int transactionId) {
//        this.transactionId = transactionId;
//    }
//
//    public int getTransactionId() {
//        return transactionId;
//    }
}
