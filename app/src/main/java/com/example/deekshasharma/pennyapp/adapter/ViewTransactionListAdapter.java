package com.example.deekshasharma.pennyapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.deekshasharma.pennyapp.R;
import com.example.deekshasharma.pennyapp.model.TransactionItem;

import java.util.List;

public class ViewTransactionListAdapter extends BaseAdapter{

    private Context context;
    private List<TransactionItem> transactionList;


    public ViewTransactionListAdapter(Context context, List<TransactionItem> transactionList)
    {
        this.context = context;
        this.transactionList = transactionList;
    }
    @Override
    public int getCount() {
        return transactionList.size();
    }

    @Override
    public Object getItem(int position)
    {
        return transactionList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.transaction_list_item, null);

//        ImageView date = (ImageView) convertView.findViewById(R.id.date_icon);
//        ImageView transactionIcon = (ImageView) convertView.findViewById(R.id.transaction_icon);
        TextView transactionName = (TextView) convertView.findViewById(R.id.transaction_name);
        TextView amount = (TextView) convertView.findViewById(R.id.amount);
        TextView date = (TextView) convertView.findViewById(R.id.date);


//        date.setImageResource(transactionList.get(position).getDateIcon());
//        transactionIcon.setImageResource(transactionList.get(position).getTransactionIcon());
        transactionName.setText(transactionList.get(position).getTransactionName());
        amount.setText(transactionList.get(position).getAmount());
        date.setText(transactionList.get(position).getTransactionDate());
        return convertView;
    }
}
