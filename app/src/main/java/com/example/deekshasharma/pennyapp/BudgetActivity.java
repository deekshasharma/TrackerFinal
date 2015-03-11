package com.example.deekshasharma.pennyapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.deekshasharma.pennyapp.Collections.BudgetsEndPoint;
import com.example.deekshasharma.pennyapp.adapter.BudgetListAdapter;
import com.example.deekshasharma.pennyapp.model.BudgetItem;
import com.example.deekshasharma.pennyapp.model.SummaryItem;

import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class BudgetActivity extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_main,frameLayout);
        getLayoutInflater().inflate(R.layout.activity_view_budget,frameLayout);

        ListView budgetListView = (ListView) findViewById(R.id.budget_list_view);
        ArrayAdapter budgetListAdapter = new BudgetListAdapter(this,R.layout.budget_list_item, BudgetsEndPoint.budgetItemList);
        budgetListView.setAdapter(budgetListAdapter);
        new BudgetsEndPoint(this,budgetListAdapter);

        setHeader();
        budgetItemClickListener(budgetListView,BudgetsEndPoint.budgetItemList);  ///// Added now
        onAddToBudgetClickListener();



    }

    /*
    Sets the header for the application
     */
    private void setHeader()
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        String month = Integer.toString(calendar.get(Calendar.MONTH) + 1);
        TextView monthTextView = (TextView) findViewById(R.id.get_month_budget);
        monthTextView.setText(month);

        String year = Integer.toString(calendar.get(Calendar.YEAR));
        TextView yearTextView = (TextView) findViewById(R.id.get_year_budget);
        yearTextView.setText(year);

        int maxDaysInMonth = (Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_MONTH));
        Calendar cal = Calendar.getInstance();
        int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
        TextView daysLeft = (TextView) findViewById(R.id.get_days_budget);
        daysLeft.setText(Integer.toString(maxDaysInMonth - dayOfMonth));
    }

    /*
    Listens to AddCategoryToBudget label
    */
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


    /*
    Handles the click of listItem on the Summary screen
     */
    private void budgetItemClickListener(ListView budgetListView, final List<BudgetItem> budgets)
    {
        budgetListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Intent intent = new Intent(getApplicationContext(),BudgetDetailViewActivity.class);
                intent.putExtra("groupName",budgets.get(position).getGroupName());
                intent.putExtra("percentSpent",budgets.get(position).getPercentSpent());
                intent.putExtra("budgeted",budgets.get(position).getBudgeted());
                intent.putExtra("available",budgets.get(position).getAvailable());
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
