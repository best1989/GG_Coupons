package com.android.gguzman.ggcoupons;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Coupons app based in the 8Coupons API.
 * @author Gabriel Guzmán
 * @version 1.0
 * @since v1.2016.03.22
 * @see android.support.v7.app.AppCompatActivity
 */
public class MainActivity extends AppCompatActivity {

    ListView ctgListView;
    int[] imgIDs;
    private final String CTGURL = "http://api.8coupons.com/v1/getcategory";
    private final String DOTDUSER = "18381";
    //private final String ECOUPONSKEY="27426ada5e0c4576c6cbaadd8042c1192b4de941374ec9fc0839f1e3f7ef94a18631c0bf9f5580262a292910aec5ce11";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get the shared preferences
        SharedPreferences preferences =  getSharedPreferences("my_preferences", MODE_PRIVATE);

        // Check if onboarding_complete is false
        if(!preferences.getBoolean("onboarding_complete",false)) {
            // Start the onboarding Activity
            Intent onboarding = new Intent(this, OnboardingActivity.class);
            startActivity(onboarding);

            // Close the main Activity
            finish();
            return;
        }

        this.setTitle(R.string.main_act_title);
        setContentView(R.layout.activity_main);

        //Int array with the IDs of the images for each category
        imgIDs = new int[]{R.drawable.ctg_1, R.drawable.ctg_2, R.drawable.ctg_3,
                R.drawable.ctg_4, R.drawable.ctg_6, R.drawable.ctg_7};

        JSONArray jsonCategories = CouponsRequest.requestWebService(CTGURL);
        ArrayList<Category> categoriesList = new ArrayList<>();

        try {
            if (jsonCategories != null) {
                for (int i = 0; i < jsonCategories.length(); i++) {
                    JSONObject jsonCategory = jsonCategories.getJSONObject(i);
                    Category category = new Category();
                    category.setCatID(jsonCategory.getString("categoryID"));
                    category.setCatName(jsonCategory.getString("category"));
                    categoriesList.add(category);
                }
            }
        } catch (JSONException e) {
            // handle exception
            Log.e("for",e.getMessage());
        }

        ctgListView = (ListView)findViewById(R.id.categories);
        ctgListView.setAdapter(new CategoryAdapter(MainActivity.this, categoriesList,imgIDs));
        /*Empty state when the list is empty
          GG 2016.03.30
          */
        ctgListView.setEmptyView(findViewById(R.id.emptyCtg));

        /*When a category is selected the subcategories activity starts, using the catName
        for the title and the catID to filter the list
         */
        ctgListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent subCtgIntent = new Intent(MainActivity.this,Subcategories.class);
                Category selCtg = (Category) ctgListView.getItemAtPosition(position);

                subCtgIntent.putExtra("ctgId", selCtg.getCatID());
                subCtgIntent.putExtra("ctgName",selCtg.getCatName());

                startActivity(subCtgIntent);
            }
        });
    }
}
