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
import com.example.deekshasharma.pennyapp.model.SummaryItem;
import java.util.List;

public class SummaryListAdapter extends ArrayAdapter<SummaryItem>{

    private List<SummaryItem> summaryItemList;

    public SummaryListAdapter(Context context, int resource, List<SummaryItem> summaryItemList)
    {
        super(context, resource,summaryItemList);
        this.summaryItemList = summaryItemList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater)
                    getContext().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.summary_list_item, null);
        }
        TextView groupName = (TextView) convertView.findViewById(R.id.get_groupname_summary);
        groupName.setText(summaryItemList.get(position).getGroupName());

        TextView spentOnGroup = (TextView) convertView.findViewById(R.id.get_spent_summary);
        spentOnGroup.setText("$"+summaryItemList.get(position).getSpent());

        ImageView groupImage = (ImageView) convertView.findViewById(R.id.get_groupImage_summary);
        int imageId = GroupToImage.getImage(summaryItemList.get(position).getGroupName());
        groupImage.setImageResource(imageId);
        return convertView;
    }

}
