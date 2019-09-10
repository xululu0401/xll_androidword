package com.xululu.hookmodule;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.xululu.hookmodule.utils.AMSHookHelper;
import com.xululu.hookmodule.utils.RefInvoke;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mJumpBtn;
    private Button mJumptoUnReg;
    private Button mJump2SecBtn;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
        try{
            AMSHookHelper.hookAmNS();
//            AMSHookHelper.attachBaseContext();
            AMSHookHelper.attachContext();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Instrumentation instrumentation = (Instrumentation) RefInvoke.getFieldObject(Activity.class, this, "mInstrumentation");
        EviInstrumentation eviInstrumentation = new EviInstrumentation(instrumentation);
        RefInvoke.setFieldObject(Activity.class, this, "mInstrumentation", eviInstrumentation);
        mJumpBtn = (Button) findViewById(R.id.test_btn);
        mJumpBtn.setOnClickListener(this);
        mJumptoUnReg = (Button) findViewById(R.id.jump_to_unreg);
        mJumptoUnReg.setOnClickListener(this);
        mJump2SecBtn = (Button) findViewById(R.id.jump2sec);
        mJump2SecBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view == mJumpBtn) {
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            startActivity(intent);
        } else if (view == mJumptoUnReg) {
            Intent intent = new Intent();
            ComponentName componentName = new ComponentName("com.xululu.hookmodule", "com.xululu.hookmodule.NoRegActivity");
            intent.setComponent(componentName);
            startActivity(intent);
        } else if (view == mJump2SecBtn) {
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            startActivity(intent);
        }
    }
}
