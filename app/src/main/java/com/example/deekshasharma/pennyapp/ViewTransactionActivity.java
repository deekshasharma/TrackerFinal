package com.example.deekshasharma.pennyapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.deekshasharma.pennyapp.Collections.TransactionsEndPoint;
import com.example.deekshasharma.pennyapp.adapter.ViewTransactionListAdapter;

import java.util.Calendar;
import java.util.Date;


public class ViewTransactionActivity extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getLayoutInflater().inflate(R.layout.activity_main,frameLayout);
        getLayoutInflater().inflate(R.layout.activity_view_transaction,frameLayout);


        ListView transListView = (ListView) findViewById(R.id.trans_list_view);
        ArrayAdapter transListAdapter = new ViewTransactionListAdapter(this,
                                        R.layout.transaction_list_item,
                                        TransactionsEndPoint.transactionList);
        transListView.setAdapter(transListAdapter);
        TransactionsEndPoint endPoint = new TransactionsEndPoint(this,transListAdapter);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        String month = Integer.toString(calendar.get(Calendar.MONTH) + 1);
        TextView monthTextView = (TextView) findViewById(R.id.get_month_trans);
        monthTextView.setText(month);
        String year = Integer.toString(calendar.get(Calendar.YEAR));
        TextView yearTextView = (TextView) findViewById(R.id.get_year_trans);
        yearTextView.setText(year);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_view_transaction, menu);
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
        }
    }
}
