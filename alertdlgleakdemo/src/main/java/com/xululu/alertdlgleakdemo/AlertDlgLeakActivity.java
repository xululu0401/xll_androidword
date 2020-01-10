package com.xululu.alertdlgleakdemo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * 在使用dialog的过程中发现AlertDialog存在内存泄漏的情况，
 * 如果要处理AlertDialog中引起的内存泄漏，可以参考该模块。
 * 对于短时间内存泄漏，个人认为不处理也没有大的影响。
 */

public class AlertDlgLeakActivity extends AppCompatActivity {

    private Button mTestBtn;

    //自定义listener，防止内存泄漏
    private MyListener mPosListener = MyListener.wrap(new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            Toast.makeText(AlertDlgLeakActivity.this, "ok", Toast.LENGTH_SHORT).show();
        }
    });
    private MyListener mNegListener = MyListener.wrap(new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            Toast.makeText(AlertDlgLeakActivity.this, "cancel", Toast.LENGTH_SHORT).show();
        }
    });



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert_dlg_leak);
        mTestBtn = findViewById(R.id.alert_leak_btn);
        mTestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alertDialog = new AlertDialog.Builder(AlertDlgLeakActivity.this)
                        .setPositiveButton("ok", mPosListener)
                        .setNegativeButton("cancel", mNegListener).create();
                mPosListener.clearOnDetach(alertDialog);
                mNegListener.clearOnDetach(alertDialog);
                alertDialog.show();
            }
        });
    }
}
