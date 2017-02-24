package com.duk.lab.android.touch;

import com.duk.lab.android.MainFragment;
import com.duk.lab.android.common.CommonFragmentActivity;

import android.app.Fragment;

public class TouchMainActivity extends CommonFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new TouchMainFragment();
    }
}
