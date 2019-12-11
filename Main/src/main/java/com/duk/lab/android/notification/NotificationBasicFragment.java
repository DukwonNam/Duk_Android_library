package com.duk.lab.android.notification;

import android.app.Fragment;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.duk.lab.android.R;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by dukwonnam on 2017. 1. 19..
 */

public class NotificationBasicFragment extends Fragment implements View.OnClickListener {
    private static final int[] ON_CLICK_LISTENER_ID_ARRAY = new int[] {
            R.id.simpleNoti
    };
    private static final Map<CharSequence, Integer> NOTI_ALARM = new HashMap<>();
    private static final Map<CharSequence, Integer> NOTI_PRIORITY = new HashMap<>();
    private static final Map<CharSequence, Integer> NOTI_VISIBILITY = new HashMap<>();
    private static final Map<CharSequence, String> NOTI_CATEGORY = new HashMap<>();

    static {
        NOTI_ALARM.put("ALL", Notification.DEFAULT_ALL);
        NOTI_ALARM.put("LIGHTS", Notification.DEFAULT_LIGHTS);
        NOTI_ALARM.put("SOUND", Notification.DEFAULT_SOUND);
        NOTI_ALARM.put("VIBRATE", Notification.DEFAULT_VIBRATE);

        // After API 16
        NOTI_PRIORITY.put("DEFAULT", Notification.PRIORITY_DEFAULT);
        NOTI_PRIORITY.put("HIGH", Notification.PRIORITY_HIGH);
        NOTI_PRIORITY.put("LOW", Notification.PRIORITY_LOW);
        NOTI_PRIORITY.put("MAX", Notification.PRIORITY_MAX);
        NOTI_PRIORITY.put("MIN", Notification.PRIORITY_MIN);

        // After API 21
        NOTI_VISIBILITY.put("PRIVATE", Notification.VISIBILITY_PRIVATE);
        NOTI_VISIBILITY.put("PUBLIC", Notification.VISIBILITY_PUBLIC);
        NOTI_VISIBILITY.put("SECRET", Notification.VISIBILITY_SECRET);

        // After API 21
        NOTI_CATEGORY.put("CATEGORY_ALARM", Notification.CATEGORY_ALARM);
        NOTI_CATEGORY.put("CATEGORY_CALL", Notification.CATEGORY_CALL);
        NOTI_CATEGORY.put("CATEGORY_EMAIL", Notification.CATEGORY_EMAIL);
        NOTI_CATEGORY.put("CATEGORY_ERROR", Notification.CATEGORY_ERROR);
        NOTI_CATEGORY.put("CATEGORY_EVENT", Notification.CATEGORY_EVENT);
        NOTI_CATEGORY.put("CATEGORY_MESSAGE", Notification.CATEGORY_MESSAGE);
        NOTI_CATEGORY.put("CATEGORY_PROGRESS", Notification.CATEGORY_PROGRESS);
        NOTI_CATEGORY.put("CATEGORY_PROMO", Notification.CATEGORY_PROMO);
        NOTI_CATEGORY.put("CATEGORY_RECOMMENDATION", Notification.CATEGORY_RECOMMENDATION);
        NOTI_CATEGORY.put("CATEGORY_REMINDER", Notification.CATEGORY_REMINDER);
        NOTI_CATEGORY.put("CATEGORY_SERVICE", Notification.CATEGORY_SERVICE);
        NOTI_CATEGORY.put("CATEGORY_SOCIAL", Notification.CATEGORY_SOCIAL);
        NOTI_CATEGORY.put("CATEGORY_STATUS", Notification.CATEGORY_STATUS);
        NOTI_CATEGORY.put("CATEGORY_SYSTEM", Notification.CATEGORY_SYSTEM);
        NOTI_CATEGORY.put("CATEGORY_TRANSPORT", Notification.CATEGORY_TRANSPORT);
    }

