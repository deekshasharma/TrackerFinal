package com.example.deekshasharma.pennyapp;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.TypedArray;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.deekshasharma.pennyapp.adapter.GroupListAdapter;
import com.example.deekshasharma.pennyapp.model.IconWithTitleItem;

import java.util.ArrayList;
import java.util.List;


public class GroupActivity extends ActionBarActivity {

    private String[] groupTitles;
    private TypedArray groupIcons;
    private List<IconWithTitleItem> groupList;
    private ListView groupListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);
        displayGroups();
    }

    protected void displayGroups()
    {
        groupListView = (ListView)findViewById(R.id.group_list_view);
        getAllGroups();
        groupListView.setAdapter(new GroupListAdapter(this, groupList));
        groupListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Intent goToCategoryIntent = new Intent(getApplicationContext(), CategoryActivity.class);
                if (position == 0) {
                    goToCategoryIntent.putExtra("groupName", "Food & Drink");
                } else if (position == 6) {
                    goToCategoryIntent.putExtra("groupName", "Sports & Fitness");
                } else if (position == 7) {
                    goToCategoryIntent.putExtra("groupName", "Gifts & Donations");
                } else if (position == 8) {
                    goToCategoryIntent.putExtra("groupName", "Health & Medical");
                } else {
                    goToCategoryIntent.putExtra("groupName", groupTitles[position]);
                }
                startActivity(goToCategoryIntent);
            }
        });
    }


                /////////////////
//                Fragment categoryFragment = new CategoryFragment();
//                FragmentTransaction transaction = getFragmentManager().beginTransaction();
//                Bundle args = new Bundle();
//                if(position == 0)
//                {
//                    args.putString("groupName","Food & Drink");
//                }
//                else if(position == 6)
//                {
//                    args.putString("groupName","Sports & Fitness");
//                }
//                else if(position == 7)
//                {
//                    args.putString("groupName", "Gifts & Donations");
//                }
//                else if(position == 8)
//                {
//                    args.putString("groupName", "Health & Medical");
//                }
//                else
//                {
//                    args.putString("groupName", groupTitles[position]);
//                }
//
//                categoryFragment.setArguments(args);
//                transaction.replace(R.id.frame_container,categoryFragment).commit();
//                setTitle("Select Category");
//            }
//        });
//    }



    /*
  Add all IconWithTitleItem objects into groupList
   */
    private void getAllGroups()
    {
        groupTitles = getResources().getStringArray(R.array.group_titles);
        groupIcons= getResources().obtainTypedArray(R.array.group_icons);
        groupList = new ArrayList<>();
        for(int i = 0; i < groupIcons.length();i++)
        {
            IconWithTitleItem groupItem = new IconWithTitleItem(groupTitles[i],groupIcons.getResourceId(i,-1));
            groupList.add(groupItem);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_group, menu);
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
