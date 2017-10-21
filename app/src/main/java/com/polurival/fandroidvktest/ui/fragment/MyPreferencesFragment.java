package com.polurival.fandroidvktest.ui.fragment;

import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;

import com.polurival.fandroidvktest.R;

/**
 * Created by Polurival
 * on 17.10.2017.
 */

public class MyPreferencesFragment extends PreferenceFragment {

    public MyPreferencesFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }
}
