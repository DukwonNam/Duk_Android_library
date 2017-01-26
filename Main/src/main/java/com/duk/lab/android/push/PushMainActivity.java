package com.duk.lab.android.push;

import com.duk.lab.android.MainFragment;
import com.duk.lab.android.common.CommonFragmentActivity;

import android.app.Fragment;

public class PushMainActivity extends CommonFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new MainFragment();
    }
}
