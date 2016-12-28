package com.duk.lab.android.connection;

import android.app.Fragment;

import com.duk.lab.android.common.CommonFragmentActivity;

/**
 * Created by Duk on 2016-12-27.
 */

public class ConnectionActivity extends CommonFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new ConnectionFragment();
    }
}
