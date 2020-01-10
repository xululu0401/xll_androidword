package com.xululu.recycleviewdemo.normalrv;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.xululu.recycleviewdemo.R;
import com.xululu.recycleviewdemo.cusviews.CustomRVActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends Activity {

    private boolean mCanLoadMore = true;

    public void UpdateData(final OnUpdateFinishListener onUpdateFinishListener) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                onUpdateFinishListener.onUpdateFinished();
                mDatas.set(mAdapter.getItemCount() - 2, "这里是倒数第二个");
            }
        }, 5000);
    }

    public interface OnUpdateFinishListener{
        void onUpdateFinished();
    }

    private RecyclerView mRv;
    private TestAdapter mAdapter;
    private List<String> mDatas = new ArrayList<>();
    private Button mBtn;
    private Button mGoCusBtn;
    private int flag=0;
    private LinearLayoutManager mManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRv = findViewById(R.id.test_rv);
        mBtn = findViewById(R.id.test_btn);
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mAdapter){
                    addData();
                }
            }
        });
        mManager = new LinearLayoutManager(this);
        mRv.setLayoutManager(mManager);
        initData();
        mAdapter = new TestAdapter(this, mDatas, this);
        mRv.setAdapter(mAdapter);
        mRv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (mCanLoadMore && dy < 0) {
                    if (mManager.findFirstVisibleItemPosition() <= 2){
                        mCanLoadMore = false;
                        loadMoreDatas();
                    }

                }
            }
        });

        mGoCusBtn = findViewById(R.id.go_cus_btn);
        mGoCusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CustomRVActivity.class);
                startActivity(intent);
            }
        });
    }

    private void loadMoreDatas() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i< 30; i++){
            list.add(i + "**********" + new Random(i).nextInt());
        }
        mDatas.addAll(0, list);
        mAdapter.notifyDataSetChanged();
        mCanLoadMore = true;
    }

    private void initData() {
        for (int i = 0; i< 30; i++){
            mDatas.add(i + "**********" + new Random(i).nextInt());
        }
    }

    private void addData(){
        if (flag % 2 == 0) {

            for (int i = 0; i< 10; i++) {
                mDatas.add(i+"    ****flag****  "+ flag);
            }
            flag ++;
        } else {

            for (int i = 0; i< 10; i++) {
                mDatas.add(i+"    ****flag****  "+ flag);
            }
            flag ++;
        }
        mAdapter.notifyDataSetChanged();
    }
}
