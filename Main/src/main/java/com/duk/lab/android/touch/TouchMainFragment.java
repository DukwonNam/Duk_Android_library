package com.duk.lab.android.touch;

import com.duk.lab.android.R;
import com.duk.lab.android.util.CommonUtil;

import android.app.AlertDialog;
import android.app.Fragment;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by dukwonnam on 2017. 1. 24..
 */

public class TouchMainFragment extends Fragment implements View.OnClickListener {
    private static final int[] CLICK_EVENT_ID_ARRAY = new int[] {
            R.id.touchTracking,
            R.id.touchSelecting,
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        final View view = inflater.inflate(R.layout.touch_main, container, false);

        for (int resId : CLICK_EVENT_ID_ARRAY) {
            view.findViewById(resId).setOnClickListener(this);
        }

        return view;
    }

    @Override
    public void onClick(View view) {
        if (view == null) {
            return;
        }

        switch (view.getId()) {
            case R.id.touchTracking:
                CommonUtil.jumpToActivity(getActivity(), TouchTrackingActivity.class);
                break;

            case R.id.touchSelecting:
                CommonUtil.jumpToActivity(getActivity(), TouchTrackingActivity.class);
                break;
        }
    }
}
