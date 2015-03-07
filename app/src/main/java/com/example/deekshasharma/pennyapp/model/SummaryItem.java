package com.example.deekshasharma.pennyapp.model;

public class SummaryItem {

    private String groupName;
    private String spent;
    private int groupIcon;

    public SummaryItem(String groupName, String spent) {
        this.groupName = groupName;
        this.spent = spent;
    }

    public String getGroupName() {
        return groupName;
    }

    public int getGroupIcon() {
        return groupIcon;
    }

    public void setGroupIcon(int groupIcon)
    {
        this.groupIcon = groupIcon;
    }

    public String getSpent() {
        return spent;
    }
}
