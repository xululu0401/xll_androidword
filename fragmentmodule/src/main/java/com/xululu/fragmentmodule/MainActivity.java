package com.xululu.fragmentmodule;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button mTestBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        final TestFragment fragment = new TestFragment();
        transaction.add(R.id.container, fragment).commitAllowingStateLoss();
        mTestBtn = findViewById(R.id.test_btn);
        mTestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment.setTvContent("呵呵呵");
            }
        });

    }
}
