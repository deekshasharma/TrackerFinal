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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ViewTransactionListAdapter extends ArrayAdapter {

    private List<TransactionItem> transactionItemList;
    private Context context;

    public ViewTransactionListAdapter(Context context, int resource, List<TransactionItem> transactionItemList) {
        super(context, resource, transactionItemList);
        this.context = context;
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
            String formattedDate = fromUTCToString(transactionItemList.get(position).getTransactionDate());
            date.setText(formattedDate);

//
            ImageView groupIcon = (ImageView)convertView.findViewById(R.id.get_group_icon);
            int imageId = GroupToImage.getImage(transactionItemList.get(position).getGroupName());
            groupIcon.setImageResource(imageId);

        }
            return convertView;
        }

    private String fromUTCToString(String utcDate)
    {
        String[] months =  context.getResources().getStringArray(R.array.months);
        String[]weekdays = context.getResources().getStringArray(R.array.weekdays);
        String dateString = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        try {
            Date date = simpleDateFormat.parse(utcDate);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            dateString = (weekdays[calendar.get(Calendar.DAY_OF_WEEK)] +","
                    +calendar.get(Calendar.DAY_OF_MONTH))+ " "
                    +months[calendar.get(Calendar.MONTH)]+" "
                    +calendar.get(Calendar.YEAR);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateString;

    }
    }
