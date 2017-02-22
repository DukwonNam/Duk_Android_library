package com.duk.lab.android.animation;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Movie;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by dukwonnam on 2017. 2. 22..
 *
 * refered : http://stackoverflow.com/questions/20416383/how-to-play-gif-in-android
 */

public class GifMovieView extends View {
    private Movie mMovie;
    private long mMovieStart;
    private int mCurrentAnimationTime;

    public GifMovieView(Context context) {
        super(context);
        initViews();
    }

    public GifMovieView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViews();
    }

    public GifMovieView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViews();
    }

    private void initViews() {
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
    }

    public void setGifImage(int gifDrawableId) {
        mMovie = Movie.decodeStream(getResources().openRawResource(gifDrawableId));
        requestLayout();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (mMovie != null) {
            setMeasuredDimension(mMovie.width(), mMovie.height());
        } else {
            setMeasuredDimension(getSuggestedMinimumWidth(), getSuggestedMinimumHeight());
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (mMovie != null) {
            long now = SystemClock.uptimeMillis();

            if (mMovieStart == 0) {
                mMovieStart = now;
            }
            int duration = mMovie.duration();
            if (duration == 0) {
                duration = 1000;
            }

            mCurrentAnimationTime = (int)((now - mMovieStart) % duration);

            drawGif(canvas);
            invalidate();
        } else {
            drawGif(canvas);
        }
    }

    private void drawGif(Canvas canvas) {
        mMovie.setTime(mCurrentAnimationTime);
        mMovie.draw(canvas, 0, 0);
        canvas.restore();
    }
}
