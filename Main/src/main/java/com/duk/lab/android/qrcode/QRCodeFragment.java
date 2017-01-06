package com.duk.lab.android.qrcode;

import android.app.Fragment;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.duk.lab.android.R;

/**
 * Created by dukwonnam on 2017. 1. 6..
 */

public class QRCodeFragment extends Fragment implements View.OnClickListener {

    private static final int[] ON_CLICK_LISTENER_ID_ARRAY = new int[] {
            R.id.qrcodeScanner,
            R.id.qrcodeWriter
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.qrcode_main, container, false);
        for (int id : ON_CLICK_LISTENER_ID_ARRAY) {
            View v = view.findViewById(id);
            if (v != null) {
                v.setOnClickListener(this);
            }
        }

        return view;
    }

    @Override
    public void onClick(View v) {
        if (v == null) {
            return;
        }

        switch (v.getId()) {
            case R.id.qrcodeScanner:
                jumpToQRCodeScanner();
                break;
            case R.id.qrcodeWriter:
                break;
        }
    }

    private void jumpToQRCodeScanner() {
        Intent intent = new Intent();
        intent.setClass(getActivity(), QRCodeScannerActivity.class);
        try {
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
            // Do nothing
        }
    }
}
