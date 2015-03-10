package com.example.deekshasharma.pennyapp;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.example.deekshasharma.pennyapp.model.CategoryMapConverter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


public class CategoryActivity extends ActionBarActivity {

    private ListView categoryListView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        Intent intentFromGroup = getIntent();
        final String groupName = intentFromGroup.getStringExtra("groupName");

        final CategoryMapConverter mapConverter = CategoryMapConverter.getInstance(this);
        Set<String> categorySet  = mapConverter.getCategoriesMap().get(groupName).keySet();
        final List<String> categoriesList = new ArrayList<>(categorySet);


        categoryListView = (ListView) findViewById(R.id.category_list_view);
        ArrayAdapter categoryAdapter = new ArrayAdapter(getApplicationContext(),R.layout.category_list_item,R.id.category_name, categoriesList);
        categoryListView.setAdapter(categoryAdapter);

        categoryListView.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent goToAddIntent = new Intent(getApplicationContext(), AddActivity.class);
                goToAddIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                goToAddIntent.putExtra("selectedCategory", categoriesList.get(position));
                goToAddIntent.putExtra("categoryId",mapConverter.getCategoriesMap().get(groupName).get(categoriesList.get(position)));
                goToAddIntent.putExtra("groupName",groupName);
                startActivity(goToAddIntent);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_category, menu);
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
