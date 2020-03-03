package com.xululu.recycleviewdemo.rvanim;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * Author: pipilu
 * Time: 2020-01-13 16:56
 */
public class AnimRvAdapter extends RecyclerView.Adapter<AnimViewHolder> {

    private static final String TAG = "AnimRvAdapter";

    private static final int ITEM_TYPE_NORMAL = 0;
    private static final int ITEM_TYPE_FOOTER_GUID = 1;
    private static final int ITEM_TYPE_FOOTER_HELPER = 2;

    private Context mContext;
    private List<String> mDatas;

    public AnimRvAdapter(Context context, List<String> datas) {
        mContext = context;
        mDatas = datas;
    }


    @NonNull
    @Override
    public AnimViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        if (viewType == ITEM_TYPE_NORMAL) {
            view= LayoutInflater.from(mContext).inflate(AnimViewHolder.RES_ANIM_ITEM, parent, false);
            return new AnimViewHolder(view);
        } else if (viewType == ITEM_TYPE_FOOTER_GUID) {
            view = LayoutInflater.from(mContext).inflate(FooterViewHolder.RES_ITEM_FOOTER, parent, false);
            return new FooterViewHolder(view);
        } else {
            view = LayoutInflater.from(mContext).inflate(FooterHelperViewHolder.RES_ITEM_FOOTER_HELPER, parent, false);
            return new FooterHelperViewHolder(view);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull AnimViewHolder holder, int position) {
        int type = getItemViewType(position);
//        if (type == ITEM_TYPE_FOOTER_GUID || type == ITEM_TYPE_FOOTER_HELPER) {
//            return;
//        }
        if (type == ITEM_TYPE_NORMAL) {
            holder.testTv.setText(mDatas.get(position));
            holder.mRootView.setBackgroundColor(position % 2 == 0 ? Color.RED : Color.YELLOW);
        }
    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "the item count:   " + (mDatas.size() + 2));
        return null != mDatas ? mDatas.size() + 2 : 2;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == getItemCount() - 1) {
            return ITEM_TYPE_FOOTER_HELPER;
        } else if (position == getItemCount() - 2) {
            return ITEM_TYPE_FOOTER_GUID;
        } else {
            return ITEM_TYPE_NORMAL;
        }
    }
}
