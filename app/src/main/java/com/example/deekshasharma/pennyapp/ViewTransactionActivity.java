package com.example.deekshasharma.pennyapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.deekshasharma.pennyapp.adapter.ViewTransactionListAdapter;
import com.example.deekshasharma.pennyapp.model.TransactionsEndPoint;


public class ViewTransactionActivity extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_view_transaction);

        getLayoutInflater().inflate(R.layout.activity_main,frameLayout);
        getLayoutInflater().inflate(R.layout.activity_view_transaction,frameLayout);


        ListView transListView = (ListView) findViewById(R.id.trans_list_view);
        ArrayAdapter transListAdapter = new ViewTransactionListAdapter(this,
                R.layout.transaction_list_item,
                TransactionsEndPoint.transactionList);
        transListView.setAdapter(transListAdapter);
        TransactionsEndPoint endPoint = new TransactionsEndPoint(this,transListAdapter);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_view_transaction, menu);
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
