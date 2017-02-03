package com.duk.lab.android.alarm;

import com.duk.lab.android.R;

import android.app.AlarmManager;
import android.app.Fragment;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by dukwonnam on 2017. 1. 24..
 */

public class AlarmMainFragment extends Fragment implements View.OnClickListener {
    private static final int[] ON_CLICK_LISTENER_ID_ARRAY = new int[] {
            R.id.addAlarm
    };

    private boolean mIsStarted;
    private RecyclerView mRecyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        final View view = inflater.inflate(R.layout.alarm_main, container, false);
        for (int resId : ON_CLICK_LISTENER_ID_ARRAY) {
            view.findViewById(resId).setOnClickListener(this);
        }

        updateAlarmList(view);

        return view;
    }

    @Override
    public void onClick(View v) {
        if (v == null) {
            return;
        }

        switch (v.getId()) {
            case R.id.addAlarm:
                addAlarm();
                break;
        }
    }

    private void addAlarm() {
        final AlarmManager alarmManager = (AlarmManager)getActivity().getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        PendingIntent pIntent = PendingIntent.getActivity(getActivity(), 0, intent, 0);

        /*
           RTC : UTC표준시간을 기준으로 하는 명시적인 시간에 intent를 발생
           RTC_WAKEUP : UTC 표준시간을 기준으로 하는 명시적인 시간에 intent를 발생, 장치를 깨움
           ELAPSED_REALTIME : 부팅 후 시간을 기준으로 지정된 시간에 intent를 발생
           ELAPSED_REALTIME_WAKEUP : 부팅 후 시간을 기준으로 지정된 시간에 intent를 발생, 장치를 깨움
           출처: http://doraeul.tistory.com/73 [도래울]
         */
        alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 5000, pIntent);
    }

    private void updateAlarmList(View view) {
        if (view == null) {
            return;
        }

        mRecyclerView = (RecyclerView)view.findViewById(R.id.currentAlarmList);
        // TODO: setAdapter
    }
}
