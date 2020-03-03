package com.xululu.recycleviewdemo.rvanim;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xululu.recycleviewdemo.R;

import butterknife.BindView;

/**
 * Author: pipilu
 * Time: 2020-01-13 16:57
 */
public class AnimViewHolder extends RecyclerView.ViewHolder {

    public static final int RES_ANIM_ITEM = R.layout.anim_item_lyt;
    @BindView(R.id.test_tv)
    TextView testTv;

    public View mRootView;

    public AnimViewHolder(@NonNull View itemView) {
        super(itemView);
        testTv = itemView.findViewById(R.id.test_tv);
        mRootView = itemView.findViewById(R.id.root_view);
    }
}
