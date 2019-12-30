package com.xululu.recycleviewdemo;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Author: pipilu
 * Time: 2019-07-10 22:22
 */
public class TestVH extends RecyclerView.ViewHolder {

    public static final int LAYOUT_RES_IS = R.layout.test_vh_layout;
    public TextView mTestTv;

    public TestVH(@NonNull View itemView) {
        super(itemView);
        mTestTv = itemView.findViewById(R.id.test_tv);

    }
}
