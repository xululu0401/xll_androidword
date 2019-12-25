package com.xululu.butterknifemodule;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class ButterKnifeActivity extends AppCompatActivity {

    @BindView(R.id.test_tv)
    TextView testTv;
    @BindView(R.id.test_btn)
    Button testBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_butter_knife);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.test_tv, R.id.test_btn})
    public void onClick(View view) {
        switch (view) {
            case R.id.test_tv:
                break;
            case R.id.test_btn:
                break;
        }
    }
}
