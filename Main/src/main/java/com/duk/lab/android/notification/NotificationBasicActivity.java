package com.duk.lab.android.notification;

import com.duk.lab.android.common.CommonFragmentActivity;

import android.app.Fragment;

/**
 * Created by dukwonnam on 2017. 1. 19..
 */

public class NotificationBasicActivity extends CommonFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new NotificationBasicFragment();
    }
}
