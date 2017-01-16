package com.duk.lab.android.camera;

import com.duk.lab.android.common.CommonFragmentActivity;

import android.app.Fragment;

/**
 * Created by Duk on 2016-12-15.
 */

public class CameraView2Activity extends CommonFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new CameraView2Fragment();
    }
}
