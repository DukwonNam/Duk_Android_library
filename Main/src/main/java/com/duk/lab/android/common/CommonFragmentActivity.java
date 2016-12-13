package com.duk.lab.android.common;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import com.duk.lab.android.R;

/**
 * Created by Duk on 2016-12-13.
 */

public abstract class CommonFragmentActivity extends FragmentActivity {
    private final Fragment mFragment = getFragment();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_activity);
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.add(R.id.fragment_position, mFragment);
        transaction.commit();
    }

    protected abstract Fragment getFragment();
}
