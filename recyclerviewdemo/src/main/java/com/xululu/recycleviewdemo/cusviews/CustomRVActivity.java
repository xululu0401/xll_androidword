package com.xululu.recycleviewdemo.cusviews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.xululu.recycleviewdemo.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CustomRVActivity extends AppCompatActivity {

    private RecyclerView mRv;

    private TestAdapter mAdapter;
    private TestLineaLayoutMgr mMgr;

    private List<String> mDatas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_rv);
        mRv = findViewById(R.id.test_rv);
        mMgr = new TestLineaLayoutMgr(this);
        mRv.setLayoutManager(mMgr);
        initData();
        mAdapter = new TestAdapter(this, mDatas);
        mRv.setAdapter(mAdapter);
        mRv.smoothScrollToPosition(23);

    }

    private void initData() {
        for (int i = 0; i < 50; i++) {
            if (i == 23) {
                mDatas.add("test 测试 test 测试test 测试test 测试test 测试test 测试test 测试test 测试test " +
                        "测试 test 测试 test 测试test 测试test 测试test 测试test 测试test 测试test 测试test" +
                        " 测试test 测试 test 测试test 测试test 测试test 测试test 测试test 测试test 测试test " +
                        "测试test 测试 test 测试test 测试test 测试test 测试test 测试test 测试test 测试test 测试");
                continue;
            }
            Random random = new Random(i);
            mDatas.add("****   " + String.valueOf(random.nextInt())  + "&&&&&");
        }


    }
}
