package com.duk.lab.android.bluetooth;

import android.app.Fragment;

import com.duk.lab.android.MainFragment;
import com.duk.lab.android.common.CommonFragmentActivity;

public class BluetoothMainActivity extends CommonFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new BluetoothMainFragment();
    }
}
