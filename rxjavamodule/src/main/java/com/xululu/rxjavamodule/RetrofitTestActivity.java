package com.xululu.rxjavamodule;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.xululu.rxjavamodule.bean.Translation;
import com.xululu.rxjavamodule.bean.YDTranslation;
import com.xululu.rxjavamodule.request.inter.RequestInterface;
import com.xululu.rxjavamodule.request.inter.YDPostInterface;

import java.lang.ref.WeakReference;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitTestActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mReqBtn;
    private Handler mHandler = new MyHandler(this);
    private TextView mResultTv;
    private Button mPostTestBtn;
    private TextView mYDResultTv;

    private class MyHandler extends Handler{
        private WeakReference<RetrofitTestActivity> ref;

        private MyHandler(RetrofitTestActivity activity){
            ref = new WeakReference(activity);
        }
        @Override
        public void handleMessage(Message msg) {
            RetrofitTestActivity activity = ref.get();
            if (null == activity) {
                return;
            } else {
                switch (msg.what) {

                }
            }

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit_test);
        mReqBtn = findViewById(R.id.request_btn);
        mReqBtn.setOnClickListener(this);
        mResultTv = findViewById(R.id.request_result);
        mPostTestBtn = findViewById(R.id.post_test_btn);
        mPostTestBtn.setOnClickListener(this);
        mYDResultTv = findViewById(R.id.yd_result);
    }

    @Override
    public void onClick(View v) {
        if (v == mReqBtn) {
            reqest();
        } else if (v == mPostTestBtn) {
            postRequest();
        }
    }

    private void reqest(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://fy.iciba.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        final RequestInterface request = retrofit.create(RequestInterface.class);
        Call<Translation> call = request.getCall();
        call.enqueue(new Callback<Translation>() {
            @Override
            public void onResponse(final Call<Translation> call, final Response<Translation> response) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        Gson gson = new Gson();
                        Translation translation = response.body();
                        mResultTv.setText(translation.getContent().getOut());

                    }
                });

            }

            @Override
            public void onFailure(Call<Translation> call, Throwable t) {
                mResultTv.setText("翻译错误");
            }
        });
    }

    /**
     * post请求
     */
    private void postRequest(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://fanyi.youdao.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        YDPostInterface request = retrofit.create(YDPostInterface.class);
        Call<YDTranslation> call = request.getCall("I love you");
        call.enqueue(new Callback<YDTranslation>() {
            @Override
            public void onResponse(Call<YDTranslation> call, Response<YDTranslation> response) {
                mYDResultTv.setText(response.body().getTranslateResult().get(0).get(0).getSrc()
                        + "   ****   " + response.body().getTranslateResult().get(0).get(0).getTgt());
            }

            @Override
            public void onFailure(Call<YDTranslation> call, Throwable t) {
                mYDResultTv.setText(t.getMessage());
            }
        });

    }

}
