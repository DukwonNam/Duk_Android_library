package com.duk.lab.android.calendar;

import android.app.Fragment;

import com.duk.lab.android.common.CommonFragmentActivity;

/**
 * Created by dukwonnam on 2017. 1. 19..
 */

public class CalendarMainActivity extends CommonFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new CalendarMainFragment();
    }
}
