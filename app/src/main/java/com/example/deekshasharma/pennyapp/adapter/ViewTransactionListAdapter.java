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
import com.example.deekshasharma.pennyapp.model.GroupToImage;
import com.example.deekshasharma.pennyapp.model.TransactionItem;

import java.util.List;

public class ViewTransactionListAdapter extends ArrayAdapter {

    private List<TransactionItem> transactionItemList;

    public ViewTransactionListAdapter(Context context, int resource, List<TransactionItem> transactionItemList) {
        super(context, resource, transactionItemList);
        this.transactionItemList = transactionItemList;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater)getContext().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.transaction_list_item, null);

            TextView transactionName = (TextView) convertView.findViewById(R.id.transaction_name);
            transactionName.setText(transactionItemList.get(position).getTransactionName());

            TextView amount = (TextView) convertView.findViewById(R.id.get_trans_amount);
            amount.setText("$"+transactionItemList.get(position).getAmount());

            TextView date = (TextView) convertView.findViewById(R.id.get_date);
            date.setText(transactionItemList.get(position).getTransactionDate());

            ImageView groupIcon = (ImageView)convertView.findViewById(R.id.get_group_icon);
            int imageId = GroupToImage.getImage(transactionItemList.get(position).getGroupName());
            groupIcon.setImageResource(imageId);

        }
            return convertView;
        }
    }
