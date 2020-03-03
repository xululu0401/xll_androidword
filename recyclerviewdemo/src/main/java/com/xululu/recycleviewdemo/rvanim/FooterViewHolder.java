package com.xululu.recycleviewdemo.rvanim;

import android.view.View;

import androidx.annotation.NonNull;

import com.xululu.recycleviewdemo.R;

/**
 * Author: pipilu
 * Time: 2020-01-17 14:12
 */
public class FooterViewHolder extends AnimViewHolder {

    public static final int RES_ITEM_FOOTER = R.layout.item_footer_lyt;

    public View mView;

    public FooterViewHolder(@NonNull View itemView) {
        super(itemView);
        mView = itemView.findViewById(R.id.guild_view);
    }
}
