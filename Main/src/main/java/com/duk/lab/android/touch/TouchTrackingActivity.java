package com.duk.lab.android.touch;

import android.app.Fragment;

import com.duk.lab.android.common.CommonFragmentActivity;

public class TouchTrackingActivity extends CommonFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new TouchTrackingFragment();
    }
}
