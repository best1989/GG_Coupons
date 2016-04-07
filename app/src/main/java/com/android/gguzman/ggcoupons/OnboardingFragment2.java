package com.android.gguzman.ggcoupons;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Fragment for the second screen of the onboarding
 * @author Gabriel Guzmán
 * @version 1.0
 * @since v1.2016.04.05
 * @see android.support.v4.app.Fragment
 */
public class OnboardingFragment2 extends Fragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle s) {
        return inflater.inflate(R.layout.ob_screen2, container, false);
    }
}

