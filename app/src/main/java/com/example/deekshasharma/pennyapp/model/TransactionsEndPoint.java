package com.example.deekshasharma.pennyapp.model;

import android.content.Context;
import android.util.Log;
import android.widget.ArrayAdapter;
import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TransactionsEndPoint {

    private ArrayAdapter viewTransactionListAdapter;
    public static List<TransactionItem> transactionList = new ArrayList<>();
    private Context context;
    private static final String URL = "https://api-pennyapp.rhcloud.com/rest/transactions/d8922b44-75af-4810-a87e-77adcf433cfd/2015/03";


    public TransactionsEndPoint(Context context, ArrayAdapter viewTransactionListAdapter)
    {
        this.context = context;
        this.viewTransactionListAdapter = viewTransactionListAdapter;
        getTransactionsFromServer();
    }


    /*
    Gets all the transactions from the server
     */
    private void getTransactionsFromServer()
    {
        RequestQueue queue = Volley.newRequestQueue(context);
        JsonObjectRequest request = new JsonObjectRequest(URL,null,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response)
                    {
                        generateTransactionCollectionFromResponse(response);
                    }
                },new Response.ErrorListener()
        {

            @Override
            public void onErrorResponse(VolleyError volleyError)
            {

            }
        })
        {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("BEARER", "55b885274e7912280095ef80ac1cb937:d8922b44-75af-4810-a87e-77adcf433cfd:760000000");
                return headers;
            }
        };
        queue.add(request);
    }


    /*
    Add all transaction items to transaction list.
     */
    private void generateTransactionCollectionFromResponse(JSONObject response) {
        transactionList.clear();
        JSONArray transactionsJson = null;
        try {
            transactionsJson = response.getJSONArray("transactions");
            Log.d("TransactionsJson:", transactionsJson.toString());

            for (int i = 0; i < transactionsJson.length(); i++) {
                JSONObject transactionsJsonJSONObject = transactionsJson.getJSONObject(i);
                TransactionItem transactionItem = new TransactionItem
                                                        (
                                                          transactionsJsonJSONObject.getString("transactionDate"),
                                                          transactionsJsonJSONObject.getString("name"),
                                                          transactionsJsonJSONObject.getString("amount"),
                                                          transactionsJsonJSONObject.getJSONObject("category").getString("groupName"));
                transactionList.add(transactionItem);
            }

            viewTransactionListAdapter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
