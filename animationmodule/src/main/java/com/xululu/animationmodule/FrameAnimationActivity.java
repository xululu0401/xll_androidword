package com.xululu.animationmodule;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.widget.ImageView;

public class FrameAnimationActivity extends AppCompatActivity {

    private ImageView mFrameAniIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        mFrameAniIv = findViewById(R.id.frame_ani_iv);
        mFrameAniIv.setBackgroundResource(R.drawable.frame_anim);
        AnimationDrawable animationDrawable = (AnimationDrawable)mFrameAniIv.getBackground();
        animationDrawable.start();

    }




}
