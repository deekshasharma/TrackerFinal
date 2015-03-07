package com.example.deekshasharma.pennyapp.Collections;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.deekshasharma.pennyapp.model.AsyncResponse;
import com.example.deekshasharma.pennyapp.model.SummaryItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AllSummaryItemsEndPoint extends AsyncTask<Void, Void, String> {

    private final String url = "https://api-pennyapp.rhcloud.com/rest/monthlySummaries/d8922b44-75af-4810-a87e-77adcf433cfd/2015/03";
    public static List<SummaryItem> summaryItemList = new ArrayList<>();
    private ArrayAdapter summaryListAdapter;
    private Context context;
    private String year;
    private String month;
    private String totalSpent;

    public AsyncResponse delegate=null;


    public AllSummaryItemsEndPoint(Context context, ArrayAdapter summaryListAdapter)
    {
        this.context = context;
        this.summaryListAdapter = summaryListAdapter;
        getSummaryItemsFromServer();

    }

    @Override
    protected String doInBackground(Void... params) {
        return null;
    }

    @Override
    protected void onPostExecute(String result)
    {
        delegate.processFinish(result);
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


    /*
    Add all Summary items to the list
     */
    private void createSummaryCollectionFromResponse(JSONObject response)
    {
        summaryItemList.clear();
        try
        {
            year = response.getString("year");
            month = response.getString("month");
            totalSpent = response.getString("totalSpent");
            JSONArray distribution = response.getJSONArray("distributions");
            for(int i = 0; i < distribution.length(); i++)
            {
                JSONObject object = distribution.getJSONObject(i);
                System.out.println("JSOnObject at "+i +"is: " + object.toString());
                SummaryItem item = new SummaryItem(object.getString("group"), object.getString("spent"));
                summaryItemList.add(item);
            }
            summaryListAdapter.notifyDataSetChanged();
        }catch(JSONException exception)
        {
            exception.printStackTrace();
        }
    }

    public String getYear() {
        return year;
    }

    public String getMonth() {
        return month;
    }

    public String getTotalSpent() {
        return totalSpent;
    }
}
