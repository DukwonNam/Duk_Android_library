package com.duk.lab.android.qrcode;

import android.app.Fragment;

import com.duk.lab.android.common.CommonFragmentActivity;

/**
 * Created by dukwonnam on 2017. 1. 9..
 */

public class QRCodeWriterActivity extends CommonFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new QRCodeWriterFragment();
    }
}
