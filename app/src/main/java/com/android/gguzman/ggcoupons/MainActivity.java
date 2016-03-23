package com.android.gguzman.ggcoupons;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    JSONObject ctgJSON;
    ListView ctgListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (android.os.Build.VERSION.SDK_INT > 9)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        CouponsRequest ctgReq = new CouponsRequest();
        JSONArray jsonCategories = ctgReq.requestWebService("http://api.8coupons.com/v1/getcategory");

        List<Categories> categoriesList = new ArrayList<Categories>();

        try {
            //JSONArray jsonCategories = resp.getJSONArray("getCategory");
            //Log.e("jsonCatg",jsonCategories.getJSONObject(0).getString("category"));
            for (int i = 0; i < jsonCategories.length(); i++) {
                JSONObject jsonCategory = jsonCategories.getJSONObject(i);

                Categories category = new Categories();
                category.setCatID(jsonCategory.getString("categoryID"));
                category.setCatName(jsonCategory.getString("category"));
                categoriesList.add(category);
            }

        } catch (JSONException e) {
            // handle exception
            Log.e("for",e.getMessage());
        }

        // This is the array adapter, it takes the context of the activity as a
        // first parameter, the type of list view as a second parameter and your
        // array as a third parameter.
        ArrayAdapter<Categories> ctgAdapter = new ArrayAdapter<Categories>(
                this,
                android.R.layout.simple_list_item_1,
                categoriesList );

        ctgListView = (ListView)findViewById(R.id.categories);
        ctgListView.setAdapter(ctgAdapter);


    }
}
