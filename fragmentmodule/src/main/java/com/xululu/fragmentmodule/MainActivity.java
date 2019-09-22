package com.xululu.fragmentmodule;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mTestBtn;
    private Button mRmBtn;
    private TestFragment2 fragment2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        final TestFragment fragment = new TestFragment();
        fragment2 = new TestFragment2();
        transaction.add(R.id.container, fragment).commit();
        mTestBtn = findViewById(R.id.test_btn);
        mRmBtn = findViewById(R.id.remove_btn);
        mRmBtn.setOnClickListener(this);
        mTestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().add(R.id.container, fragment2).commitNow();
            }
        });

    }

    @Override
    public void onClick(View v) {
        if (v == mRmBtn){
            getSupportFragmentManager().beginTransaction().remove(fragment2).commitNow();
        }
    }
}
