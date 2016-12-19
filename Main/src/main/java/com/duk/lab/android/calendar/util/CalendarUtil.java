package com.duk.lab.android.calendar.util;

import java.util.Calendar;

/**
 * Created by Duk on 2016-12-19.
 */

public class CalendarUtil {

    public static void clearTimeInfo(Calendar theDate) {
        if (theDate == null) {
            return;
        }

        theDate.set(Calendar.HOUR_OF_DAY, 0);
        theDate.set(Calendar.MINUTE, 0);
        theDate.set(Calendar.SECOND, 0);
    }

    public static boolean isSameMonth(Calendar calA, Calendar calB) {
        if (calA == null || calB == null) {
            return false;
        }

        if (calA.get(Calendar.YEAR) == calB.get(Calendar.YEAR)
                && calA.get(Calendar.MONTH) == calB.get(Calendar.MONTH)) {
            return true;
        }
        return false;
    }
}
