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

/**
 * Created by dukwonnam on 2017. 1. 24..
 */

public class AnimationByJpgFragment extends Fragment implements View.OnClickListener {
    private static final int[] ON_CLICK_LISTENER_ID_ARRAY = new int[] {
            R.id.imageView
    };

    private AnimationDrawable mAnimationBackground;
    private ImageView mImageView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        final View view = inflater.inflate(R.layout.animation_image, container, false);
        for (int resId : ON_CLICK_LISTENER_ID_ARRAY) {
            view.findViewById(resId).setOnClickListener(this);
        }

        mImageView = (ImageView) view.findViewById(R.id.imageView);

        /*
         * Please set single Images first for java.lang.OutOfMemoryError
         */
        mImageView.setBackgroundResource(R.drawable.cat_0);

        return view;
    }

    @Override
    public void onClick(View v) {
        if (v == null) {
            return;
        }

        switch (v.getId()) {
            case R.id.imageView:
                if (mAnimationBackground == null) {
                    mImageView.setBackgroundResource(R.drawable.cat_ani);
                    mAnimationBackground = (AnimationDrawable) mImageView.getBackground();
                    mAnimationBackground.start();
                } else {
                    mAnimationBackground.stop();
                    mAnimationBackground = null;
                }
                break;
        }
    }
}
