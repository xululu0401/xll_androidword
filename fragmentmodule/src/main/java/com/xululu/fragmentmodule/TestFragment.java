package com.xululu.fragmentmodule;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Author: llxu4
 * Time: 2019-07-17 15:43
 */
public class TestFragment  extends Fragment {

    private TextView mTv;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.test_fragment_layout, container,false);
        mTv = view.findViewById(R.id.testtv);
        return view;
    }

    public void setTvContent(String contentStr){
        mTv.setText(contentStr);
    }
}
