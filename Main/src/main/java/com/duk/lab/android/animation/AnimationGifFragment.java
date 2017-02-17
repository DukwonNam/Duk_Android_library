package com.duk.lab.android.animation;

import com.duk.lab.android.R;

import android.app.Fragment;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by dukwonnam on 2017. 1. 24..
 */

public class AnimationGifFragment extends Fragment implements View.OnClickListener {
    private static final int[] ON_CLICK_LISTENER_ID_ARRAY = new int[] {
            R.id.gifImageView
    };

    private AnimationDrawable mGifBackground;
    private ImageView mGifImageView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        final View view = inflater.inflate(R.layout.animation_gif, container, false);
        for (int resId : ON_CLICK_LISTENER_ID_ARRAY) {
            view.findViewById(resId).setOnClickListener(this);
        }

        mGifImageView = (ImageView) view.findViewById(R.id.gifImageView);

        /*
         * Please set single Images first for java.lang.OutOfMemoryError
         */
        mGifImageView.setBackgroundResource(R.drawable.cat_0);

        return view;
    }

    @Override
    public void onClick(View v) {
        if (v == null) {
            return;
        }

        switch (v.getId()) {
            case R.id.gifImageView:
                if (mGifBackground == null) {
                    mGifImageView.setBackgroundResource(R.drawable.cat_ani);
                    mGifBackground = (AnimationDrawable) mGifImageView.getBackground();
                    mGifBackground.start();
                } else {
                    mGifBackground.stop();
                    mGifBackground = null;
                }
                break;
        }
    }
}
