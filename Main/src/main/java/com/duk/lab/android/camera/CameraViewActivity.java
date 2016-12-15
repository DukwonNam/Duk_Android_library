package com.duk.lab.android.camera;

import android.app.Fragment;

import com.duk.lab.android.common.CommonFragmentActivity;

/**
 * Created by Duk on 2016-12-15.
 */

public class CameraViewActivity extends CommonFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new CameraViewFragment();
    }
}
