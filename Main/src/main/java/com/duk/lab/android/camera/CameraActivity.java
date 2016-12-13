package com.duk.lab.android.camera;

import android.app.Fragment;
import android.content.Intent;
import android.support.annotation.NonNull;

import com.duk.lab.android.common.CommonFragmentActivity;

/**
 * Created by Duk on 2016-12-13.
 */

public class CameraActivity extends CommonFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new CameraFragment();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
