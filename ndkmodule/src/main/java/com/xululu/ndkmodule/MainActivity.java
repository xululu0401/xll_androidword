package com.xululu.ndkmodule;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Environment;
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

    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bitmap bitmap = ((BitmapDrawable) getResources().getDrawable(
                R.mipmap.idcard)).getBitmap();
        int w = bitmap.getWidth(), h = bitmap.getHeight();
        int[] pix = new int[w * h];
        bitmap.getPixels(pix, 0, w, 0, 0, w, h);
        // 调用JNI实现的gray方法
        int [] resultPixes = gray(pix,w,h);
        Bitmap result = Bitmap.createBitmap(w,h, Bitmap.Config.RGB_565);
        result.setPixels(resultPixes, 0, w, 0, 0,w, h);

        ImageView img = (ImageView)findViewById(R.id.img2);
        img.setImageBitmap(result);

        mResultTv = findViewById(R.id.result_tv);
        mGetNumBtn = findViewById(R.id.get_num_btn);
        mGetNumBtn.setOnClickListener(this);
        mNumIv = findViewById(R.id.num_iv);
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

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();
    public native int[] gray(int[] buf, int w, int h);

    @Override
    public void onClick(View v) {
        if (v == mGetNumBtn){
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.idcard);
            final Bitmap idNumberBitmap = findIdNumber(bitmap, Bitmap.Config.ARGB_8888);
            if (idNumberBitmap != null) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Log.d("llxu4 result", getRecString(idNumberBitmap));

                    }
                }).start();
            } else
                return;
        }
    }

    private native Bitmap findIdNumber(Bitmap bitmap, Bitmap.Config argb8888);


}
