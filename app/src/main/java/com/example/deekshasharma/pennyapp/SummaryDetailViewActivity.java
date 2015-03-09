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


public class SummaryDetailViewActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary_detail_view);

        Intent intent = getIntent();
        String groupName = intent.getStringExtra("groupName");
        String spentOnGroup = intent.getStringExtra("spentOnGroup");

        setUpHeader(groupName,spentOnGroup);

        ListView detailListView = (ListView) findViewById(R.id.summary_detail_list_view);
        TextView numOfTrans = (TextView)findViewById(R.id.get_detail_num_trans);
        ArrayAdapter transactionListAdapter = new ViewTransactionListAdapter(this,
                                                R.layout.transaction_list_item,
                                                TransactionsEndPoint.transactionList);
        detailListView.setAdapter(transactionListAdapter);
        TransactionsEndPoint endPoint = new TransactionsEndPoint(this,transactionListAdapter,groupName,numOfTrans);
    }

    /*
    Populate values in the header of this Activity
     */
    private void setUpHeader(String groupName, String spentOnGroup)
    {
        TextView spent = (TextView) findViewById(R.id.get_spent_on_grp);
        spent.setText("$"+spentOnGroup);

        TextView group = (TextView) findViewById(R.id.get_detail_grp_name);
        group.setText(groupName);

        int imageId = GroupToImage.getImage(groupName);
        ImageView groupIcon = (ImageView) findViewById(R.id.get_detail_grp_icon);
        groupIcon.setImageResource(imageId);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_summary_detail_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
