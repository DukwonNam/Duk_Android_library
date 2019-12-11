package com.duk.lab.android.notification;

import com.duk.lab.android.R;

import android.app.Fragment;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RemoteViews;
import android.widget.SeekBar;

/**
 * Created by dukwonnam on 2017. 1. 24..
 */

public class NotificationCustomFragment extends Fragment implements View.OnClickListener {
    private static final int[] ON_CLICK_LISTENER_ID_ARRAY = new int[] {
            R.id.toggleNotification
    };
    private RemoteViews mRemoteViews;
    private NotificationCompat.Builder mNotificationBuilder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        final View view = inflater.inflate(R.layout.notification_custom, container, false);
        for (int resId : ON_CLICK_LISTENER_ID_ARRAY) {
            view.findViewById(resId).setOnClickListener(this);
        }
        SeekBar seakBar = (SeekBar) view.findViewById(R.id.seekBar);
        seakBar.setOnSeekBarChangeListener(mSeekBarChangeListener);

        return view;
    }

    @Override
    public void onClick(View v) {
        if (v == null) {
            return;
        }

        switch (v.getId()) {
            case R.id.toggleNotification:
                startNotification();
                break;
        }
    }

    private void startNotification() {
        mNotificationBuilder = new NotificationCompat.Builder(getActivity(), "");

        mNotificationBuilder.setContentTitle("Content Title")
                .setContentText("Content Text")
                .setTicker("Ticker text")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.main_icon))
//                        .setContentIntent(contentIntent)
                .setAutoCancel(false)
                .setWhen(System.currentTimeMillis())
                .setDefaults(Notification.DEFAULT_ALL);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            mNotificationBuilder.setCategory(Notification.CATEGORY_STATUS)
                    .setPriority(Notification.PRIORITY_DEFAULT)
                    .setVisibility(Notification.VISIBILITY_PUBLIC);
        }

        mRemoteViews = new RemoteViews(getActivity().getPackageName(), R.layout.noti_custom_view);
        mRemoteViews.setProgressBar(R.id.progressBar, 100, 0, true);
        mNotificationBuilder.setCustomBigContentView(mRemoteViews);
        mNotificationBuilder.setProgress(100, 0, true);
        Notification notification = mNotificationBuilder.build();
//        notification.flags |= Notification.FLAG_NO_CLEAR;

        NotificationManager nm = (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
        nm.notify(1, notification);
    }

    private SeekBar.OnSeekBarChangeListener mSeekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            Log.i("test_duk", "onProgressChanged progress=" + progress);
            mRemoteViews.setProgressBar(R.id.progressBar, 100, progress, false);
            mNotificationBuilder.setProgress(100, progress, false);
            mNotificationBuilder.setDefaults(Notification.DEFAULT_LIGHTS);
            mNotificationBuilder.setVisibility(Notification.VISIBILITY_PRIVATE);
            NotificationManager nm = (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
            nm.notify(1, mNotificationBuilder.build());
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
        }
    };
}
