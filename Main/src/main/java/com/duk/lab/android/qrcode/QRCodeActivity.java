package com.duk.lab.android.qrcode;

import android.app.Fragment;

import com.duk.lab.android.common.CommonFragmentActivity;

/**
 * Created by dukwonnam on 2017. 1. 6..
 */

public class QRCodeActivity extends CommonFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new QRCodeFragment();
    }
}
