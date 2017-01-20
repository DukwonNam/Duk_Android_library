package com.duk.lab.android.notification;

import android.app.Fragment;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.NotificationCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.duk.lab.android.R;

/**
 * Created by dukwonnam on 2017. 1. 19..
 */

public class NotificationMainFragment extends Fragment implements View.OnClickListener {
    private static final int[] ON_CLICK_LISTENER_ID_ARRAY = new int[] {
            R.id.simpleNoti
    };
    private static final int[] PRIORITY_ARRAY = new int[] {
            Notification.PRIORITY_DEFAULT,

    };

    private RadioGroup mPriorityGroup;
    private Object mVisibilityGroup;
    private RadioGroup mCategoryGroup;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.notification_main, container, false);
        for (int resId : ON_CLICK_LISTENER_ID_ARRAY) {
            view.findViewById(resId).setOnClickListener(this);
        }

        mPriorityGroup = (RadioGroup)view.findViewById(R.id.priority);
        mPriorityGroup.addView();
        mVisibilityGroup = (RadioGroup)view.findViewById(R.id.visibility);
        mCategoryGroup = (RadioGroup)view.findViewById(R.id.category);

        return view;
    }

    @Override
    public void onClick(View v) {
        if (v == null) {
            return;
        }

        switch (v.getId()) {
            case R.id.simpleNoti:
                setSimpleNotification(Notification.DEFAULT_ALL, Notification.PRIORITY_DEFAULT, Notification.CATEGORY_MESSAGE, Notification.VISIBILITY_PUBLIC);
                break;
        }
    }

    /*
        Notification.CATEGORY_ALARM;
        Notification.CATEGORY_CALL;
        Notification.CATEGORY_EMAIL;
        Notification.CATEGORY_ERROR;
        Notification.CATEGORY_EVENT;
        Notification.CATEGORY_MESSAGE;
        Notification.CATEGORY_PROGRESS;
        Notification.CATEGORY_PROMO;
        Notification.CATEGORY_RECOMMENDATION;
        Notification.CATEGORY_REMINDER;
        Notification.CATEGORY_SERVICE;
        Notification.CATEGORY_SOCIAL;
        Notification.CATEGORY_STATUS;
        Notification.CATEGORY_SYSTEM;
        Notification.CATEGORY_TRANSPORT;
     */

    private int getPriority() {
        int priority = Notification.PRIORITY_DEFAULT;

        if (mPriorityGroup != null) {
            final int childCount = mPriorityGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                final View view = mPriorityGroup.getChildAt(i);
                if (view instanceof RadioButton) {
                    final RadioButton button = (RadioButton)view;
                    if (button.isChecked()) {
                        switch (i) {
                            case 0:
                                priority = Notification.PRIORITY_DEFAULT;
                                break;
                            case
                        }
                    }
                }
            }
        }
    }
    private void setSimpleNotification(int notiType, int priority, String category, int visibility) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getActivity());

        builder.setContentTitle("Content Title [priority]=" + priority)
                .setContentText("Content Text [category]=" + category)
                .setTicker("Ticker text [visibility]=" + visibility)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.main_icon))
//                        .setContentIntent(contentIntent)
                .setAutoCancel(true)
                .setWhen(System.currentTimeMillis())
                .setDefaults(notiType);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            builder.setCategory(category)
                    .setPriority(priority)
                    .setVisibility(visibility);
        }

        NotificationManager nm = (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
        nm.notify(1, builder.build());
    }
}
