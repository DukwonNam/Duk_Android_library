package com.duk.lab.android;

import android.app.Fragment;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.duk.lab.android.animation.AnimationMainActivity;
import com.duk.lab.android.calendar.CalendarActivity;
import com.duk.lab.android.camera.CameraActivity;
import com.duk.lab.android.connection.ConnectionActivity;
import com.duk.lab.android.notification.NotificationMainActivity;
import com.duk.lab.android.qrcode.QRCodeActivity;

/**
 * Created by Duk on 2016-12-13.
 */

public class MainFragment extends Fragment {
    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        final View view = inflater.inflate(R.layout.navigation_frame_main, container, false);

        mToolbar = (Toolbar) view.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(mToolbar);
        initNavigationDrawer(view);
        return view;
    }

    private void initNavigationDrawer(View view) {
        final NavigationView navigationView = (NavigationView)view.findViewById(R.id.navigation_view);
        mDrawerLayout = (DrawerLayout)view.findViewById(R.id.drawer);
        navigationView.setNavigationItemSelectedListener(mNavigationItemSelectedListener);

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(getActivity(), mDrawerLayout, mToolbar, R.string.drawer_open, R.string.drawer_close) {

            @Override
            public void onDrawerClosed(View v){
                super.onDrawerClosed(v);
            }

            @Override
            public void onDrawerOpened(View v) {
                super.onDrawerOpened(v);
            }
        };
        mDrawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }

    private void jumpToActivity(Class<?> cls) {
        Intent intent = new Intent();
        intent.setClass(getActivity(), cls);
        try {
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            // Do nothing
        }
    }

    private NavigationView.OnNavigationItemSelectedListener mNavigationItemSelectedListener = new NavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            int id = item.getItemId();

            switch (id){
                case R.id.menu_calendar:
                    jumpToActivity(CalendarActivity.class);
                    break;
                case R.id.menu_camera:
                    jumpToActivity(CameraActivity.class);
                    break;
                case R.id.menu_qrcode:
                    jumpToActivity(QRCodeActivity.class);
                    break;
                case R.id.menu_connection:
                    jumpToActivity(ConnectionActivity.class);
                    break;
                case R.id.menu_notification:
                    jumpToActivity(NotificationMainActivity.class);
                    break;
                case R.id.menu_animation:
                    jumpToActivity(AnimationMainActivity.class);
                    break;
            }
            mDrawerLayout.closeDrawers();
            return true;
        }
    };
}
