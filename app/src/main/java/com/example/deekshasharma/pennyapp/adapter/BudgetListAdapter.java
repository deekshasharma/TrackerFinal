package com.example.deekshasharma.pennyapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.deekshasharma.pennyapp.R;
import com.example.deekshasharma.pennyapp.model.BudgetItem;

import java.util.List;

public class BudgetListAdapter extends ArrayAdapter<BudgetItem>{

    private List<BudgetItem> budgetItemList;

    public BudgetListAdapter(Context context, int resource, List<BudgetItem> budgetItemList)
    {
        super(context, resource, budgetItemList);
        this.budgetItemList = budgetItemList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater)
                    getContext().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.category_list_item, null);
        }
//        TextView txtTitle = (TextView) convertView.findViewById(R.id.category_name);
//        txtTitle.setText(categoryItemList.get(position).toString());
        return convertView;
    }

}
