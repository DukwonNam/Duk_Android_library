package com.duk.lab.android.notification;

import android.app.Fragment;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.duk.lab.android.R;
import com.duk.lab.android.util.CommonUtil;

/**
 * Created by dukwonnam on 2017. 1. 24..
 */

public class NotificationMainFragment extends Fragment implements View.OnClickListener {
    private static final int[] ON_CLICK_LISTENER_ID_ARRAY = new int[] {
            R.id.notificationBasic,
            R.id.notificationCustom
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        final View view = inflater.inflate(R.layout.notification_main, container, false);
        for (int resId : ON_CLICK_LISTENER_ID_ARRAY) {
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
            case R.id.notificationBasic:
                CommonUtil.jumpToActivity(getActivity(), NotificationBasicActivity.class);
                break;
            case R.id.notificationCustom:
                CommonUtil.jumpToActivity(getActivity(), NotificationCustomActivity.class);
                break;
        }
    }
}
