package com.xululu.animationmodule;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AnimationCategory extends AppCompatActivity {


    @BindView(R.id.frame_anim_btn)
    Button frameAnimBtn;
    @BindView(R.id.view_anim_btn)
    Button viewAnimBtn;
    @BindView(R.id.pro_anim_btn)
    Button proAnimBtn;
    @BindView(R.id.trans_anim_btn)
    Button transAnimBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_category);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.frame_anim_btn, R.id.view_anim_btn, R.id.pro_anim_btn, R.id.trans_anim_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.frame_anim_btn:
                goToSpecPage(FrameAnimationActivity.class);
                break;
            case R.id.view_anim_btn:
                break;
            case R.id.pro_anim_btn:
                break;
            case R.id.trans_anim_btn:
                break;
        }
    }

    private void goToSpecPage(Class cls) {
        Intent intent = new Intent(this, cls);
        startActivity(intent);
    }
}
