package com.example.deekshasharma.pennyapp.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BudgetItem {

    private String categoryName;
    private String groupName;
    private boolean groupOnly;
    private String percentSpent;
    private String budgeted;
    private String available;


    public BudgetItem(JSONObject jsonObject) {
        try
        {
            this.categoryName = jsonObject.getJSONObject("category").getString("name");
            this.groupName = jsonObject.getJSONObject("category").getString("groupName");
            this.groupOnly = jsonObject.getBoolean("groupOnly");
            this.budgeted = jsonObject.getString("budgeted");
            this.available = jsonObject.getString("available");

            double budgeted = Double.parseDouble(jsonObject.getString("budgeted"));
            double available = Double.parseDouble(jsonObject.getString("available"));
            double percentSpentDouble = ((budgeted - available)/budgeted) * 100.0;
            double rounded = round(percentSpentDouble,2);
            this.percentSpent = Double.toString(rounded);

        }catch (JSONException e)
        {
            e.printStackTrace();
        }
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public String getAvailable() {
        return available;
    }

    public String getCategoryName() {
        return this.categoryName;
    }

    public String getPercentSpent() {
        return this.percentSpent;
    }

    public String getBudgeted() {
        return this.budgeted;
    }

    public String getGroupName() {
        return this.groupName;
    }

    public boolean isGroupOnly() {
        return this.groupOnly;
    }
}
