package com.example.deekshasharma.pennyapp.model;

import android.app.ListFragment;
import android.content.Context;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Categories {
    public static List<CategoryItem> allCategories = Arrays.asList(new CategoryItem("name", "id", "groupName"));
//    private static ListFragment listFragment;

    private ArrayAdapter categoryListAdapter;
    private Context context;
    private String groupName;


//    public Categories(Context context, ListFragment lf, String groupName) {
//        this.context = context;
//        this.groupName = groupName;
//        listFragment = lf;
//        allCategories = new ArrayList<>();
//        getCategoriesFromServer(this.groupName);
//
//    }

    /////// Added to test with Activity
    public Categories(Context context,  String groupName, ArrayAdapter categoryListAdapter) {
        this.context = context;
        this.groupName = groupName;
        this.categoryListAdapter = categoryListAdapter;
        allCategories = new ArrayList<>();
        getCategoriesFromServer(this.groupName);

    }

    private void getCategoriesFromServer(String groupName) {
        RequestQueue queue = Volley.newRequestQueue(context);
        final String url = " https://api-pennyapp.rhcloud.com/rest/categories/groups/" + groupName.replace(" ", "%20");
        JsonArrayRequest arrayRequest = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        createCategoryCollectionFromJsonResponse(response);
                        System.out.println("GET /Categories:" + response.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("/GET Categories", error.toString());
            }
        }) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("BEARER", "55b885274e7912280095ef80ac1cb937:d8922b44-75af-4810-a87e-77adcf433cfd:760000000");
                return headers;
            }
        };

        queue.add(arrayRequest);
    }


    /*
    Parse the JSONArray response received from Server and add to the ArrayList<CategoryItem>
     */
    private void createCategoryCollectionFromJsonResponse(JSONArray response) {

        for (int i = 0; i < response.length(); i++) {
            try {
                JSONObject categoryJSON = response.getJSONObject(i);
                CategoryItem item = new CategoryItem(categoryJSON.getString("name"),
                        categoryJSON.getString("id"),
                        categoryJSON.getString("groupName"));
                allCategories.add(item);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
//        ((BaseAdapter) listFragment.getListView().getAdapter()).notifyDataSetChanged();
        categoryListAdapter.notifyDataSetChanged();


    }
}
