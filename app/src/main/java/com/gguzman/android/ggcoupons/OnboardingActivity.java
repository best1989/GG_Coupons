package com.gguzman.android.ggcoupons;

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
 * Class that manages the onboarding for the app, through three fragments. <br><br>
 *     It implements interfaces from two of the fragments to manage the callbacks
 *     and data input form the user.
 * @author Gabriel Guzmán
 * @version 1.0
 * @since v1.2016.04.05
 * @see android.support.v4.app.FragmentActivity
 * @see OnboardingFragment2
 * @see OnboardingFragment3
 */
public class OnboardingActivity extends FragmentActivity
        implements OnboardingFragment2.onUsernameChangedListener,
                   OnboardingFragment3.onZipcodeChangedListener {

    private ViewPager pager;
    private SmartTabLayout indicator;
    private Button skip;
    private Button next;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.onboarding);

        pager = (ViewPager)findViewById(R.id.obPager);
        indicator = (SmartTabLayout)findViewById(R.id.obIndicator);
        skip = (Button)findViewById(R.id.obSkip);
        next = (Button)findViewById(R.id.obNext);

        // Get the shared preferences
        preferences = getSharedPreferences("my_preferences", MODE_PRIVATE);

        /* FragmentStatePagerAdapter is used to save the state of the fragment if it's killed
           between transitions. This way, the system uses less memory for each fragment, at the
           cost of possible overheat if the fragments are too heavy. getItem and getCount always
           have to be present.
         */
        FragmentStatePagerAdapter adapter = new FragmentStatePagerAdapter(getSupportFragmentManager()) {

            /**
             *
             * @param position The position at the Pager
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
                    next.setText(R.string.obDoneButton);
                } else {
                    skip.setVisibility(View.VISIBLE);
                    next.setText(R.string.obNextButton);
                }
            }
        });
    }

    /**
     * Method that completes the onboarding process and saves the status in the
     * shared preferences, to avoid showing the onboarding screens after the first time.
     */
    private void finishOnboarding() {

        // Set onboarding_complete to true
        preferences.edit().putBoolean("onboarding_complete",true).apply();

        // Launch the main Activity, called MainActivity
        Intent main = new Intent(this, MainActivity.class);
        startActivity(main);

        // Close the OnboardingActivity
        finish();
    }

    /**
     * Implemented from the second fragment, saves the username at the Shared Preferences
     * @param username The ID that the user wrote
     */
    @Override
    public void onUsernameChanged(String username) {
        // Set username
        preferences.edit().putString("username",username).apply();
    }

    /**
     * Implemented from the third fragment, saves the zipcode at the Shared Preferences
     * @param zipcode The zipcode that the user wrote
     */
    @Override
    public void onZipcodeChanged(String zipcode) {
        // Set zipcode
        preferences.edit().putString("zipcode",zipcode).apply();
    }
}