    private RadioGroup mPriorityGroup;
    private RadioGroup mVisibilityGroup;
    private RadioGroup mCategoryGroup;
    private RadioGroup mNotiTypeGroup;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.notification_basic, container, false);
        for (int resId : ON_CLICK_LISTENER_ID_ARRAY) {
            view.findViewById(resId).setOnClickListener(this);
        }

        mNotiTypeGroup = (RadioGroup)view.findViewById(R.id.notiType);
        final Set<CharSequence> notiKeySet = NOTI_ALARM.keySet();
        final Iterator<CharSequence> notiIterator = notiKeySet.iterator();
        while (notiIterator.hasNext()) {
            final RadioButton btn = new RadioButton(getActivity());
            btn.setText(notiIterator.next());
            mNotiTypeGroup.addView(btn);
        }

        mPriorityGroup = (RadioGroup)view.findViewById(R.id.priority);
        final Set<CharSequence> priorityKeySet = NOTI_PRIORITY.keySet();
        final Iterator<CharSequence> priorityIterator = priorityKeySet.iterator();
        while (priorityIterator.hasNext()) {
            final RadioButton btn = new RadioButton(getActivity());
            btn.setText(priorityIterator.next());
            mPriorityGroup.addView(btn);
        }

        mVisibilityGroup = (RadioGroup)view.findViewById(R.id.visibility);
        final Set<CharSequence> visibilityKeySet = NOTI_VISIBILITY.keySet();
        final Iterator<CharSequence> visibilityIterator = visibilityKeySet.iterator();
        while (visibilityIterator.hasNext()) {
            final RadioButton btn = new RadioButton(getActivity());
            btn.setText(visibilityIterator.next());
            mVisibilityGroup.addView(btn);
        }

        mCategoryGroup = (RadioGroup)view.findViewById(R.id.category);
        final Set<CharSequence> categoryKeySet = NOTI_CATEGORY.keySet();
        final Iterator<CharSequence> categoryIterator = categoryKeySet.iterator();
        while (categoryIterator.hasNext()) {
            final RadioButton btn = new RadioButton(getActivity());
            btn.setText(categoryIterator.next());
            mCategoryGroup.addView(btn);
        }

        return view;
    }

    @Override
    public void onClick(View v) {
        if (v == null) {
            return;
        }

        switch (v.getId()) {
            case R.id.simpleNoti:
                setSimpleNotification(getAlarm(), getPriority(), getCategory(), getVisibility());
                break;
        }
    }

    private int getPriority() {
        return getValue(mPriorityGroup, NOTI_PRIORITY, Notification.PRIORITY_DEFAULT);
    }

    private int getAlarm() {
        return getValue(mNotiTypeGroup, NOTI_ALARM, Notification.DEFAULT_ALL);
    }

    private int getVisibility() {
        return getValue(mVisibilityGroup, NOTI_VISIBILITY, Notification.VISIBILITY_PUBLIC);
    }

    private String getCategory() {
        return getValue(mCategoryGroup, NOTI_CATEGORY, Notification.CATEGORY_ALARM);
    }

    private int getValue(RadioGroup radioGroup, Map<CharSequence, Integer> valueMap, int defaultValue) {
        int result = defaultValue;
        if (radioGroup != null) {
            final int childCount = radioGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                final View view = radioGroup.getChildAt(i);
                if (view instanceof RadioButton) {
                    final RadioButton button = (RadioButton)view;
                    if (button.isChecked()) {
                        final Integer value = valueMap.get(((RadioButton)view).getText());
                        if (value != null) {
                            result = value;
                            break;
                        }
                    }
                }
            }
        }
        return result;
    }

    private String getValue(RadioGroup radioGroup, Map<CharSequence, String> valueMap, String defaultValue) {
        String result = defaultValue;
        if (radioGroup != null) {
            final int childCount = radioGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                final View view = radioGroup.getChildAt(i);
                if (view instanceof RadioButton) {
                    final RadioButton button = (RadioButton)view;
                    if (button.isChecked()) {
                        final String value = valueMap.get(((RadioButton)view).getText());
                        if (value != null) {
                            result = value;
                            break;
                        }
                    }
                }
            }
        }
        return result;
    }

    private void setSimpleNotification(int notiType, int priority, String category, int visibility) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getActivity(), "");

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
