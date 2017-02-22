package com.duk.lab.android.animation;

import com.duk.lab.android.common.CommonFragmentActivity;

import android.app.Fragment;

/**
 * Created by dukwonnam on 2017. 1. 19..
 */

public class AnimationByGifActivity extends CommonFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new AnimationByGifFragment();
    }
}
