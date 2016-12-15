package com.duk.lab.android.camera;

import android.hardware.Camera;

/**
 * Created by Duk on 2016-12-15.
 */

public class CameraUtil {
    public static Camera getCameraInstance() {
        Camera camera = null;

        try {
            camera = android.hardware.Camera.open();
        } catch (Exception e) {

        }

        return camera;
    }
}
