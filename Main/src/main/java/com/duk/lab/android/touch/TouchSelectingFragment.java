package com.duk.lab.android.touch;

import android.app.Fragment;
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

public class TouchSelectingFragment extends Fragment implements View.OnClickListener {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        final View view = inflater.inflate(R.layout.touch_selecting, container, false);

        return view;
    }

    @Override
    public void onClick(View view) {
        if (view == null) {
            return;
        }

        switch (view.getId()) {
        }
    }
}
