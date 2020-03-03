package com.xululu.recycleviewdemo.cusviews;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * Author: pipilu
 * Time: 2020-01-06 19:43
 */
public class TestAdapter extends RecyclerView.Adapter<TestViewHolder> {

    private List<String> mItems;
    private Context mContext;

    public TestAdapter(Context context, List<String> list) {
        mContext = context;
        mItems = list;

    }
    @NonNull
    @Override
    public TestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(TestViewHolder.RES_ITE_LAYOUT, null);
        return new TestViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TestViewHolder holder, int position) {
        holder.mTextView.setText(mItems.get(position));

    }

    @Override
    public int getItemCount() {
        return null != mItems ? mItems.size() : 0;
    }
}
