package com.duk.lab.android.qrcode;

import android.app.AlertDialog;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.duk.lab.android.R;
import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

/**
 * Created by dukwonnam on 2017. 1. 6..
 */

public class QRCodeScannerFragment extends Fragment implements ZXingScannerView.ResultHandler {
    private static String TAG = "QRCodeScannerFragment";

    private FrameLayout mQRcodeScannerLayout;
    private ZXingScannerView mScannerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.qrcode_scanner, container, false);

        mQRcodeScannerLayout = (FrameLayout)view.findViewById(R.id.qrcodeScannerLayout);
        mScannerView = new ZXingScannerView(getActivity());   // Programmatically initialize the scanner view
        mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.

        mQRcodeScannerLayout.addView(mScannerView);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        mScannerView.startCamera(1);         // Start camera
    }

    @Override
    public void onPause() {
        super.onPause();

        mScannerView.stopCamera();
    }

    @Override
    public void handleResult(Result rawResult) {
        // Do something with the result here

        Log.e("handler", rawResult.getText()); // Prints scan results
        Log.e("handler", rawResult.getBarcodeFormat().toString()); // Prints the scan format (qrcode)

        // show the scanner result into dialog box.
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Scan Result");
        builder.setMessage(rawResult.getText());
        AlertDialog alert1 = builder.create();
        alert1.show();

        // If you would like to resume scanning, call this method below:
        // mScannerView.resumeCameraPreview(this);
    }
}
