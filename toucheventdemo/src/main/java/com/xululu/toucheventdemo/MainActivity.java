package com.xululu.toucheventdemo;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private MyViewGroup mMyViewGroup;
    private MyButton mMyButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMyButton = findViewById(R.id.my_btn);
        mMyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMyButton.smoothScrollTo(200, 200);
            }
        });

    }
}
