package com.duk.lab.android.media;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.duk.lab.android.R;
import com.duk.lab.android.notification.NotificationBasicActivity;
import com.duk.lab.android.notification.NotificationCustomActivity;
import com.duk.lab.android.util.CommonUtil;

/**
 * Created by dukwonnam on 2017. 1. 24..
 */

public class MediaMainFragment extends Fragment implements View.OnClickListener {
    private static final int[] ON_CLICK_LISTENER_ID_ARRAY = new int[] {
            R.id.musicPlayer,
            R.id.videoPlayer
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        final View view = inflater.inflate(R.layout.media_main, container, false);
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
            case R.id.videoPlayer:
                CommonUtil.jumpToActivity(getActivity(), VideoPlayerActivity.class);
                break;
        }
    }
}
