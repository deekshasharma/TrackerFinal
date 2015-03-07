package com.example.deekshasharma.pennyapp;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.deekshasharma.pennyapp.Collections.AllSummaryItemsEndPoint;
import com.example.deekshasharma.pennyapp.adapter.SummaryListAdapter;
import com.example.deekshasharma.pennyapp.model.AsyncResponse;


public class SummaryActivity extends MainActivity implements AsyncResponse{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_main,frameLayout);
        getLayoutInflater().inflate(R.layout.activity_summary,frameLayout);

        ListView summaryListView = (ListView) findViewById(R.id.summary_list_view);

        ArrayAdapter summaryListAdapter = new SummaryListAdapter(this,R.layout.summary_list_item, AllSummaryItemsEndPoint.summaryItemList);
        summaryListView.setAdapter(summaryListAdapter);
        AllSummaryItemsEndPoint endPoint = new AllSummaryItemsEndPoint(this,summaryListAdapter);

        endPoint.delegate = this;
        TextView year = (TextView) findViewById(R.id.get_year_summary);
        year.setText(endPoint.getYear());
        TextView month = (TextView) findViewById(R.id.get_months_summary);
        month.setText(endPoint.getMonth());
        TextView totalSpent = (TextView) findViewById(R.id.get_total_spent_summary);
        totalSpent.setText(endPoint.getTotalSpent());
    }

    @Override
    public void processFinish(String output)
    {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_summary, menu);
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
