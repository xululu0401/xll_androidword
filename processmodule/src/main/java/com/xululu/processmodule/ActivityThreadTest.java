package com.xululu.processmodule;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ActivityThreadTest extends AppCompatActivity {

    private Button mGoBtn;
    private Button mStartServiceBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thrad_test);
        mGoBtn = findViewById(R.id.go_btn);
        mGoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityThreadTest.this, ActivityThreadTest2.class);
                startActivity(intent);
            }
        });
        mStartServiceBtn = findViewById(R.id.start_service_btn);
        mStartServiceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityThreadTest.this, ServiceThreadTest.class);
                startService(intent);
            }
        });
    }

}
