package com.duk.lab.android.camera;

import android.app.Fragment;
import android.hardware.Camera;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.duk.lab.android.R;

/**
 * Created by Duk on 2016-12-15.
 */

public class CameraViewFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.camera_view, container, false);
        FrameLayout cameraViewLayout = (FrameLayout) view.findViewById(R.id.cameraViewLayout);
        CameraView cameraView = new CameraView(getActivity());
        cameraViewLayout.addView(cameraView);

        return view;
    }
}
