package com.duk.lab.android.calendar;

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.duk.lab.android.R;

import java.util.Calendar;

/**
 * Created by Duk on 2016-12-19.
 */

public class DayTitleCell extends FrameLayout {
    private TextView mTitleView;

    public DayTitleCell(Context context) {
        super(context);
        initViews();
    }

    public DayTitleCell(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViews();
    }

    public DayTitleCell(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViews();
    }

    private void initViews() {
        removeAllViews();

        mTitleView = new TextView(getContext());
        mTitleView.setGravity(Gravity.CENTER);
        mTitleView.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.calendar_font_date_cell_text));
        final LinearLayout.LayoutParams titleParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        addView(mTitleView, titleParams);
    }

    /**
     *
     * @param day integer of {@link java.util.Calendar#DAY_OF_WEEK}
     */
    public void setDayOfWeek(int day) {

        int titleResId = 0;
        switch (day) {
            case Calendar.SUNDAY:
                titleResId = R.string.calendar_day_title_sunday;
                break;
            case Calendar.MONDAY:
                titleResId = R.string.calendar_day_title_monday;
                break;
            case Calendar.TUESDAY:
                titleResId = R.string.calendar_day_title_tuesday;
                break;
            case Calendar.WEDNESDAY:
                titleResId = R.string.calendar_day_title_wednesday;
                break;
            case Calendar.THURSDAY:
                titleResId = R.string.calendar_day_title_thursday;
                break;
            case Calendar.FRIDAY:
                titleResId = R.string.calendar_day_title_friday;
                break;
            case Calendar.SATURDAY:
                titleResId = R.string.calendar_day_title_saturday;
                break;
        }
        mTitleView.setText(titleResId);
    }
}
