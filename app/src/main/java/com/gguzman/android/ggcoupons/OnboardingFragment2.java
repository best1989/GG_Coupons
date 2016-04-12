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
 * Fragment for the second screen of the onboarding. This fragment ask for the username
 * @author Gabriel Guzmán
 * @version 1.0
 * @since v1.2016.04.05
 * @see android.support.v4.app.Fragment
 */
public class OnboardingFragment2 extends Fragment{

    private EditText username;
    private onUsernameChangedListener mCallback;

    // Container OnboardingActivity must implement this interface
    public interface onUsernameChangedListener {
        void onUsernameChanged(String username);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle s) {

        View view = inflater.inflate(R.layout.ob_screen2, container, false);
        username = (EditText)view.findViewById(R.id.obUsername);

        /*When the user changes the text in the username EditText, this listener
         makes the callback to the method implemented in the OnboardingActivity
          */
        username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mCallback.onUsernameChanged(username.getText().toString());
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
            mCallback = (onUsernameChangedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement onUsernameChangedListener");
        }
    }
}

