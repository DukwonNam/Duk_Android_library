package com.duk.lab.android.calendar;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.duk.lab.android.R;
import com.duk.lab.android.calendar.util.CalendarUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Duk on 2016-12-19.
 */

public class CalendarNavigator extends FrameLayout {
    private static final int MAX_CALENDAR_PAGE_HALF = 12;
    private ViewPager mCalendarPager;
    private CalendarPagerAdapter mCalendarPagerAdapter;

    private Calendar mCurrMonth;
    private int mDefaultPosition;
    private List<Calendar> mMonthList;

    public CalendarNavigator(Context context) {
        super(context);
        initViews();
    }

    public CalendarNavigator(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViews();
    }

    public CalendarNavigator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViews();
    }

    private void initViews() {
        View view = View.inflate(getContext(), R.layout.calendar_navigator, this);
        mCalendarPager = (ViewPager)view.findViewById(R.id.calendarPager);
        makeMonthList();
        mCalendarPagerAdapter = new CalendarPagerAdapter(getContext(), mMonthList);
        mCalendarPager.setAdapter(mCalendarPagerAdapter);
        mCalendarPager.setCurrentItem(mDefaultPosition);
    }

    private void makeMonthList() {
        mCurrMonth = Calendar.getInstance();
        CalendarUtil.clearTimeInfo(mCurrMonth);
        mCurrMonth.set(Calendar.DATE, 1);

        mMonthList = new ArrayList<>();
        Calendar monthTraveler = mCurrMonth;
        for (int i = 0; i < MAX_CALENDAR_PAGE_HALF; i++) {
            monthTraveler = (Calendar) monthTraveler.clone();
            monthTraveler.add(Calendar.MONTH, -1);
            mMonthList.add(0, monthTraveler);
        }

        mDefaultPosition = mMonthList.size();
        mMonthList.add((Calendar) mCurrMonth.clone());

        monthTraveler = mCurrMonth;
        for (int i = 0; i < MAX_CALENDAR_PAGE_HALF; i++) {
            monthTraveler = (Calendar) monthTraveler.clone();
            monthTraveler.add(Calendar.MONTH, 1);
            mMonthList.add(monthTraveler);
        }
    }
}
