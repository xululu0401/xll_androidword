package com.xululu.stubviewmodule;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.view.ViewStub;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mAddBtn;
    private Button mChangeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAddBtn = findViewById(R.id.add_viewstub);
        mAddBtn.setOnClickListener(this);
        mChangeBtn = findViewById(R.id.change_viewstub);
        mChangeBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mAddBtn) {
            ViewStub viewStub = findViewById(R.id.text_vs);
            viewStub.inflate();

        } else if (v == mChangeBtn){


        }
    }
}
