package com.example.deekshasharma.pennyapp.Collections;

import android.content.Context;
import android.util.Log;
import android.widget.ArrayAdapter;
import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.deekshasharma.pennyapp.model.BudgetItem;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BudgetsEndPoint {

    private final String url = "https://api-pennyapp.rhcloud.com/rest/members/d8922b44-75af-4810-a87e-77adcf433cfd/budgetSummaries/2015/3";
    public static List<BudgetItem> budgetItemList = new ArrayList<>();
    private ArrayAdapter budgetListAdapter;
    private Context context;
    private String year;
    private String month;


    public BudgetsEndPoint(Context context, ArrayAdapter budgetListAdapter)
    {
        this.context = context;
        this.budgetListAdapter = budgetListAdapter;
        getBudgetItemsFromServer();
    }

    /*
    Get all budget items from Server
     */
    private void getBudgetItemsFromServer()
    {
        RequestQueue queue = Volley.newRequestQueue(context);
        JsonObjectRequest request = new JsonObjectRequest(url,null,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response)
                    {
                        createBudgetCollectionFromResponse(response);
                        Log.d("response", response.toString());
                    }
                },new Response.ErrorListener()
        {

            @Override
            public void onErrorResponse(VolleyError volleyError)
            {
                Log.e("/GET Budget Summary", volleyError.toString());
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
        queue.add(request);}


    /*
    Add all Budget items to the list
     */
    private void createBudgetCollectionFromResponse(JSONObject response)
    {
        budgetItemList.clear();
        try
        {
            year = response.getString("year");
            month = response.getString("month");
            JSONArray distribution = response.getJSONArray("distributions");
            for(int i = 0; i < distribution.length(); i++)
            {
                JSONObject object = distribution.getJSONObject(i);
                BudgetItem item = new BudgetItem(object);
                budgetItemList.add(item);
            }
            budgetListAdapter.notifyDataSetChanged();
        }catch(JSONException exception)
        {
            exception.printStackTrace();
        }
    }





}
