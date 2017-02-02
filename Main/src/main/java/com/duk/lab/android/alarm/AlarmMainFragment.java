package com.duk.lab.android.alarm;

import com.duk.lab.android.R;

import android.app.AlarmManager;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
                // TODO: add alarm
                break;
        }
    }

    private void updateAlarmList(View view) {
        if (view == null) {
            return;
        }

        final AlarmManager alarmManager = (AlarmManager)getActivity().getSystemService(Context.ALARM_SERVICE);

        mRecyclerView = (RecyclerView)view.findViewById(R.id.currentAlarmList);
        // TODO: setAdapter
    }
}
