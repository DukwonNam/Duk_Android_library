package com.duk.lab.android.calendar;

import android.support.v4.view.PagerAdapter;
import android.view.View;

import com.duk.lab.android.calendar.util.CalendarUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Duk on 2016-12-19.
 */

public class CalendarPagerAdapter extends PagerAdapter {
    private Calendar mCurrMonth;
    private List<Calendar> mMonthList;

    public CalendarPagerAdapter(Calendar startMonth) {
        mCurrMonth = (Calendar) startMonth.clone();
        CalendarUtil.clearTimeInfo(mCurrMonth);
        mCurrMonth.set(Calendar.DATE, 1);
        makeMonthList();
    }

    private void makeMonthList() {
        mMonthList = new ArrayList<>();
        Calendar prevMonth = (Calendar) mCurrMonth.clone();
        prevMonth.add(Calendar.MONTH, -1);
        Calendar nextMonth = (Calendar) mCurrMonth.clone();
        nextMonth.add(Calendar.MONTH, 1);

        mMonthList.add(prevMonth);
        mMonthList.add(mCurrMonth);
        mMonthList.add(nextMonth);
    }

    @Override
    public int getCount() {
        return mMonthList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return false;
    }
}
