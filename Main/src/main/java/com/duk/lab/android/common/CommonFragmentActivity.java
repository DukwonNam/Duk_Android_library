package com.duk.lab.android.common;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.duk.lab.android.R;

/**
 * Created by Duk on 2016-12-13.
 */

public abstract class CommonFragmentActivity extends AppCompatActivity {
    private final Fragment mFragment = createFragment();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_activity);
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.fragment_position, mFragment);
        transaction.commit();
    }

    protected Fragment getFragment() {
        return mFragment;
    }

    protected abstract Fragment createFragment();
}
