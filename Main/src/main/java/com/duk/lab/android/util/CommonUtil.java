package com.duk.lab.android.util;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;

/**
 * Created by dukwonnam on 2017. 1. 31..
 */

public class CommonUtil {

    /**
     *
     * @param context
     * @param cls
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
