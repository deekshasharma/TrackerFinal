package com.example.deekshasharma.pennyapp.Collections;

import android.content.Context;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.deekshasharma.pennyapp.model.SummaryItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AllSummaryItemsEndPoint
{
    private final String url = "https://api-pennyapp.rhcloud.com/rest/monthlySummaries/d8922b44-75af-4810-a87e-77adcf433cfd/2015/02";
    public static List<SummaryItem> summaryItemList = new ArrayList<>();
    private ArrayAdapter summaryListAdapter;
    private Context context;

    public AllSummaryItemsEndPoint(Context context, ArrayAdapter summaryListAdapter)
    {
        this.context = context;
        this.summaryListAdapter = summaryListAdapter;
//        summaryItemList = new ArrayList<>();
        getSummaryItemsFromServer();

    }

    /*
    Get all summary items from Server
     */
    private void getSummaryItemsFromServer()
    {
        RequestQueue queue = Volley.newRequestQueue(context);
        JsonObjectRequest request = new JsonObjectRequest(url,null,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response)
                    {
                        createSummaryCollectionFromResponse(response);
                        Log.d("response", response.toString());
                    }
                },new Response.ErrorListener()
        {

            @Override
            public void onErrorResponse(VolleyError volleyError)
            {
                Log.e("/GET Summary", volleyError.toString());
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
        queue.add(request);    }


    private void createSummaryCollectionFromResponse(JSONObject response)
    {
        try
        {
            JSONArray distribution = response.getJSONArray("distributions");
            for(int i = 0; i < distribution.length(); i++)
            {
                JSONObject object = distribution.getJSONObject(i);
                Log.d("JSOnObject at "+i +"is: " , object.toString());
                SummaryItem item = new SummaryItem(object.getString("group"), object.getString("spent"));
                summaryItemList.add(item);
            }
            summaryListAdapter.notifyDataSetChanged();
        }catch(JSONException exception)
        {
            exception.printStackTrace();
        }
    }

    /*
    Return the list of all Summary Items
     */
    public List<SummaryItem> getSummaryItemList()
    {
        return this.summaryItemList;
    }




}
