package com.gguzman.android.ggcoupons;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Class to manage and show subcategories in the app
 * @author Gabriel Guzmán
 * @version 1.0
 * @since v1.2016.04.03
 * @see android.support.v7.app.AppCompatActivity
 */
public class Subcategories extends AppCompatActivity{

    private String subCtgURL = "http://api.8coupons.com/v1/getsubcategory";
    private ListView subCtgListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String ctgId = getIntent().getStringExtra("ctgId");
        String ctgName = getIntent().getStringExtra("ctgName");

        this.setTitle(ctgName);
        setContentView(R.layout.subcategories);

        JSONArray jsonSubcategories = CouponsRequest.requestWebService(subCtgURL);

        ArrayList<Subcategory> subcategoriesList = new ArrayList<>();

        try {
            if (jsonSubcategories != null) {
                for (int i = 0; i < jsonSubcategories.length(); i++) {
                    JSONObject jsonSubcategory = jsonSubcategories.getJSONObject(i);
                    if (ctgId.equals(jsonSubcategory.getString("categoryID"))){
                        Subcategory subcategory = new Subcategory();
                        subcategory.setSubcatID(jsonSubcategory.getString("subcategoryID"));
                        subcategory.setSubcatName(jsonSubcategory.getString("subcategory"));
                        subcategoriesList.add(subcategory);
                    }
                }
            }
        } catch (JSONException e) {
            // handle exception
            Log.e("for", e.getMessage());
        }

        subCtgListView = (ListView)findViewById(R.id.subCtgListView);
        subCtgListView.setAdapter(new SubcategoryAdapter(Subcategories.this, subcategoriesList));

    }
}