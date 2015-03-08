package com.example.deekshasharma.pennyapp;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.deekshasharma.pennyapp.model.GroupToImage;

import java.util.Set;


public class AddBudget extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_budget);


        onAddCategoryClickListener();


    }

    private void setUpGroupSpinner()
    {
        Spinner groupSpinner = (Spinner) findViewById(R.id.group_spinner);
//        groupSpinner.setOnItemSelectedListener(this);
        Set<String> groups = GroupToImage.getGroups();
        ArrayAdapter groupAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, (java.util.List) groups);
        groupAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        groupSpinner.setAdapter(groupAdapter);
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
