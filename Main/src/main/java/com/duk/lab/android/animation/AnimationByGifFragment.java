package com.duk.lab.android.animation;

import com.duk.lab.android.R;

import android.app.Fragment;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by dukwonnam on 2017. 1. 24..
 */

public class AnimationByGifFragment extends Fragment implements View.OnClickListener {
    private static final int[] ON_CLICK_LISTENER_ID_ARRAY = new int[] {
            R.id.gifView
    };

    private AnimationDrawable mAnimationBackground;
    private GifMovieView mGifMovieView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        final View view = inflater.inflate(R.layout.animation_gif, container, false);
//        for (int resId : ON_CLICK_LISTENER_ID_ARRAY) {
//            view.findViewById(resId).setOnClickListener(this);
//        }

        mGifMovieView = (GifMovieView) view.findViewById(R.id.gifView);
        mGifMovieView.setGifImage(R.drawable.giphy);

        return view;
    }

    @Override
    public void onClick(View v) {
        if (v == null) {
            return;
        }

        switch (v.getId()) {
            case R.id.gifView:
                break;
        }
    }
}
