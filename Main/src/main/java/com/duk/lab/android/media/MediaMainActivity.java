package com.duk.lab.android.media;

import android.app.Fragment;

import com.duk.lab.android.common.CommonFragmentActivity;

/**
 * Created by dukwonnam on 2017. 1. 19..
 */

public class MediaMainActivity extends CommonFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new MediaMainFragment();
    }
}
