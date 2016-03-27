package com.android.gguzman.ggcoupons;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
    private String ctgURL = "http://api.8coupons.com/v1/getcategory";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*To avoid problems with HttpUrlConnection
          GG 2016.03.27
         */
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        //Int array with the IDs of the images for each category
        imgIDs = new int[]{R.drawable.ctg_1, R.drawable.ctg_2, R.drawable.ctg_3,
                R.drawable.ctg_4, R.drawable.ctg_6, R.drawable.ctg_7};

        JSONArray jsonCategories = CouponsRequest.requestWebService(ctgURL);
        ArrayList<Categories> categoriesList = new ArrayList<>();

        try {
            if (jsonCategories != null) {
                for (int i = 0; i < jsonCategories.length(); i++) {
                    JSONObject jsonCategory = jsonCategories.getJSONObject(i);
                    Categories category = new Categories();
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
        ctgListView.setAdapter(new CategoriesAdapter(MainActivity.this, categoriesList,imgIDs));
    }
}
