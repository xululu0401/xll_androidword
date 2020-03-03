package com.xululu.aidlmodule;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.xululu.aidlmodule.messengerdemo.MessengerService;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";

    private Button mMessengerBtn, mAidlBtn, mBindPoolBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMessengerBtn = findViewById(R.id.messenger_btn);
        mMessengerBtn.setOnClickListener(this);
        mAidlBtn = findViewById(R.id.aidl_btn);
        mAidlBtn.setOnClickListener(this);
        mBindPoolBtn = findViewById(R.id.bind_pool_btn);
        mBindPoolBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if (v == mMessengerBtn) {
            Intent intent = new Intent(this, MessengerActivity.class);
            startActivity(intent);
        } else if (v == mAidlBtn) {
            Intent intent = new Intent(this, BookManagerActivity.class);
            startActivity(intent);
        } else if (v == mBindPoolBtn) {
            Intent intent = new Intent(this, BinderPoolActivity.class);
            startActivity(intent);
        }

    }
}
