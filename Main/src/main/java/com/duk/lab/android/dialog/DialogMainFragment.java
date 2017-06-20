package com.duk.lab.android.dialog;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.duk.lab.android.R;

/**
 * Created by dukwonnam on 2017. 6. 20..
 */

public class DialogMainFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = "DialogMainFragment";

    private static final String BUTTON_START = "Start timer";
    private static final String BUTTON_STOP = "Stop timer";

    private ProgressBar mProgressView;
    private TextView mProgressCountView;
    private int mTimerCount;
    private HandlerTimer mHandlerTimer;
    private Button mToggleTimer;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.dialog_main, container, false);

        mProgressView = (ProgressBar)view.findViewById(R.id.progressBar);
        mProgressCountView = (TextView)view.findViewById(R.id.progressBarCount);
        mHandlerTimer = new HandlerTimer(mTimerListener);

        mToggleTimer = (Button)view.findViewById(R.id.toggleTimer);
        mToggleTimer.setOnClickListener(this);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        if (mHandlerTimer != null) {
            mHandlerTimer.stopTimer();
            mHandlerTimer = null;
        }
    }

    @Override
    public void onClick(View v) {
        if (v == null) {
            return;
        }

        switch (v.getId()) {
            case R.id.toggleTimer:
                if (BUTTON_START.equalsIgnoreCase(mToggleTimer.getText().toString())) {
                    mToggleTimer.setText(BUTTON_STOP);
                    mProgressView.setProgress(0);
                    mHandlerTimer.startTimer(1000);
                    mTimerCount = 0;
                } else {
                    mToggleTimer.setText(BUTTON_START);
                    mHandlerTimer.stopTimer();
                }
                break;
        }
    }

    private TimerListener mTimerListener = new TimerListener() {
        @Override
        public void onTime() {
            mProgressCountView.setText(String.valueOf(++mTimerCount));
            mProgressView.setProgress(mTimerCount);
            mHandlerTimer.startTimer(1000);
        }
    };
}
