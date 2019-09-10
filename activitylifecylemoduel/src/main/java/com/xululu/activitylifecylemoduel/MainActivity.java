package com.xululu.activitylifecylemoduel;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "pipiluMainActivity";

    private Button mDlgBtn;
    private TextView mShowTv;
    private Button mChangeBtn;
    private Button mJumpBtn;
    private EditText mTestRestoreBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDlgBtn = findViewById(R.id.dlg_btn);
        mDlgBtn.setOnClickListener(this);
        mShowTv = findViewById(R.id.show_tv);
        mChangeBtn = findViewById(R.id.change_tv_btn);
        mChangeBtn.setOnClickListener(this);
        mJumpBtn = findViewById(R.id.jump_to_sec);
        mJumpBtn.setOnClickListener(this);
        Log.d(TAG, "oncreate");
        mTestRestoreBtn = findViewById(R.id.test_restore_edt);
    }

    @Override
    public void onClick(View v) {
        if (v == mDlgBtn) {
            Dialog dialog = new Dialog(this);
            dialog.setContentView(LayoutInflater.from(this).inflate(R.layout.dlt_layout, null));
            dialog.setTitle("test");
            dialog.show();
        } else if (v == mChangeBtn) {
            mShowTv.setText("修改后的值");
        } else if (v == mJumpBtn) {
            Intent intent = new Intent("pipilu");
            intent.setDataAndType(Uri.parse("http://www.pipilu.com:20"), "image/jpg");
            startActivity(intent);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onstart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onresume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG,"onpause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onstop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "ondestroy");
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onsaveInstance");

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d(TAG, "onrestoreInstance");
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

    @Override
    public void finish() {
        super.finish();
        Log.d(TAG, "onfinished");
    }
}
