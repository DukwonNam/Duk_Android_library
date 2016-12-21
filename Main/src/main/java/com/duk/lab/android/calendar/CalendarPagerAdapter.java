package com.duk.lab.android.calendar;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.Calendar;
import java.util.List;

/**
 * Created by Duk on 2016-12-19.
 */

public class CalendarPagerAdapter extends PagerAdapter {
    private Context mContext;
    private List<Calendar> mMonthList;

    public CalendarPagerAdapter(Context context, List<Calendar> monthList) {
        mContext = context;
        mMonthList = monthList;
    }

    @Override
    public int getCount() {
        return mMonthList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public CalendarView instantiateItem(ViewGroup container, int position) {
        position %= mMonthList.size();
        final Calendar theMonth = mMonthList.get(position);

        final CalendarView calendarView = new CalendarView(mContext);
        calendarView.setMonth(theMonth, true);
        container.addView(calendarView);

        return calendarView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
    }
}
