package com.duk.lab.android.camera;

import com.duk.lab.android.R;

import android.app.Fragment;
import android.content.Context;
import android.content.res.Configuration;
import android.hardware.Camera;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;

import java.io.IOException;
import java.util.List;

/**
 * Created by Duk on 2016-12-15.
 */

public class CameraView2Fragment extends Fragment implements SurfaceHolder.Callback, View.OnClickListener {
    private static final String TAG = "CameraView2Fragment";

    private Camera mCamera;
    private int mCurrentCameraId;
    private SurfaceView mSurfaceView;
    private Button mCameraSwitch;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i("test_duk", "onCreateView");
        super.onCreateView(inflater, container, savedInstanceState);
        final View view = inflater.inflate(R.layout.camera_view2, container, false);

        mCameraSwitch = (Button) view.findViewById(R.id.cameraSwitch);
        mCameraSwitch.setOnClickListener(this);

        mSurfaceView = (SurfaceView) view.findViewById(R.id.cameraSurfaceView);
        final SurfaceHolder surfaceHolder = mSurfaceView.getHolder();
        surfaceHolder.addCallback(this);

        return view;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.i("test_duk", "onConfigurationChanged newConfig=" + newConfig);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Log.i("test_duk", "surfaceCreated holder=" + holder);

        try {
            mCamera = android.hardware.Camera.open(mCurrentCameraId);
        } catch (Exception e) {
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        Log.i("test_duk", "surfaceChanged width=" + width + ", height=" + height);
        if (holder.getSurface() == null || mCamera == null) {
            // preview surface does not exist
            return;
        }

        // Stop preview
        mCamera.stopPreview();

        startPreview(width, height);
    }

    private void setPreviewSize(int width, int height) {
        final Camera.Parameters cameraParams = mCamera.getParameters();
        final Camera.Size cameraSize = getBestMatchedSize(cameraParams.getSupportedPreviewSizes(), width, height);
        cameraParams.setPreviewSize(cameraSize.width, cameraSize.height);
        mCamera.setParameters(cameraParams);
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

    private void setDisplayOrientation() {
        final WindowManager wm = (WindowManager)getActivity().getSystemService(Context.WINDOW_SERVICE);
        int orientation = 0;
        final int windowRotation = wm.getDefaultDisplay().getRotation();
        Log.i("test_duk", "windowRotation=" + windowRotation);
        switch (windowRotation) {
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
        mCamera.setDisplayOrientation(orientation);
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        Log.i("test_duk", "surfaceDestroyed holder=" + holder);
        releaseCamera();
    }

    @Override
    public void onClick(View v) {
        if (v == null) {
            return;
        }

        switch (v.getId()) {
            case R.id.cameraSwitch: {
                switchCamera();
                break;
            }
        }
    }

    private void startPreview(int width, int height) {
        setPreviewSize(width, height);
        setDisplayOrientation();
        try {
            mCamera.setPreviewDisplay(mSurfaceView.getHolder());
            mCamera.startPreview();
        } catch (IOException e) {
            Log.d(TAG, "Error setting camera preview: " + e.getMessage());
        }
    }

    private void releaseCamera() {
        if (mCamera == null) {
            return;
        }

        mCamera.stopPreview();
        mCamera.release();
        mCamera = null;
    }

    private void switchCamera() {
        releaseCamera();

        final int cameraCount = Camera.getNumberOfCameras();
        mCurrentCameraId = (mCurrentCameraId + 1) % cameraCount;

        try {
            mCamera = android.hardware.Camera.open(mCurrentCameraId);
        } catch (Exception e) {
        }

        startPreview(mSurfaceView.getWidth(), mSurfaceView.getHeight());
    }
}
