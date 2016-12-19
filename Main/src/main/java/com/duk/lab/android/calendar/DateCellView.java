package com.duk.lab.android.calendar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.duk.lab.android.R;

import java.util.Calendar;

/**
 * Created by Duk on 2016-12-19.
 */

public class DateCellView extends FrameLayout {
    private Calendar mDate;
    private TextView mDateText;

    public DateCellView(Context context) {
        super(context);
        initViews();
    }

    public DateCellView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViews();
    }

    public DateCellView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViews();
    }

    private void initViews() {
        removeAllViews();
        View view = View.inflate(getContext(), R.layout.date_cell_view, this);
        mDateText = (TextView)view.findViewById(R.id.dateText);
    }

    public void setDate(Calendar date) {
        mDate = date;
        mDateText.setText(Integer.toString(mDate.get(Calendar.DAY_OF_MONTH)));
    }
}
