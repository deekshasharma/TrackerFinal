package com.example.deekshasharma.pennyapp;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.deekshasharma.pennyapp.adapter.ViewTransactionListAdapter;
import com.example.deekshasharma.pennyapp.model.TransactionItem;
import com.example.deekshasharma.pennyapp.model.TransactionsEndPoint;

import java.util.List;

public class ViewTransactionFragment extends ListFragment{


    public ViewTransactionFragment(){}

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);

        TransactionsEndPoint transactionsEndPoint = new TransactionsEndPoint(getActivity(), this);
        List<TransactionItem> list = transactionsEndPoint.getTransactionList();
        setListAdapter(new ViewTransactionListAdapter(getActivity(),list));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_view_transaction, container, false);
        return rootView;
    }
}
