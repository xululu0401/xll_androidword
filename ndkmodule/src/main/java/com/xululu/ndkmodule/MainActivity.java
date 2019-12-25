package com.xululu.ndkmodule;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.googlecode.tesseract.android.TessBaseAPI;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TESSBASE_PATH = Environment.getExternalStorageDirectory().getAbsolutePath()+"/tesseract";
    private static final String DEFAULT_LANGUAGE = "eng";
    private static final String CHINESE_LANGUAGE = "chi_sim";

    private TextView mResultTv;
    private Button mGetNumBtn;
    private ImageView mNumIv;
    private TessBaseAPI mTessBaseAPI;
    private ImageView mResizedIv;
    private ImageView mBinaryIv;
    private ImageView mEroIv;


    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mResultTv = findViewById(R.id.result_tv);
        mGetNumBtn = findViewById(R.id.get_num_btn);
        mGetNumBtn.setOnClickListener(this);
        mNumIv = findViewById(R.id.num_iv);
        mResizedIv = findViewById(R.id.resized_iv);
        mBinaryIv = findViewById(R.id.binary_iv);
        mEroIv = findViewById(R.id.erod_iv);
    }

    private String getRecString(Bitmap bitmap) {
        mTessBaseAPI = new TessBaseAPI();
        mTessBaseAPI.init("/mnt/sdcard/tesseract", DEFAULT_LANGUAGE);
        mTessBaseAPI.setPageSegMode(TessBaseAPI.PageSegMode.PSM_AUTO);
        bitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true);
        mTessBaseAPI.setImage(bitmap);
        String result = mTessBaseAPI.getUTF8Text();
        mTessBaseAPI.clear();
        mTessBaseAPI.end();
        return result;
    }

    public native String stringFromJNI();
    public native int[] gray(int[] buf, int w, int h);

    @Override
    public void onClick(View v) {
        if (v == mGetNumBtn){
            Intent i = new Intent("com.xululu.close.app");
            sendBroadcast(i);
            final Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.idcard);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        //首先进行归一化
                        final Bitmap resizedBitmap = getResizedBitmap(bitmap);
                        //二值化的图像
                        final Bitmap binaryBitmap = getBinaryBitmap(resizedBitmap);
                        final Bitmap erodeBitmap = getErodeBitmap(binaryBitmap);
                        final Bitmap resultBitmap = findIdNumber(bitmap);
                        final String resultNum = getRecString(resultBitmap);
                        Log.d("llxu4 image", "  "+erodeBitmap);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mResizedIv.setImageBitmap(resizedBitmap);
                                mBinaryIv.setImageBitmap(binaryBitmap);
                                mEroIv.setImageBitmap(erodeBitmap);
                                mNumIv.setImageBitmap(resultBitmap);
                                mResultTv.setText(resultNum);
                            }
                        });

                    }
                }).start();
        }
    }

    private native Bitmap findIdNumber(Bitmap bitmap);

    private native Bitmap getResizedBitmap(Bitmap bitmap);

    private native Bitmap getBinaryBitmap(Bitmap bitmap);

    private native Bitmap getErodeBitmap(Bitmap bitmap);

    private native Bitmap getResultBitmap(Bitmap bitmap, Bitmap srcBitmap);


}
