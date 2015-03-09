package com.example.deekshasharma.pennyapp.model;

import android.content.Context;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CategoryMapConverter {
    private final Map<String, Map<String, String>> categoriesMap = new HashMap<>();
    private static CategoryMapConverter converter;

    public static CategoryMapConverter getInstance(Context context) {
        if (converter == null) {
            converter = new CategoryMapConverter(context);
        }
        return converter;
    }

    private CategoryMapConverter (Context context) {
        generateCategoriesMapFromFile(context);
    }

    private void generateCategoriesMapFromFile(Context context)
    {
        try
        {
            final JSONArray array = new JSONArray(loadJSONFromAsset(context));
            for (int i = 0; i < array.length(); i++)
            {
                final JSONObject jsonObject = array.getJSONObject(i);
                addJsonToMap(jsonObject);
            }
        }catch (JSONException e)
        {
            e.printStackTrace();
        }

        System.out.println(categoriesMap);
    }

    private void addJsonToMap(final JSONObject jsonObject) {
        final Map<String, String> category = new HashMap<>();
        try
        {
            category.put(jsonObject.getString("name"), jsonObject.getString("id"));

            final String groupName = jsonObject.getString("groupName");
            if (categoriesMap.containsKey(groupName)) {
                categoriesMap.get(groupName).put(jsonObject.getString("name"), jsonObject.getString("id"));
            } else {
                categoriesMap.put(groupName, category);
            }
        }catch (JSONException e)
        {
            e.printStackTrace();
        }

    }

    private String loadJSONFromAsset(Context context) {
        String json = null;
        try {

			final InputStream is = context.getAssets().open("categories.json");
            final int size = is.available();
            final byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");

        } catch (final IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public Map<String, Map<String, String>> getCategoriesMap()
    {
        return categoriesMap;
    }

}
