package com.duk.lab.android.weather;

import com.duk.lab.android.common.CommonFragmentActivity;

import android.app.Fragment;

public class WeatherMainActivity extends CommonFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new WeatherMainFragment();
    }
}
