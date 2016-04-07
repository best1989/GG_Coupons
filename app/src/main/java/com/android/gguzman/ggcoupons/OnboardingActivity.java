package com.android.gguzman.ggcoupons;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;

import com.ogaclejapan.smarttablayout.SmartTabLayout;

/**
 * Class that manages the onboarding for the app, through three fragments
 * @author Gabriel Guzmán
 * @version 1.0
 * @since v1.2016.04.05
 * @see android.support.v4.app.FragmentActivity
 */
public class OnboardingActivity extends FragmentActivity {

    private ViewPager pager;
    private SmartTabLayout indicator;
    private Button skip;
    private Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.onboarding);

        pager = (ViewPager)findViewById(R.id.obPager);
        indicator = (SmartTabLayout)findViewById(R.id.obIndicator);
        skip = (Button)findViewById(R.id.obSkip);
        next = (Button)findViewById(R.id.obNext);

        /* FragmentStatePagerAdapter is used to save the state of the fragment if it's killed
           between transitions. This way, the system uses less memory for each fragment, at the
           cost of possible overheat if the fragments are too heavy. getItem and getCount always
           have to be present.
         */
        FragmentStatePagerAdapter adapter = new FragmentStatePagerAdapter(getSupportFragmentManager()) {

            /**
             *
             * @param position
             * @return The required fragment
             */
            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0 : return new OnboardingFragment1();
                    case 1 : return new OnboardingFragment2();
                    case 2 : return new OnboardingFragment3();
                    default: return null;
                }
            }

            @Override
            public int getCount() {
                return 3;
            }
        };

        pager.setAdapter(adapter);
        indicator.setViewPager(pager);

        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishOnboarding();
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pager.getCurrentItem() == 2) { // The last screen
                    finishOnboarding();
                } else {
                    pager.setCurrentItem(
                            pager.getCurrentItem() + 1,
                            true
                    );
                }
            }
        });

        /* This listener allows to change the buttons at the bottom of the screen.
           If the user gets to the last page, the Skip button is dismissed and the text
           for Next button changes
         */
        indicator.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                if (position == 2) {
                    skip.setVisibility(View.GONE);
                    next.setText("Done");
                } else {
                    skip.setVisibility(View.VISIBLE);
                    next.setText("Next");
                }
            }
        });

    }

    /**
     * Method that completes the onboarding process and saves the status in the
     * shared preferences, to avoid showing the onboarding screens after the first time.
     */
    private void finishOnboarding() {
        // Get the shared preferences
        SharedPreferences preferences = getSharedPreferences("my_preferences", MODE_PRIVATE);

        // Set onboarding_complete to true
        preferences.edit().putBoolean("onboarding_complete",true).apply();

        // Launch the main Activity, called MainActivity
        Intent main = new Intent(this, MainActivity.class);
        startActivity(main);

        // Close the OnboardingActivity
        finish();
    }
}
