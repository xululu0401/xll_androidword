package com.xululu.viewmodelmodule;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;

import com.xululu.viewmodelmodule.data.Student;

import java.util.List;

public class MainActivity extends FragmentActivity implements View.OnClickListener {

    private TextView mTv;
    private Button mAddBtn;
    private Button mRemoveBtn;
    private List<Student> mDatas;
    private MyViewModel mVM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTv = findViewById(R.id.count_tv);
        mAddBtn = findViewById(R.id.add_btn);
        mAddBtn.setOnClickListener(this);
        mRemoveBtn = findViewById(R.id.remove_btn);
        mRemoveBtn.setOnClickListener(this);

        mVM = ViewModelProviders.of(this).get(MyViewModel.class);
        mDatas =  mVM.getData();
        mTv.setText(mDatas.size()+"");

    }

    @Override
    public void onClick(View v) {
        if (v == mAddBtn) {
            mVM.addData();
            mTv.setText(mDatas.size()+"");
        } else if (v == mRemoveBtn) {
            mVM.removeDatas();
            mTv.setText(mDatas.size()+"");
        }

    }
}
