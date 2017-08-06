package com.example.heqing.animation.layout_anim;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.heqing.animation.R;

/**
 * Created by HeQing on 2017/8/6 0006.
 */

public class LayoutChangeAnimActivity extends AppCompatActivity {

    private ViewGroup mContainer;
    private TextView mAdd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_change);

        mContainer = (ViewGroup) findViewById(R.id.container);
        mAdd = (TextView) findViewById(R.id.add);
        mAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addItem();
            }
        });
    }

    private void addItem() {
        final View item = LayoutInflater.from(this)
                .inflate(R.layout.layout_change_item, mContainer, false);
        item.findViewById(R.id.remove).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContainer.removeView(item);
            }
        });
        mContainer.addView(item);
    }


}
