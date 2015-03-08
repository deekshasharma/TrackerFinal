package com.example.deekshasharma.pennyapp;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.deekshasharma.pennyapp.Collections.BudgetsEndPoint;
import com.example.deekshasharma.pennyapp.adapter.BudgetListAdapter;


public class BudgetActivity extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getLayoutInflater().inflate(R.layout.activity_main,frameLayout);
        getLayoutInflater().inflate(R.layout.activity_view_budget,frameLayout);

        ListView budgetListView = (ListView) findViewById(R.id.budget_list_view);
        ArrayAdapter budgetListAdapter = new BudgetListAdapter(this,R.layout.budget_list_item, BudgetsEndPoint.budgetItemList);
        budgetListView.setAdapter(budgetListAdapter);

        BudgetsEndPoint endPoint = new BudgetsEndPoint(this,budgetListAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_budget, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
