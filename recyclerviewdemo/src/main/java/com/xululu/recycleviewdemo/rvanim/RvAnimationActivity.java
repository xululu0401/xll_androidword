package com.xululu.recycleviewdemo.rvanim;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;

import com.xululu.recycleviewdemo.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RvAnimationActivity extends AppCompatActivity {

    private static final String TAG = "RvAnimationActivity";

    @BindView(R.id.anim_rv)
    RecyclerView animRv;
    @BindView(R.id.add_btn)
    Button addBtn;
    @BindView(R.id.dlt_btn)
    Button dltBtn;
    @BindView(R.id.get_height_btn)
    Button getHeightBtn;
    @BindView(R.id.change_btn)
    Button changeBtn;

    private LinearLayoutManager mManager;
    private AnimRvAdapter mAdapter;

    private List<String> mDatas = new ArrayList<>();

    private CustomItemAnimator mAnimator;

    private Handler mHandler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rv_animation);
        ButterKnife.bind(this);

        mManager = new LinearLayoutManager(this);
        mManager.setStackFromEnd(true);
        animRv.setLayoutManager(mManager);
        initData();
        mAdapter = new AnimRvAdapter(this, mDatas);
        animRv.setAdapter(mAdapter);

        mAnimator = new CustomItemAnimator();
        mAnimator.setAddDuration(400);
        mAnimator.setRemoveDuration(2000);
        animRv.setItemAnimator(mAnimator);


        ((SimpleItemAnimator) animRv.getItemAnimator()).setSupportsChangeAnimations(false);
        SimpleItemAnimator defaultItemAnimator = ((SimpleItemAnimator) animRv.getItemAnimator());
        if (null != defaultItemAnimator) {
            defaultItemAnimator.setChangeDuration(0);
//            defaultItemAnimator.setMoveDuration(0);
//            defaultItemAnimator.setRemoveDuration(0);
//            defaultItemAnimator.setSupportsChangeAnimations(false);
        }
        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                View view = mManager.findViewByPosition(mAdapter.getItemCount() - 2);
                if (view == null) {
                    return;
                }
                Log.d(TAG, "run: the height of the last second view:  " + view.getHeight());
            }
        });

//        setWindowImmersive(this);

    }

    private void initData() {
        for (int i = 0; i < 100; i++) {
            Random random = new Random(i);
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < random.nextInt(100); j++) {
                sb.append(i).append("**");
            }
            mDatas.add(sb.toString());
        }
    }


    @OnClick({R.id.add_btn, R.id.dlt_btn, R.id.get_height_btn, R.id.change_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add_btn:
                mDatas.add(mDatas.size(), "这是一条新增的消息记录，用于测试增加动画");
//                mAdapter.notifyDataSetChanged();
                mAdapter.notifyItemInserted(mDatas.size() - 1);
                break;
            case R.id.dlt_btn:
                if (mDatas.size() > 0) {
                    mDatas.remove(mDatas.size() - 1);
                    mAdapter.notifyDataSetChanged();
                }
                break;
            case R.id.get_height_btn:
                View v = mManager.findViewByPosition(mAdapter.getItemCount() - 2);
                if (v == null) {
                    return;
                }
                Log.d(TAG, "run: the height of the last second view:  " + v.getHeight());
                break;
            case R.id.change_btn:
                String curStr = mDatas.get(mDatas.size() - 1);
                curStr = curStr + " ***   ";
                mDatas.set(mDatas.size() - 1, curStr);
                mAdapter.notifyItemChanged(mAdapter.getItemCount() - 3);
                break;
        }
    }

    private static boolean setWindowImmersive(Activity activity) {
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = activity.getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            activity.getWindow().setStatusBarColor(Color.TRANSPARENT);
            return true;
        } else {
            return false;
        }
    }

}
