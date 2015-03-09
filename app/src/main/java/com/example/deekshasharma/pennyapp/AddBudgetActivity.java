package com.example.deekshasharma.pennyapp;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import com.example.deekshasharma.pennyapp.model.CategoryMapConverter;
import com.example.deekshasharma.pennyapp.model.GroupToImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class AddBudgetActivity extends ActionBarActivity {

    private String groupSelected;
    private String categorySelected;
    private Spinner categorySpinner;
    private Spinner groupSpinner;




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
                Toast.makeText(getApplicationContext(), "You have selected "+ groupSelected,Toast.LENGTH_SHORT).show();
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
        CategoryMapConverter mapConverter = CategoryMapConverter.getInstance();
        Map<String, Map<String, String>> map = mapConverter.getCategoriesMap();
        Set<String> categories = map.get(groupSelected).keySet();
        List<String> categoriesList = new ArrayList<>(categories);

        categorySpinner = (Spinner) findViewById(R.id.category_spinner);
        ArrayAdapter categoryAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,categoriesList);
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(categoryAdapter);
    }

/*

 */
    private void onCategorySelectListener()
    {
        categorySpinner.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
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
                // Get data from all fields and POST to server.
            }
        });
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
