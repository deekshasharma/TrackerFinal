package com.example.deekshasharma.pennyapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

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

        onAddToBudgetClickListener();


    }

    private void onAddToBudgetClickListener()
    {
        TextView addToBudget = (TextView) findViewById(R.id.add_category_to_budget);
        addToBudget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddBudgetActivity.class);
                startActivity(intent);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_budget, menu);
        getMenuInflater().inflate(R.menu.menu_add_button,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (item.getItemId())
        {
            case R.id.action_settings:
                return true;
            case R.id.addButton:
                Intent intent = new Intent(this,AddActivity.class);
                startActivity(intent);
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
