package com.duk.lab.android.calendar;

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.duk.lab.android.R;
import com.duk.lab.android.calendar.util.CalendarUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Duk on 2016-12-19.
 */

public class CalendarView extends LinearLayout {
    private static final int DAYS_OF_WEEK = 7;
    private static final int START_OF_WEEK = Calendar.SUNDAY;
    private static final int END_OF_WEEK = Calendar.SATURDAY;
    private Calendar mThisMonth;
    private TextView mTitleView;

    public CalendarView(Context context) {
        super(context);
        initViews();
    }

    public CalendarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViews();
    }

    public CalendarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViews();
    }

    private void initViews() {
        setOrientation(LinearLayout.VERTICAL);
        setGravity(Gravity.CENTER);
        removeAllViews();

        mTitleView = new TextView(getContext());
        mTitleView.setGravity(Gravity.CENTER);
        mTitleView.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.calendar_font_month_text));
        final LinearLayout.LayoutParams titleParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        addView(mTitleView, titleParams);
    }

    public void setMonth(Calendar thisMonth, boolean showTitle) {
        mThisMonth = (Calendar)thisMonth.clone();
        CalendarUtil.clearTimeInfo(mThisMonth);
        mThisMonth.set(Calendar.DATE, 1);

        if (showTitle) {
            SimpleDateFormat formatter = new SimpleDateFormat(getResources().getString(R.string.calendar_month_formatter));
            mTitleView.setText(formatter.format(mThisMonth.getTime()));
            mTitleView.setVisibility(VISIBLE);
        } else {
            mTitleView.setVisibility(GONE);
        }

        final List<Calendar> dateList = makeDateList();
        makeCalendarLayout(dateList);
    }

    private List<Calendar> makeDateList() {
        final List<Calendar> dateList = new ArrayList<>();
        Calendar dateTraveler = (Calendar)mThisMonth.clone();
        dateTraveler.set(Calendar.DATE, 1);

        while (CalendarUtil.isSameMonth(dateTraveler, mThisMonth)) {
            dateList.add(dateTraveler);
            dateTraveler = (Calendar)dateTraveler.clone();
            dateTraveler.add(Calendar.DATE, 1);
        }

        dateTraveler = (Calendar)dateList.get(0).clone();
        while (dateTraveler.get(Calendar.DAY_OF_WEEK) != START_OF_WEEK) {
            dateTraveler.add(Calendar.DATE, -1);
            dateList.add(0, dateTraveler);
            dateTraveler = (Calendar)dateTraveler.clone();
        }

        dateTraveler = (Calendar)dateList.get(dateList.size() - 1).clone();
        while (dateTraveler.get(Calendar.DAY_OF_WEEK) != END_OF_WEEK) {
            dateTraveler.add(Calendar.DATE, 1);
            dateList.add(dateTraveler);
            dateTraveler = (Calendar)dateTraveler.clone();
        }

        return dateList;
    }

    private void makeCalendarLayout(List<Calendar> dateList) {
        for (int start = 0, end = DAYS_OF_WEEK; end <= dateList.size(); start = end, end += DAYS_OF_WEEK) {
            if (start == 0) {
                makeDayTitles(dateList.subList(start, end));
            }
            makeWeekLayout(dateList.subList(start, end));
        }
    }

    private void makeDayTitles(List<Calendar> weekList) {
        final LinearLayout weekContainer = new LinearLayout(getContext());
        weekContainer.setOrientation(HORIZONTAL);
        for (Calendar date : weekList) {
            DayTitleCell day = new DayTitleCell(getContext());
            day.setDayOfWeek(date.get(Calendar.DAY_OF_WEEK));

            final LinearLayout.LayoutParams dateCellParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            dateCellParams.weight = 1;
            weekContainer.addView(day, dateCellParams);
        }

        LinearLayout.LayoutParams weekParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        addView(weekContainer, weekParams);
    }

    private void makeWeekLayout(List<Calendar> weekList) {
        final LinearLayout weekContainer = new LinearLayout(getContext());
        weekContainer.setOrientation(HORIZONTAL);
        for (Calendar date : weekList) {
            DateCellView dateView = new DateCellView(getContext());
            dateView.setDate(date);

            final LinearLayout.LayoutParams dateCellParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            dateCellParams.weight = 1;
            weekContainer.addView(dateView, dateCellParams);
        }

        LinearLayout.LayoutParams weekParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        weekParams.weight = 1;
        addView(weekContainer, weekParams);
    }
}
