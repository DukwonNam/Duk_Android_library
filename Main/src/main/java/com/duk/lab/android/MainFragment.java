package com.duk.lab.android;

import android.app.Fragment;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.duk.lab.android.camera.CameraActivity;

/**
 * Created by Duk on 2016-12-13.
 */

public class MainFragment extends Fragment implements View.OnClickListener {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        final View view = inflater.inflate(R.layout.main, container, false);
        final Button cameraBotton = (Button) view.findViewById(R.id.cameraBotton);
        if (cameraBotton != null) {
            cameraBotton.setOnClickListener(this);
        }

        return view;
    }

    @Override
    public void onClick(View v) {
        if (v == null) {
            return;
        }

        switch (v.getId()) {
            case R.id.cameraBotton:
                jumpToCamera();
                break;
        }
    }

    private void jumpToCamera() {
        Intent intent = new Intent();
        intent.setClass(getActivity(), CameraActivity.class);
        try {
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            // Do nothing
        }
    }
}
