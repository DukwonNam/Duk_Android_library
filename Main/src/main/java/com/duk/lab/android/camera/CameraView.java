package com.duk.lab.android.camera;

import android.content.Context;
import android.content.res.Configuration;
import android.hardware.Camera;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;

import java.io.IOException;

/**
 * Created by Duk on 2016-12-15.
 */

public class CameraView extends SurfaceView implements SurfaceHolder.Callback {
    private static final String TAG = "CameraView";

    private SurfaceHolder mHolder;
    private Camera mCamera;

    public CameraView(Context context) {
        super(context);
        initViews();
    }

    public CameraView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViews();
    }

    public CameraView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViews();
    }

    private void initViews() {
        mHolder = getHolder();
        mHolder.addCallback(this);
        mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Log.i("test_duk", "surfaceCreated holder=" + holder);
        mCamera = CameraUtil.getCameraInstance();
        if (mCamera == null) {
            return;
        }

        try {
            mCamera.setPreviewDisplay(holder);
            mCamera.startPreview();
        } catch (IOException e) {
            Log.d(TAG, "Error setting camera preview: " + e.getMessage());
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        Log.i("test_duk", "surfaceChanged width=" + width + ", height=" + height);
        if (mHolder.getSurface() == null || mCamera == null) {
            // preview surface does not exist
            return;
        }

        mCamera.stopPreview();

        // set preview size and make any resize, rotate or
        // reformatting changes here

        // start preview with new settings
        try {
            WindowManager wm = (WindowManager)getContext().getSystemService(Context.WINDOW_SERVICE);
            int orientation = 0;
            switch (wm.getDefaultDisplay().getRotation()) {
                case Surface.ROTATION_90:
                    orientation = 90;
                    break;
                case Surface.ROTATION_180:
                    orientation = 180;
                    break;
                case Surface.ROTATION_270:
                    orientation = 270;
                    break;
                case Surface.ROTATION_0:
                default:
                    orientation = 0;
                    break;
            }
            mCamera.setDisplayOrientation(orientation);
            mCamera.setPreviewDisplay(mHolder);
            mCamera.startPreview();
        } catch (IOException e){
            Log.d(TAG, "Error starting camera preview: " + e.getMessage());
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        Log.i("test_duk", "surfaceDestroyed holder=" + holder);
        if (mCamera == null) {
            return;
        }

        mCamera.stopPreview();
        mCamera.release();
        mCamera = null;
    }
}
