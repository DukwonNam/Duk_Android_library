package com.duk.lab.android.camera;

import android.app.Fragment;

import com.duk.lab.android.common.CommonFragmentActivity;

/**
 * Created by Duk on 2016-12-13.
 */

public class CameraActivity extends CommonFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new CameraFragment();
    }

}
