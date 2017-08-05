package com.example.heqing.animation.crossfading;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.heqing.animation.R;

/**
 * Created by HeQing on 2017/8/5 0005.
 */

public class CrossfadeActivity extends AppCompatActivity {

    private View contentView;
    private View loadingView;

    private int mLongAnimDuration;
    private Handler handler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crossfade);
        contentView = findViewById(R.id.content);
        loadingView = findViewById(R.id.loading);
        contentView.setVisibility(View.GONE);

        mLongAnimDuration = getResources().getInteger(android.R.integer.config_longAnimTime);

        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                crossfade();
            }
        };

        handler.sendEmptyMessageDelayed(1,2000);
    }

    private void crossfade() {
        contentView.setAlpha(0);
        contentView.setVisibility(View.VISIBLE);
        contentView
                .animate()
                .alpha(1f)
                .setDuration(mLongAnimDuration)
                .setListener(null);

        loadingView
                .animate()
                .alpha(0)
                .setDuration(mLongAnimDuration)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        loadingView.setVisibility(View.GONE);
                    }
                });
    }


}
