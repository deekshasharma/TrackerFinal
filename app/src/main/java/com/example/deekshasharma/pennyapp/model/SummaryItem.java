package com.example.deekshasharma.pennyapp.model;

public class SummaryItem {

    private String groupName;
    private String spent;

    public SummaryItem(String groupName, String spent) {
        this.groupName = groupName;
        this.spent = spent;
    }

    public String getGroupName() {
        return groupName;
    }

    public String getSpent() {
        return spent;
    }
}
