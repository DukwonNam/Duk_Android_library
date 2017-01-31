package com.duk.lab.android.connection.retrofit;

import com.duk.lab.android.common.CommonFragmentActivity;
import com.duk.lab.android.connection.ConnectionFragment;

import android.app.Fragment;

/**
 * Created by Duk on 2016-12-27.
 */

public class RetrofitActivity extends CommonFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new RetrofitFragment();
    }
}
