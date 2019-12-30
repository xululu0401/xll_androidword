package com.xululu.toucheventdemo;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Scroller;

/**
 * Author: pipilu
 * Time: 2019-09-23 19:14
 */
public class MyButton extends android.support.v7.widget.AppCompatButton {

    private static final String TAG = "MyButton";

    private Scroller mScroller = new Scroller(getContext());

    public MyButton(Context context) {
        super(context);
    }

    public MyButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.d(TAG, "dispatchTouchEvent: dispatchtouchevent***************  " + event);
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(TAG, "onTouchEvent: ontouchevent***************  "+ event);
//        if (MotionEvent.ACTION_DOWN == event.getAction()){
//            return false;
//        } else if (MotionEvent.ACTION_MOVE == event.getAction()){
//            return false;
//        }
        return super.onTouchEvent(event);
    }

    public void smoothScrollTo(int destX, int destY){
        int scrollX = getScrollX();
        int delta = destX - scrollX;
        mScroller.startScroll(scrollX, 0, delta, 0, 10000);
        invalidate();
    }

    @Override
    public void computeScroll() {
        Log.d(TAG, "computeScroll:  "+ mScroller.computeScrollOffset());
        if (mScroller.computeScrollOffset()) {
            Log.d(TAG, "computeScroll:  "+ "currentx:  "+mScroller.getCurrX()+"   currenty:   "+mScroller.getCurrY());
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            postInvalidate();
        }
    }
}
