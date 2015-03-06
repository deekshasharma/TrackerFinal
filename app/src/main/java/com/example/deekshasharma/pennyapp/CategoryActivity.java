package com.example.deekshasharma.pennyapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.deekshasharma.pennyapp.adapter.CategoryListAdapter;
import com.example.deekshasharma.pennyapp.model.Categories;
import com.example.deekshasharma.pennyapp.model.CategoryItem;

import java.util.ArrayList;
import java.util.List;


public class CategoryActivity extends ActionBarActivity {

    private ListView categoryListView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        Intent intentFromGroup = getIntent();
        String groupName = intentFromGroup.getStringExtra("groupName");

        categoryListView = (ListView) findViewById(R.id.category_list_view);
        ArrayAdapter categoryAdapter = new CategoryListAdapter(this,R.layout.category_list_item,Categories.allCategories);
        categoryListView.setAdapter(categoryAdapter);
        Categories categoriesSingleton = new Categories(this, groupName,categoryAdapter);
        categoryListView.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent goToAddIntent = new Intent(getApplicationContext(), AddActivity.class);
                goToAddIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                goToAddIntent.putExtra("position", position);
                startActivity(goToAddIntent);
            }
                   });



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_category, menu);
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
