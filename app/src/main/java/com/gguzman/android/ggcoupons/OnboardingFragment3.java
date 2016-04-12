package com.gguzman.android.ggcoupons;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

/**
 * Fragment for the third screen of the onboarding
 * @author Gabriel Guzmán
 * @version 1.0
 * @since v1.2016.04.05
 * @see android.support.v4.app.Fragment
 */
public class OnboardingFragment3 extends Fragment{

    private EditText zipcode;
    onZipcodeChangedListener mCallback;

    // Container OnboardingActivity must implement this interface
    public interface onZipcodeChangedListener {
        void onZipcodeChanged(String zipcode);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle s) {
        View view = inflater.inflate(R.layout.ob_screen3, container, false);
        zipcode = (EditText)view.findViewById(R.id.obCity);

        /*When the user changes the text in the zipcode EditText, this listener
         makes the callback to the method implemented in the OnboardingActivity
          */
        zipcode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mCallback.onZipcodeChanged(zipcode.getText().toString());
            }
        });

        return view;
    }

    /**
     * For validation purposes, to check that the activity implements the callback
     * @param activity The container activity for the fragment
     */
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallback = (onZipcodeChangedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement onZipcodeChangedListener");
        }
    }
}

