package com.xululu.hookmodule;

import android.content.Context;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.xululu.hookmodule.utils.Utils;
import com.xululu.mypluginlibrary.IBean;

import java.io.File;
import java.lang.reflect.Method;

import dalvik.system.DexClassLoader;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {

    private String apkName = "plugin.apk";
    private String dexPath = null;
    private File fileRelease = null;
    private DexClassLoader mClassLoader = null;

    private Button mBtn;
    private TextView mShowTv;


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
        Utils.extractAssets(newBase, apkName);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        mBtn = (Button) findViewById(R.id.test_btn);
        mBtn.setOnClickListener(this);
        mShowTv = (TextView) findViewById(R.id.show_tv);

        File extractFile = getFileStreamPath(apkName);
        dexPath = extractFile.getPath();
        fileRelease = getDir("dex", 0);
        mClassLoader = new DexClassLoader(dexPath, fileRelease.getAbsolutePath(), null, getClassLoader());
    }

    @Override
    public void onClick(View v) {
        if (v == mBtn) {
            Class mLoadClassBean;
            try {
                mLoadClassBean = mClassLoader.loadClass("com.xululu.plugindemo.Bean");
                Object beanObject = mLoadClassBean.newInstance();
//                Method getNameMethod = mLoadClassBean.getMethod("getName");
//                getNameMethod.setAccessible(true);
//                String name = (String) getNameMethod.invoke(beanObject);
//                mShowTv.setText(name);
                IBean bean = (IBean) beanObject;
                bean.setName("pipibao");
                mShowTv.setText(bean.getName());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
