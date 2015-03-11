package com.example.deekshasharma.pennyapp;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.deekshasharma.pennyapp.Collections.TransactionsEndPoint;
import com.example.deekshasharma.pennyapp.adapter.ViewTransactionListAdapter;
import com.example.deekshasharma.pennyapp.model.GroupToImage;


public class BudgetDetailViewActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget_detail_view);

        Intent intent = getIntent();
        String groupName = intent.getStringExtra("groupName");
        String percentSpent = intent.getStringExtra("percentSpent");
        String budgeted = intent.getStringExtra("budgeted");
        String avail = intent.getStringExtra("available");
        String categoryId = intent.getStringExtra("categoryId");
        String groupOnly = intent.getStringExtra("isGroupOnly");

        setUpHeader(groupName,percentSpent,budgeted,avail);

        ListView budgetListView = (ListView)findViewById(R.id.budget_detail_list_view);
        ArrayAdapter transactionListAdapter = new ViewTransactionListAdapter(this,
                                                    R.layout.transaction_list_item,
                                                TransactionsEndPoint.transactionList);
        budgetListView.setAdapter(transactionListAdapter);

        if(groupOnly.equalsIgnoreCase("true"))
        {
            new TransactionsEndPoint(this,transactionListAdapter,groupName,true);
        }
        else
        {
            new TransactionsEndPoint(this,transactionListAdapter,categoryId,false);
        }


    }


    /*
    Populate values in the header of this Activity
     */
    private void setUpHeader(String groupName, String percentSpent,String budgeted, String avail)
    {
        TextView spent = (TextView) findViewById(R.id.detailBudget_getSpent);
        spent.setText(percentSpent+"%");

        TextView group = (TextView) findViewById(R.id.detailBudget_getGrpName);
        group.setText(groupName);

        int imageId = GroupToImage.getImage(groupName);
        ImageView groupIcon = (ImageView) findViewById(R.id.detailBudget_getGrpIcon);
        groupIcon.setImageResource(imageId);

        TextView budgetAmount = (TextView)findViewById(R.id.detailBudget_getBudgeted);
        budgetAmount.setText("$"+budgeted);

        TextView available = (TextView)findViewById(R.id.detailBudget_getAvailable);
        available.setText("$"+avail);


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_budget_detail_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
