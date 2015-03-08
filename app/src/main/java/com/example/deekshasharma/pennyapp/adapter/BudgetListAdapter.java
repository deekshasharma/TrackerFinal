package com.example.deekshasharma.pennyapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.deekshasharma.pennyapp.R;
import com.example.deekshasharma.pennyapp.model.BudgetItem;
import com.example.deekshasharma.pennyapp.model.GroupToImage;

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
            convertView = mInflater.inflate(R.layout.budget_list_item, null);
        }



        TextView categoryName = (TextView) convertView.findViewById(R.id.budget_group_name);
        if(budgetItemList.get(position).isGroupOnly())
        {
            categoryName.setText(budgetItemList.get(position).getGroupName());
        }
        else
        {
            categoryName.setText(budgetItemList.get(position).getCategoryName());
        }

        ImageView groupIcon  = (ImageView) convertView.findViewById(R.id.budget_group_icon);
        int imageId = GroupToImage.getImage(budgetItemList.get(position).getGroupName());
        groupIcon.setImageResource(imageId);

        TextView percentSpent = (TextView) convertView.findViewById(R.id.budget_percent_spent);
        percentSpent.setText(budgetItemList.get(position).getPercentSpent()+"%");

        TextView budgetAmount = (TextView) convertView.findViewById(R.id.total_budget);
        budgetAmount.setText("$"+budgetItemList.get(position).getBudgeted());
        return convertView;
    }

}
