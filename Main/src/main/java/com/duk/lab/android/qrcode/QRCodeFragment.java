package com.duk.lab.android.qrcode;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.duk.lab.android.R;
import com.duk.lab.android.util.CommonUtil;
import com.google.zxing.client.android.Intents;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

/**
 * Created by dukwonnam on 2017. 1. 6..
 */

public class QRCodeFragment extends Fragment implements View.OnClickListener {
    private static String TAG = "QRCodeFragment";

    private static final int[] ON_CLICK_LISTENER_ID_ARRAY = new int[] {
            R.id.qrcodeScannerZxing,
            R.id.qrcodeScannerCustom2,
            R.id.qrcodeScannerCustom,
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
            case R.id.qrcodeScannerZxing:
                jumpToQRCodeScannerZxing();
                break;
            case R.id.qrcodeScannerCustom2:
                CommonUtil.jumpToActivity(getActivity(), QRCodeScannerCustom2Activity.class);
                break;
            case R.id.qrcodeScannerCustom:
                CommonUtil.jumpToActivity(getActivity(), QRCodeScannerCustomActivity.class);
                break;
            case R.id.qrcodeWriter:
                CommonUtil.jumpToActivity(getActivity(), QRCodeWriterActivity.class);
                break;
        }
    }

    private void jumpToQRCodeScannerZxing() {
        IntentIntegrator integrator = new IntentIntegrator(getActivity());
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
        Intent intent = integrator.createScanIntent();
        intent.putExtra(Intents.Scan.CAMERA_ID, 1); // Front camera

        startActivityForResult(intent, IntentIntegrator.REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i(TAG, "onActivityResult requestCode=" + requestCode + ", resultCode=" + resultCode + ", data=" + data);

        IntentResult qrResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (qrResult != null) {
            Log.i(TAG, "onActivityResult qrResult.getContents()=" + qrResult.getContents());
            Log.i(TAG, "onActivityResult qrResult.getErrorCorrectionLevel()=" + qrResult.getErrorCorrectionLevel());
            Log.i(TAG, "onActivityResult qrResult.getFormatName()=" + qrResult.getFormatName());
            Log.i(TAG, "onActivityResult qrResult.getOrientation()=" + qrResult.getOrientation());
            Log.i(TAG, "onActivityResult qrResult.getRawBytes()=" + qrResult.getRawBytes());
        }
    }
}
