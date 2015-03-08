package com.example.deekshasharma.pennyapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.example.deekshasharma.pennyapp.Collections.AllSummaryItemsEndPoint;
import com.example.deekshasharma.pennyapp.adapter.SummaryListAdapter;


public class SummaryActivity extends MainActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_main,frameLayout);
        getLayoutInflater().inflate(R.layout.activity_summary,frameLayout);

        ListView summaryListView = (ListView) findViewById(R.id.summary_list_view);

        ArrayAdapter summaryListAdapter = new SummaryListAdapter(this,R.layout.summary_list_item, AllSummaryItemsEndPoint.summaryItemList);
        summaryListView.setAdapter(summaryListAdapter);
        AllSummaryItemsEndPoint endPoint = new AllSummaryItemsEndPoint(this,summaryListAdapter);


        TextView year = (TextView) findViewById(R.id.get_year_summary);
        year.setText(endPoint.getYear());
        TextView month = (TextView) findViewById(R.id.get_months_summary);
        month.setText(endPoint.getMonth());
        TextView totalSpent = (TextView) findViewById(R.id.get_total_spent_summary);
        totalSpent.setText(endPoint.getTotalSpent());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_summary, menu);
        getMenuInflater().inflate(R.menu.menu_add_button,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.action_settings:
                return true;
            case R.id.addButton:
                Intent intent = new Intent(this,AddActivity.class);
                startActivity(intent);
            default:
                return super.onOptionsItemSelected(item);
        }    }


}
