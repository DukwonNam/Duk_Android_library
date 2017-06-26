package com.duk.lab.android.qrcode;

import android.app.Fragment;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.duk.lab.android.R;
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
            R.id.qrcodeScannerFullscreen,
            R.id.qrcodeScanner,
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
            case R.id.qrcodeScannerFullscreen:
                jumpToQRCodeScannerFullscreen();
                break;
            case R.id.qrcodeScanner:
                jumpToQRCodeScanner();
                break;
            case R.id.qrcodeScannerCustom:
                jumpToQRCodeScannerCustom();
                break;
            case R.id.qrcodeWriter:
                jumpToQRCodeWriter();
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

    private void jumpToQRCodeScannerFullscreen() {
        Intent intent = new Intent();
        intent.setClass(getActivity(), QRCodeScannerFullscreenActivity.class);
        try {
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
            // Do nothing
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

    private void jumpToQRCodeScannerCustom() {
        Intent intent = new Intent();
        intent.setClass(getActivity(), QRCodeScannerCustomActivity.class);
        try {
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
            // Do nothing
        }
    }

    private void jumpToQRCodeWriter() {
        Intent intent = new Intent();
        intent.setClass(getActivity(), QRCodeWriterActivity.class);
        try {
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
            // Do nothing
        }
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
