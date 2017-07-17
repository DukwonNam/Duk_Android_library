package com.duk.lab.android.touch;

import android.app.AlertDialog;
import android.app.Fragment;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.duk.lab.android.R;

/**
 * Created by dukwonnam on 2017. 1. 24..
 */

public class TouchTrackingFragment extends Fragment implements View.OnClickListener {
    private static final int[] CLICK_EVENT_ID_ARRAY = new int[] {
            R.id.reset,
            R.id.confirm,
    };
    private TouchTrackingDrawer mTouchTrackingDrawer;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        final View view = inflater.inflate(R.layout.touch_tracking, container, false);

        for (int resId : CLICK_EVENT_ID_ARRAY) {
            view.findViewById(resId).setOnClickListener(this);
        }

        mTouchTrackingDrawer = (TouchTrackingDrawer)view.findViewById(R.id.touchTrackingDrawer);

        return view;
    }

    @Override
    public void onClick(View view) {
        if (view == null) {
            return;
        }

        switch (view.getId()) {
            case R.id.reset:
                if (mTouchTrackingDrawer != null) {
                    mTouchTrackingDrawer.resetTracked();
                }
                break;

            case R.id.confirm:
                if (mTouchTrackingDrawer != null) {
                    final Bitmap bitmap = mTouchTrackingDrawer.getTrackedBitmap();
                    final ImageView imageView = new ImageView(getActivity());
                    imageView.setImageBitmap(bitmap);

                    final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setView(imageView);
                    builder.create().show();
                }
                break;
        }
    }
}
