package com.duk.lab.android.calendar;

import android.app.Fragment;

import com.duk.lab.android.common.CommonFragmentActivity;

/**
 * Created by Duk on 2016-12-13.
 */

public class CalendarActivity extends CommonFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new CalendarFragment();
    }

}
