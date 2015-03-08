package com.example.deekshasharma.pennyapp.model;

public class TransactionItem {

    private String transactionName;
    private String transactionDate;
    private String amount;
    private String groupName;
    private int transactionId;


    public TransactionItem(String date, String transactionName, String amount, String groupName)
    {
        this.transactionDate = date;
        this.transactionName = transactionName;
        this.amount = amount;
        this.groupName = groupName;
    }

    public String getGroupName() {
        return groupName;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public String getTransactionName() {
        return transactionName;
    }

    public String getAmount() {
        return amount;
    }

}
