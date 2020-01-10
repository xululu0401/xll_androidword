package com.xululu.recycleviewdemo.cusviews;

import android.content.Context;
import android.util.AttributeSet;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Author: llxu4
 * Time: 2020-01-06 19:51
 */
public class TestLineaLayoutMgr extends LinearLayoutManager {

    public TestLineaLayoutMgr(Context context) {
        super(context);
    }

    public TestLineaLayoutMgr(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }

    public TestLineaLayoutMgr(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int position) {
        TestScroller scroller = new TestScroller(recyclerView.getContext());
        scroller.setTargetPosition(position);
        startSmoothScroll(scroller);
    }

    @Override
    public void scrollToPosition(int position) {
        super.scrollToPosition(position);
    }
}
