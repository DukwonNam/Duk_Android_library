package com.duk.lab.android.qrcode;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.duk.lab.android.R;
import com.google.zxing.ResultPoint;
import com.google.zxing.client.android.Intents;
import com.journeyapps.barcodescanner.BarcodeCallback;
import com.journeyapps.barcodescanner.BarcodeResult;
import com.journeyapps.barcodescanner.CompoundBarcodeView;

import java.util.List;

/**
 * Created by dukwonnam on 2017. 1. 6..
 */

public class QRCodeScannerCustomFragment extends Fragment {
    private static String TAG = "QRCodeScannerCustomFragment";

    private CompoundBarcodeView barcodeView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.qrcode_scanner_custom, container, false);

        barcodeView = (CompoundBarcodeView) view.findViewById(R.id.barcode_scanner);
        barcodeView.decodeContinuous(barcodeCallback);
        Intent cameraSetting = new Intent();
        cameraSetting.putExtra(Intents.Scan.CAMERA_ID, 1);
        cameraSetting.putExtra(Intents.Scan.PROMPT_MESSAGE, "QR을 인식시켜 주세요.");
        barcodeView.initializeFromIntent(cameraSetting);

        return view;
    }

    private BarcodeCallback barcodeCallback = new BarcodeCallback() {
        @Override
        public void barcodeResult(BarcodeResult result) {
            if (result.getText() != null) {
                barcodeView.setStatusText(result.getText());
            }

            //Do something with code result
        }

        @Override
        public void possibleResultPoints(List<ResultPoint> resultPoints) {
        }
    };

    @Override
    public void onResume() {
        super.onResume();

        barcodeView.resume();
    }

    @Override
    public void onPause() {
        super.onPause();

        barcodeView.pause();
    }
}
