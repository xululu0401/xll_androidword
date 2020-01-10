package com.xululu.recycleviewdemo.cusviews;

import android.content.Context;
import android.util.Log;

import androidx.recyclerview.widget.LinearSmoothScroller;

/**
 * Author: llxu4
 * Time: 2020-01-06 19:52
 */
public class TestScroller extends LinearSmoothScroller {


    private static final String TAG = "TestScroller";

    public TestScroller(Context context) {
        super(context);
    }


    @Override
    public int calculateDtToFit(int viewStart, int viewEnd, int boxStart, int boxEnd, int snapPreference) {
        Log.d(TAG, "view height in cal:   " + (viewEnd - viewStart));
        Log.d(TAG, "box height in cal:   " + (boxEnd - boxStart));

        return super.calculateDtToFit(viewStart, viewEnd, boxStart, boxEnd, snapPreference);
    }

    @Override
    protected int getVerticalSnapPreference() {
        return SNAP_TO_END;
    }
}
