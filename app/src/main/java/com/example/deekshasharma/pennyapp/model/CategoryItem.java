package com.example.deekshasharma.pennyapp.model;


public class CategoryItem {

    private String name;
    private String id;
    private String groupName;

    public CategoryItem(String name, String id, String groupName)
    {
        this.name = name;
        this.id = id;
        this.groupName = groupName;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getGroupName() {
        return groupName;
    }


    @Override
    public String toString() {
        return name;
    }
}
