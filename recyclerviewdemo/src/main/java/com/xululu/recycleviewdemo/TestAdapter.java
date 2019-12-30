package com.xululu.recycleviewdemo;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * Author: pipilu
 * Time: 2019-07-10 22:20
 */
public class TestAdapter extends RecyclerView.Adapter<TestVH> {
    private static final String TAG = "TestAdapter";

    private List<String> mItems;
    private Context mContext;
    private MainActivity mActivity;


    public TestAdapter(Context context, List<String> list, MainActivity activity){
        mContext = context;
        mItems = list;
        mActivity = activity;
    }

    @NonNull
    @Override
    public TestVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TestVH(LayoutInflater.from(mContext).inflate(TestVH.LAYOUT_RES_IS, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final TestVH holder, final int position) {
        holder.mTestTv.setText(mItems.get(position));

    }

    @Override
    public int getItemCount() {
        return null != mItems ? mItems.size() : 0;
    }
}
