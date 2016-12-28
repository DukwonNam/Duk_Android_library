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

import com.duk.lab.android.calendar.CalendarActivity;
import com.duk.lab.android.camera.CameraActivity;
import com.duk.lab.android.connection.ConnectionActivity;

/**
 * Created by Duk on 2016-12-13.
 */

public class MainFragment extends Fragment implements View.OnClickListener {
    private static final int[] CLICK_EVENT_ID_ARRAY = new int[] {
            R.id.calendarButton,
            R.id.cameraButton,
            R.id.connectionButton
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        final View view = inflater.inflate(R.layout.main, container, false);
        for (int resId : CLICK_EVENT_ID_ARRAY) {
            view.findViewById(resId).setOnClickListener(this);
        }

        return view;
    }

    @Override
    public void onClick(View v) {
        if (v == null) {
            return;
        }

        switch (v.getId()) {
            case R.id.calendarButton:
                jumpToCalendar();
                break;
            case R.id.cameraButton:
                jumpToCamera();
                break;
            case R.id.connectionButton:
                jumpToConnection();
                break;
        }
    }

    private void jumpToCalendar() {
        Intent intent = new Intent();
        intent.setClass(getActivity(), CalendarActivity.class);
        try {
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            // Do nothing
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

    private void jumpToConnection() {
        Intent intent = new Intent();
        intent.setClass(getActivity(), ConnectionActivity.class);
        try {
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            // Do nothing
        }
    }
}
