package com.xululu.applicationtestmodule;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mTestTv;
    private Handler mHandler = new Handler();

    public interface CallBackListener{
        void onCallBack(String str);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTestTv = findViewById(R.id.test_tv);
        new Thread(new Runnable() {
            @Override
            public void run() {
                testFun(new CallBackListener() {
                    @Override
                    public void onCallBack(final String str) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        mTestTv.setText(str);
                                    }
                                });
//                                mTestTv.setText(str);
//                                try {
//                                    Thread.sleep(1000);
//                                } catch (InterruptedException e) {
//                                    e.printStackTrace();
//                                }
                            }
                        });
                    }
                });
            }
        }).start();

    }

    private void testFun(CallBackListener listener){
        int i = 0;
        while (i < 3276799){
            listener.onCallBack("content:  "+ i++);
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

//        listener.onCallBack("test");
    }
}
