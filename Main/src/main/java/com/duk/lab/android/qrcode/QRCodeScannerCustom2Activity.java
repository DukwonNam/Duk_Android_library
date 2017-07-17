package com.duk.lab.android.qrcode;

import android.app.Fragment;

import com.duk.lab.android.common.CommonFragmentActivity;

/**
 * Created by dukwonnam on 2017. 1. 6..
 */

public class QRCodeScannerCustom2Activity extends CommonFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new QRCodeScannerCustom2Fragment();
    }
}
