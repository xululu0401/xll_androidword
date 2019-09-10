package com.xululu.animatortest;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private View mTestview;
    private Button mTestBtn;
    private Button mStopBtn;
    private TextView mTestTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTestview = findViewById(R.id.test_lyt);

        mTestBtn = findViewById(R.id.start_btn);
        mTestBtn.setOnClickListener(this);
        mStopBtn = findViewById(R.id.stop_btn);
        mStopBtn.setOnClickListener(this);
        mTestTv = findViewById(R.id.test_tv);
    }

    @Override
    public void onClick(View v) {
        if (v == mTestBtn){
            mTestview.setVisibility(View.VISIBLE);
            ObjectAnimator animator = ObjectAnimator.ofFloat(mTestTv, "translationX", -300, 0);
            animator.setDuration(10000);
            animator.start();
        } else if (v == mStopBtn){
            mTestview.setVisibility(View.GONE);
            mTestTv.setVisibility(View.VISIBLE);
        }
    }

}
