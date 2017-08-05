package com.example.heqing.animation.svg;

import android.graphics.drawable.AnimatedVectorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.heqing.animation.R;

/**
 * Created by HeQing on 2017/8/5 0005.
 */

public class SvgCircleAnimActivity extends AppCompatActivity
        implements View.OnClickListener {

    private ImageView[] imageViews;
    private ImageView circleHook;
    private Button begin;

    private AnimatedVectorDrawable[] animDrawable;
    private AnimatedVectorDrawable circleHookAnim;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_svg_circle_anim);
        imageViews = new ImageView[5];
        animDrawable = new AnimatedVectorDrawable[imageViews.length];

        imageViews[0] = (ImageView) findViewById(R.id.circle_1);
        imageViews[1] = (ImageView) findViewById(R.id.circle_2);
        imageViews[2] = (ImageView) findViewById(R.id.circle_3);
        imageViews[3] = (ImageView) findViewById(R.id.circle_4);
        imageViews[4] = (ImageView) findViewById(R.id.circle_5);
        circleHook = (ImageView) findViewById(R.id.circle_hook);

        begin = (Button) findViewById(R.id.begin);
        begin.setOnClickListener(this);
        for (int i = 0; i < imageViews.length; i++) {
            animDrawable[i] = (AnimatedVectorDrawable) getResources()
                    .getDrawable(R.drawable.circle_anim, null);
        }

        circleHookAnim = (AnimatedVectorDrawable) getResources()
                .getDrawable(R.drawable.circle_hook_anim, null);
        circleHook.setImageDrawable(circleHookAnim);
        circleHookAnim.start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.begin:
                startAnim();
                break;
        }
    }

    private void startAnim() {
        for (int i = 0; i < imageViews.length; i++) {
            imageViews[i].setImageDrawable(animDrawable[i]);
            animDrawable[i].start();
        }
        circleHook.setImageDrawable(circleHookAnim);
        circleHookAnim.start();
    }

}
