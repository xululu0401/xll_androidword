package com.xululu.recycleviewdemo.cusviews;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xululu.recycleviewdemo.R;

/**
 * Author: llxu4
 * Time: 2020-01-06 19:45
 */
public class TestViewHolder extends RecyclerView.ViewHolder {

    public static final int RES_ITE_LAYOUT = R.layout.test_item_lyt;

    public TextView mTextView;

    public TestViewHolder(@NonNull View itemView) {
        super(itemView);
        mTextView = itemView.findViewById(R.id.test_vh_tv);
    }
}
