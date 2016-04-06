package com.android.gguzman.ggcoupons;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Gabriel on 05/04/2016.
 */

public class OnboardingFragment1 extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle s) {
        return inflater.inflate(R.layout.ob_screen1, container, false);
    }
}

