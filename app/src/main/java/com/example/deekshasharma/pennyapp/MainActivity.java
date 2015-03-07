package com.example.deekshasharma.pennyapp;
import android.app.Fragment;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;
import com.example.deekshasharma.pennyapp.adapter.NavDrawerListAdapter;
import com.example.deekshasharma.pennyapp.model.IconWithTitleItem;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {


    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;

    // nav drawer title
    private CharSequence mDrawerTitle;

    // used to store app title
    private CharSequence mTitle;

    // slide menu items
    private String[] navMenuTitles;
    private TypedArray navMenuIcons;

    private ArrayList<IconWithTitleItem> navDrawerItems;
    private NavDrawerListAdapter adapter;

    protected FrameLayout frameLayout;
    protected static int position;
    private static boolean isLaunch = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        frameLayout = (FrameLayout) findViewById(R.id.frame_container);

        mTitle = mDrawerTitle = getTitle();

        // load slide menu items
        navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);

        // nav drawer icons from resources
        navMenuIcons = getResources().obtainTypedArray(R.array.nav_drawer_icons);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.list_slidermenu);

        navDrawerItems = new ArrayList<>();

        // adding nav drawer items to array
        // TransactionItem
        navDrawerItems.add(new IconWithTitleItem(navMenuTitles[0], navMenuIcons.getResourceId(0, -1)));
        // Summary
        navDrawerItems.add(new IconWithTitleItem(navMenuTitles[1], navMenuIcons.getResourceId(1, -1)));
        // Budget
        navDrawerItems.add(new IconWithTitleItem(navMenuTitles[2], navMenuIcons.getResourceId(2, -1)));


        // Recycle the typed array
        navMenuIcons.recycle();

//        mDrawerList.setOnItemClickListener(new SlideMenuClickListener());

        // setting the nav drawer list adapter
        adapter = new NavDrawerListAdapter(getApplicationContext(),navDrawerItems);
        mDrawerList.setAdapter(adapter);

        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                displayView(position);
            }
        });

        // enabling action bar app icon and behaving it as toggle button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.drawable.ic_drawer, //nav menu toggle icon
                R.string.app_name, // nav drawer open - description for accessibility
                R.string.app_name // nav drawer close - description for accessibility
        ) {
            public void onDrawerClosed(View view) {
                getSupportActionBar().setTitle(navMenuTitles[position]);
                // calling onPrepareOptionsMenu() to show action bar icons
                invalidateOptionsMenu();
                super.onDrawerClosed(view);
            }

            public void onDrawerOpened(View drawerView) {
                getSupportActionBar().setTitle(mDrawerTitle);
                // calling onPrepareOptionsMenu() to hide action bar icons
                invalidateOptionsMenu();
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
            }

            @Override
            public void onDrawerStateChanged(int newState) {
                super.onDrawerStateChanged(newState);
            }
        };

        mDrawerLayout.setDrawerListener(mDrawerToggle);

        if (isLaunch)
        {
            isLaunch = false;
            displayView(0);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        getMenuInflater().inflate(R.menu.menu_add_button,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // toggle nav drawer on selecting action bar app icon/title
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle action bar actions click
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

    /* *
	 * Called when invalidateOptionsMenu() is triggered
	 */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // if nav drawer is opened, hide the action items
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
        menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }

    // New method to test Budget Activity
    private void displayView(int position) {
        // update the main content by replacing fragments
        Fragment fragment = null;

        mDrawerLayout.closeDrawer(mDrawerList);
        MainActivity.position = position;

        switch (position) {
            case 0:
                Intent intent1 = new Intent(this,ViewTransactionActivity.class);
                startActivity(intent1);
                break;
            case 1:
                Intent intent2 = new Intent(this,BudgetActivity.class);
                startActivity(intent2);
                break;
            case 2:
                Intent intent = new Intent(this,BudgetActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }

    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getSupportActionBar().setTitle(mTitle);
    }

    /**
     * When using the ActionBarDrawerToggle, you must call it during
     * onPostCreate() and onConfigurationChanged()...
     */

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

}


