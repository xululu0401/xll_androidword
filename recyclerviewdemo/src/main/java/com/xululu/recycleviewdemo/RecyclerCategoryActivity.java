package com.xululu.recycleviewdemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.xululu.recycleviewdemo.cusviews.CustomRVActivity;
import com.xululu.recycleviewdemo.normalrv.MainActivity;
import com.xululu.recycleviewdemo.rvanim.RvAnimationActivity;

public class RecyclerCategoryActivity extends AppCompatActivity {


    @butterknife.BindView(R.id.normal_btn)
    Button normalBtn;
    @butterknife.BindView(R.id.cus_scroller_btn)
    Button cusScrollerBtn;
    @butterknife.BindView(R.id.anim_btn)
    Button animBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_category);
        butterknife.ButterKnife.bind(this);
    }

    @butterknife.OnClick({R.id.normal_btn, R.id.cus_scroller_btn, R.id.anim_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.normal_btn:
                gotoPage(MainActivity.class);
                break;
            case R.id.cus_scroller_btn:
                gotoPage(CustomRVActivity.class);
                break;
            case R.id.anim_btn:
                gotoPage(RvAnimationActivity.class);
                break;
        }
    }

    private void gotoPage(Class cls) {
        Intent intent = new Intent(this, cls);
        startActivity(intent);
    }
}
