package com.example.deekshasharma.pennyapp;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.example.deekshasharma.pennyapp.Collections.BudgetsEndPoint;
import com.example.deekshasharma.pennyapp.model.CategoryMapConverter;
import com.example.deekshasharma.pennyapp.model.GroupToImage;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class AddBudgetActivity extends ActionBarActivity {

    private String categorySelected;
    private Spinner categorySpinner;
    private Spinner groupSpinner;
    private boolean recurringFlag;
    private String groupSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_budget);

        setUpGroupSpinner();
        onGroupSelectListener();
        onCategorySelectListener();
        onAddCategoryClickListener();
    }

    /*
    Fills the group Spinner with group Titles
     */
    private void setUpGroupSpinner() {
        groupSpinner = (Spinner) findViewById(R.id.group_spinner);
        Set<String> groups = GroupToImage.getGroups();
        List<String> groupList = new ArrayList<>(groups);
        ArrayAdapter<String> groupAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, groupList);
        groupAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        groupSpinner.setAdapter(groupAdapter);
    }

    /*
    When user select a group from the dropdown menu
     */
    private void onGroupSelectListener()
    {
        groupSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                groupSelected = parent.getItemAtPosition(position).toString();
                setUpCategorySpinner();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    /*
    Fetch the categories for a group and fills the dropdown
     */
    private void setUpCategorySpinner()
    {
        CategoryMapConverter mapConverter = CategoryMapConverter.getInstance(this);
        Map<String, Map<String, String>> map = mapConverter.getCategoriesMap();
        Set<String> categories = map.get(groupSelected).keySet();
        List<String> categoriesList = new ArrayList<>();
        categoriesList.add("Select Category");
        for(String each: categories)
        {
            categoriesList.add(each);
        }
        categorySpinner = (Spinner) findViewById(R.id.category_spinner);
        ArrayAdapter categoryAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,categoriesList);
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(categoryAdapter);
    }

    /*
        When user selects the category from dropdown
    */
    private void onCategorySelectListener()
    {
        categorySpinner = (Spinner) findViewById(R.id.category_spinner);
        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                categorySelected = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    /*
    Listens to AddCategory button
     */
    private void onAddCategoryClickListener()
    {
        Button addCategoryButton = (Button) findViewById(R.id.add_category_button);
        addCategoryButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                EditText budgetAmount = (EditText) findViewById(R.id.get_amount);
                String amount = budgetAmount.getText().toString();
                String categoryId = getCategoryId();
                String groupOnly = getGroupOnlyFlag();
                getSwitchState();
                String recurring = Boolean.toString(recurringFlag);
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(new Date());
                String month = Integer.toString(calendar.get(Calendar.MONTH) + 1);
                String year = Integer.toString(calendar.get(Calendar.YEAR));

                List<String> addBudgetList = new ArrayList<>();
                addBudgetList.add(amount);
                addBudgetList.add(categoryId);
                addBudgetList.add(groupOnly);
                addBudgetList.add(recurring);
                addBudgetList.add(month);
                addBudgetList.add(year);

                BudgetsEndPoint endPoint = new BudgetsEndPoint(getApplicationContext(),addBudgetList);
                endPoint.postBudgetCategory(getApplicationContext());

                Intent intent = new Intent(getApplicationContext(),BudgetActivity.class);
                startActivity(intent);

                Toast.makeText(getApplicationContext(), "Category added", Toast.LENGTH_SHORT).show();

            }
        });
    }

    /*
    Returns groupOnly flag as true or false
     */
    private String getGroupOnlyFlag()
    {
        if(categorySelected.equals("Select Category"))
        {
            return  "true";
        }
        else
        {
            return "false";
        }
    }

    /*
    Return category id of the selected category
     */
    private String getCategoryId()
    {
        CategoryMapConverter mapConverter = CategoryMapConverter.getInstance(this);
        Map<String, Map<String, String>> map = mapConverter.getCategoriesMap();
        String id ="";
        if(categorySelected.equals("Select Category"))
        {
            Collection allId =  map.get(groupSelected).values();
            Iterator iterator = allId.iterator();
            while(iterator.hasNext())
            {
                id = iterator.next().toString();
                break;
            }
        }
        else
        {
            id = map.get(groupSelected).get(categorySelected);
        }
        return id;
    }


    private void getSwitchState()
    {
        Switch recurringSwitch = (Switch)findViewById(R.id.switch1);
        if(recurringSwitch != null)
        {
            recurringSwitch.setOnCheckedChangeListener( new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
                {
                    if (isChecked)
                    {
                        recurringFlag = true;
                    }
                    else
                    {
                        recurringFlag = false;
                    }
                }
            });
        }

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_budget, menu);
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
