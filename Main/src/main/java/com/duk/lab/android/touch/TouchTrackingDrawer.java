package com.duk.lab.android.touch;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by dukwonnam on 2017. 2. 23..
 *
 * refered : http://bitsoul.tistory.com/62
 */

public class TouchTrackingDrawer extends View implements View.OnTouchListener {

    private final Paint mPaint = new Paint();
    private final Path mPath  = new Path();

    public TouchTrackingDrawer(Context context) {
        super(context);
        init();
    }

    public TouchTrackingDrawer(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TouchTrackingDrawer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint.setStyle(Paint.Style.STROKE); // 선이 그려지도록
        mPaint.setStrokeWidth(10f);
        setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch(event.getAction()) {
            case MotionEvent.ACTION_DOWN :
                mPath.moveTo(x, y);
                break;
            case MotionEvent.ACTION_MOVE :
                mPath.lineTo(x, y);
                break;
            case MotionEvent.ACTION_UP :
                break;
        }

        invalidate();

        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawPath(mPath, mPaint);
    }

    public void resetTracked() {
        mPath.reset();
        invalidate();
    }

    public Bitmap getTrackedBitmap() {
        destroyDrawingCache();
        buildDrawingCache();
        return getDrawingCache();
    }
}
