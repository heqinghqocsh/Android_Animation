package com.example.heqing.animation;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.heqing.animation.cardflip.CardFlipActivity;
import com.example.heqing.animation.crossfading.CrossfadeActivity;
import com.example.heqing.animation.viewpager.ScreenSlideActivity;
import com.example.heqing.animation.zooming.ZoomingActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by HeQing on 2017/7/31.
 */
public class MainActivity extends AppCompatActivity {

    @BindView(R.id.viewpager_zoom)
    TextView viewpagerZoom;
    @BindView(R.id.viewpager_depth)
    TextView viewpagerDepth;
    @BindView(R.id.crossfade)
    TextView crossfade;
    @BindView(R.id.card_flip)
    TextView cardFlip;

    private Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);

    }

    @OnClick({R.id.viewpager_zoom, R.id.viewpager_depth
            , R.id.crossfade,R.id.card_flip,R.id.zoom_view})
    public void onViewClicked(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.viewpager_zoom:
                intent.setClass(this, ScreenSlideActivity.class);
                intent.putExtra("anim", 1);
                break;
            case R.id.viewpager_depth:
                intent.setClass(this, ScreenSlideActivity.class);
                intent.putExtra("anim", 2);
                break;
            case R.id.crossfade:
                intent.setClass(this, CrossfadeActivity.class);
                break;
            case R.id.card_flip:
                intent.setClass(this, CardFlipActivity.class);
                break;
            case R.id.zoom_view:
                intent.setClass(this, ZoomingActivity.class);
                break;
        }
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

}
