package com.example.deekshasharma.pennyapp;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class ViewTransactionActivity extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_view_transaction);

        getLayoutInflater().inflate(R.layout.activity_main,frameLayout);
        getLayoutInflater().inflate(R.layout.activity_view_transaction,frameLayout);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_transaction, menu);
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
