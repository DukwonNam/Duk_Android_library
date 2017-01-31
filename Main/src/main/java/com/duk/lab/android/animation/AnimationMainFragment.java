package com.duk.lab.android.animation;

import com.duk.lab.android.R;

import android.app.Fragment;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by dukwonnam on 2017. 1. 24..
 */

public class AnimationMainFragment extends Fragment implements View.OnClickListener {
    private static final int[] ON_CLICK_LISTENER_ID_ARRAY = new int[] {
            R.id.toggleAnimation
    };

    private boolean mIsStarted;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        final View view = inflater.inflate(R.layout.animation_main, container, false);
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
            case R.id.toggleAnimation:
                if (mIsStarted) {
                    stopAnimation();
                } else {
                    startAnimation();
                }
                break;
        }
    }

    private void startAnimation() {
        final TextView tv = (TextView) getView().findViewById(R.id.marqueeText);
        tv.animate().rotation(100).setDuration(500);
    }

    private void stopAnimation() {
        final TextView tv = (TextView) getView().findViewById(R.id.marqueeText);
        tv.animate().rotation(0).setDuration(500);
    }
}
