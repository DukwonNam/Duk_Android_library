package com.duk.lab.android.camera;

import android.Manifest;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

import com.duk.lab.android.R;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Duk on 2016-12-13.
 *
 * Ref to :
 * https://developer.android.com/guide/topics/media/camera.html
 * https://developer.android.com/training/camera/photobasics.html
 * https://developer.android.com/training/permissions/requesting.html?hl=ko
 */

public class CameraFragment extends Fragment implements View.OnClickListener {
    private static final int PERMISSION_MASK = 0xFF;
    private static final int PERMISSION_CAMERA = 0x1;
    private static final int PERMISSION_STORAGE = 0x2;

    private static final int REQUEST_MASK = 0xFF00;
    private static final int REQUEST_SINGLE_SHOT = 0x100;
    private static final int REQUEST_SINGLE_SHOT_SAVING = 0x200;
    private static final int[] ON_CLICK_LISTENER_ID_ARRAY = new int[] {
            R.id.singleShot,
            R.id.singleShotSaving,
            R.id.viewThroughCamera,
            R.id.viewThroughCamera2
    };

    private String mCurrentPhotoPath;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        final View view = inflater.inflate(R.layout.camera_main, container, false);
        initViews(view);

        return view;
    }

    private void initViews(View container) {
        for (int id : ON_CLICK_LISTENER_ID_ARRAY) {
            View view = container.findViewById(id);
            if (view != null) {
                view.setOnClickListener(this);
            }
        }
    }


    @Override
    public void onClick(View v) {
        if (v == null) {
            return;
        }

        switch (v.getId()) {
            case R.id.singleShot:
                if (checkCameraPermission(REQUEST_SINGLE_SHOT)) {
                    singleShot();
                }
                break;
            case R.id.singleShotSaving:
                if (checkCameraPermission(REQUEST_SINGLE_SHOT_SAVING) && checkStoragePermission(REQUEST_SINGLE_SHOT_SAVING)) {
                    singleShotSaving();
                }
                break;
            case R.id.viewThroughCamera: {
                Context context = getActivity();
                Intent intent = new Intent(context, CameraViewActivity.class);
                context.startActivity(intent);
                break;
            }
            case R.id.viewThroughCamera2: {
                Context context = getActivity();
                Intent intent = new Intent(context, CameraView2Activity.class);
                context.startActivity(intent);
                break;
            }
        }
    }

    private void singleShot() {
        Intent singleShotIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (singleShotIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivityForResult(singleShotIntent, REQUEST_SINGLE_SHOT);
        }
    }

    private void singleShotSaving() {
        Intent singleShotIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (singleShotIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                // Create an image file name
                String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                String imageFileName = "JPEG_" + timeStamp + "_";
                File storageDir = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
//                File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
                Log.i("test_duk", "storageDir=" + storageDir);
                photoFile = File.createTempFile(
                        imageFileName,  /* prefix */
                        ".jpg",         /* suffix */
                        storageDir      /* directory */
                );

                // Save a file: path for use with ACTION_VIEW intents
                mCurrentPhotoPath = photoFile.getAbsolutePath();
            } catch (IOException ex) {
            }

            Log.i("test_duk", "photoFile=" + photoFile);
            if (photoFile != null) {
//                ProviderInfo info = getActivity().getPackageManager()
//                        .resolveContentProvider("com.duk.lab.android.fileprovider", PackageManager.GET_META_DATA);
//                Log.i("test_duk", "info=" + info);
                Uri photoURI = FileProvider.getUriForFile(getActivity(), "com.duk.lab.android.fileprovider", photoFile);
                singleShotIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(singleShotIntent, REQUEST_SINGLE_SHOT_SAVING);
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_SINGLE_SHOT:
                if (resultCode == Activity.RESULT_OK) {
                    Log.i("test_duk", "onActivityResult REQUEST_SINGLE_SHOT data=" + data);

                    final Bundle extras = data.getExtras();
                    final Bitmap imageBitmap = (Bitmap) extras.get("data");
                    final ImageView imageView = new ImageView(getActivity());
                    final ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    imageView.setImageBitmap(imageBitmap);
                    imageView.setLayoutParams(params);

                    final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setView(imageView);
                    builder.create().show();
                }
                break;
            case REQUEST_SINGLE_SHOT_SAVING:
                if (resultCode == Activity.RESULT_OK) {
                    Log.i("test_duk", "onActivityResult REQUEST_SINGLE_SHOT_SAVING");
                    galleryAddPic();

                    final ImageView imageView = new ImageView(getActivity());
                    final ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    File f = new File(mCurrentPhotoPath);
                    Uri contentUri = Uri.fromFile(f);
                    imageView.setImageURI(contentUri);
                    imageView.setLayoutParams(params);

                    final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setView(imageView);
                    builder.create().show();
                }
                break;
        }
    }

    private void galleryAddPic() {
        Log.i("test_duk", "galleryAddPic");
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(mCurrentPhotoPath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        getActivity().sendBroadcast(mediaScanIntent);
    }


    private boolean checkCameraPermission(int requestCode) {
        if (Build.VERSION.SDK_INT >= 23) {
            if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
//                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, PERMISSION_CAMERA);
                requestPermissions(new String[]{Manifest.permission.CAMERA}, PERMISSION_CAMERA | requestCode);
                return false;
            }
        }
        return true;
    }

    private boolean checkStoragePermission(int requestCode) {
        if (Build.VERSION.SDK_INT >= 23) {
            if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
//                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, PERMISSION_CAMERA);
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_STORAGE | requestCode);
                return false;
            }
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Log.i("test_duk", "requestCode=" + requestCode + ", permissions=" + permissions + ", grantResults=" + grantResults);
        if (permissions == null || permissions == null) {
            return;
        }
        if (grantResults.length > 0 && grantResults[0] != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        switch (requestCode & REQUEST_MASK) {
            case REQUEST_SINGLE_SHOT:
                singleShot();
                break;
            case REQUEST_SINGLE_SHOT_SAVING:
                singleShotSaving();
                break;
        }
    }
}
