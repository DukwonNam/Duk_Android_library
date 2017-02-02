package com.duk.lab.android.alarm;

import com.duk.lab.android.animation.AnimationMainFragment;
import com.duk.lab.android.common.CommonFragmentActivity;

import android.app.Fragment;

/**
 * Created by dukwonnam on 2017. 1. 19..
 */

public class AlarmMainActivity extends CommonFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new AlarmMainFragment();
    }
}
