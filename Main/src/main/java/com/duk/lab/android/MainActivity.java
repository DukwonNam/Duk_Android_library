package com.duk.lab.android;

import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.duk.lab.android.common.CommonFragmentActivity;

public class MainActivity extends CommonFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new MainFragment();
    }
}
