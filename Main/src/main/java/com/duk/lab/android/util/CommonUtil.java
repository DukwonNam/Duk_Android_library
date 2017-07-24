package com.duk.lab.android.util;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;

/**
 * Created by dukwonnam on 2017. 1. 31..
 */

public class CommonUtil {

    /**
     * This is to launch the Activity.
     *
     * @param context this application context
     * @param cls the activity class
     */
    public static void jumpToActivity(Context context, Class<?> cls) {
        Intent intent = new Intent();
        intent.setClass(context, cls);
        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            // Do nothing
        }
    }
}
