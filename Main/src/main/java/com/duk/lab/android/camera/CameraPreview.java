package com.duk.lab.android.camera;

import android.content.Context;
import android.hardware.Camera;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.ViewGroup;
import android.view.WindowManager;

import java.io.IOException;
import java.util.List;

/**
 * Created by Duk on 2016-12-15.
 */

public class CameraPreview extends SurfaceView implements SurfaceHolder.Callback {
    private static final String TAG = "CameraPreview";

    private SurfaceHolder mHolder;
    private Camera mCamera;

    public CameraPreview(Context context) {
        super(context);
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
        Log.i("test_duk", "getWidth()=" + getWidth() + ", getHeight()=" + getHeight());
        if (mHolder.getSurface() == null || mCamera == null) {
            // preview surface does not exist
            return;
        }

        mCamera.stopPreview();

        final Camera.Parameters cameraParams = mCamera.getParameters();
        List<Camera.Size> supportedSizeList = cameraParams.getSupportedPreviewSizes();
//        supportedSizeList = cameraParams.getSupportedPictureSizes();
        Camera.Size size = getBestMatchedSize(supportedSizeList, width, height);
        if (width != size.width || height != size.height) {
            final ViewGroup.LayoutParams viewParams = getLayoutParams();
            viewParams.height = size.height;
            viewParams.width = size.width;
            setLayoutParams(viewParams);
            invalidate();
            return;
        }

        Camera.Size defaultSize = cameraParams.getPreviewSize();
        double ratio = ((double) defaultSize.height) / defaultSize.width;
        Log.i("test_duk", "ratio=" + ratio);
//        cameraParams.setPreviewSize(width, (int)(height * ratio));
        cameraParams.setPreviewSize(width, height);
        cameraParams.setPictureSize(width, height);
        mCamera.setParameters(cameraParams);

        // set preview size and make any resize, rotate or
        // reformatting changes here

        // start preview with new settings
        try {
            final WindowManager wm = (WindowManager)getContext().getSystemService(Context.WINDOW_SERVICE);
            int orientation = 0;
            switch (wm.getDefaultDisplay().getRotation()) {
                // Landscape on left side bottom
                case Surface.ROTATION_90:
                    orientation = 0;
                    break;
                // Landscape on right side bottom
                case Surface.ROTATION_270:
                    orientation = 180;
                    break;
                // Portrait
                case Surface.ROTATION_0:
                case Surface.ROTATION_180:
                default:
                    orientation = 90;
                    break;
            }
            Log.i("test_duk", "wm.getDefaultDisplay().getRotation()=" + wm.getDefaultDisplay().getRotation());
            Log.i("test_duk", "orientation=" + orientation);
            mCamera.setDisplayOrientation(orientation);
            mCamera.setPreviewDisplay(mHolder);
            mCamera.startPreview();
        } catch (IOException e){
            Log.d(TAG, "Error starting camera preview: " + e.getMessage());
        }
    }

    private Camera.Size getBestMatchedSize(List<Camera.Size> supportedSizeList, int width, int height) {
        Log.i("test_duk", "getBestMatchedSize width=" + width + ", height" + height);
        for (Camera.Size item :supportedSizeList) {
            Log.i("test_duk", "item.width=" + item.width + ", item.height" + item.height);
        }
        for (Camera.Size item :supportedSizeList) {
            if (item.width <= width && item.height <= height) {
                Log.i("test_duk", "bestMatched!! item.width=" + item.width + ", item.height" + item.height);
                return item;
            }
        }
        return null;
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
