package com.duk.lab.android.qrcode;

import android.app.AlertDialog;
import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.duk.lab.android.R;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

/**
 * Created by dukwonnam on 2017. 1. 9..
 */

public class QRCodeWriterFragment extends Fragment implements View.OnClickListener {

    private static final int[] ON_CLICK_LISTENER_ID_ARRAY = new int[] {
            R.id.generateQRcode,
    };
    private EditText mQRCodeContentView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.qrcode_writer, container, false);
        for (int id : ON_CLICK_LISTENER_ID_ARRAY) {
            View v = view.findViewById(id);
            if (v != null) {
                v.setOnClickListener(this);
            }
        }
        mQRCodeContentView = (EditText)view.findViewById(R.id.qrcodeContents);

        return view;
    }

    @Override
    public void onClick(View v) {
        if (v == null) {
            return;
        }

        switch (v.getId()) {
            case R.id.generateQRcode:
                showQRCodePopup();
                break;
        }
    }

    private void showQRCodePopup() {
        final Editable editable = mQRCodeContentView == null ? null : mQRCodeContentView.getText();
        final String contents = editable == null ? null : editable.toString();
        if (TextUtils.isEmpty(contents)) {
            return;
        }

        final QRCodeWriter writer = new QRCodeWriter();
        BitMatrix bitMatrix = null;
        try {
            bitMatrix  = writer.encode(contents, BarcodeFormat.QR_CODE, 400, 400);
        } catch (WriterException e) {
        }

        Bitmap bmp = null;
        if (bitMatrix != null) {
            int height = bitMatrix.getHeight();
            int width = bitMatrix.getWidth();
            bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
            for (int x = 0; x < width; x++){
                for (int y = 0; y < height; y++){
                    bmp.setPixel(x, y, bitMatrix.get(x,y) ? Color.BLACK : Color.WHITE);
                }
            }
        }

        if (bmp == null) {
            return;
        }
        final ImageView imageView = new ImageView(getActivity());
        imageView.setImageBitmap(bmp);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(imageView);
        builder.create().show();
    }
}
