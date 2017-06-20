package com.duk.lab.android.dialog;

import android.os.Handler;
import android.os.Message;

import java.lang.ref.WeakReference;

/**
 * Created by dukwonnam on 2017. 6. 20..
 */

public class HandlerTimer extends Handler {

    private static final int TIMER = 0x01;

    private WeakReference<TimerListener> mTimerListenerWeakRef;

    HandlerTimer(TimerListener listener) {
        mTimerListenerWeakRef = new WeakReference<>(listener);
    }

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);

        switch (msg.what) {
            case TIMER:
                TimerListener listener = mTimerListenerWeakRef.get();
                if (listener != null) {
                    listener.onTime();
                }
                break;
        }
    }

    public void startTimer(int intervalMillis) {
        removeMessages(TIMER);
        sendEmptyMessageDelayed(TIMER, intervalMillis);
    }

    public void stopTimer() {
        removeMessages(TIMER);
    }
}
