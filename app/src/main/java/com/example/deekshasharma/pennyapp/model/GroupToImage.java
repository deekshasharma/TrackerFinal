package com.example.deekshasharma.pennyapp.model;

import com.example.deekshasharma.pennyapp.R;

import java.util.HashMap;
import java.util.Map;

public class GroupToImage {



    public static int getImage(String groupName)
    {
        return getMapping().get(groupName);
    }


    private static Map<String, Integer> getMapping () {
        Map<String, Integer>  groupToImageMap = new HashMap<String, Integer>()
        {{
            put("Food & Drink",R.drawable.food_drink);
            put("Business",R.drawable.business);
            put("Children",R.drawable.children);
            put("Culture",R.drawable.culture);
            put("Education",R.drawable.education);
            put("Financial",R.drawable.financial);
            put("Sports & Fitness",R.drawable.sports);
            put("Gifts & Donations",R.drawable.gifts);
            put("Health & Medical",R.drawable.health);
            put("Home",R.drawable.home);
            put("Income",R.drawable.income);
            put("Investment",R.drawable.investment);
            put("Legal",R.drawable.legal);
            put("Office",R.drawable.office);
            put("Personal",R.drawable.personal);
            put("Pets",R.drawable.pets);
            put("Technology",R.drawable.technology);
            put("Transportation",R.drawable.transportation);
            put("Travel",R.drawable.travel);
            put("Uncategorized",R.drawable.uncategorized);
            put("Utilities",R.drawable.utilities);

        }};
        return groupToImageMap;
    }

}
