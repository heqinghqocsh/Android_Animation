package com.example.heqing.animation.circular;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.heqing.animation.R;

/**
 * Created by 29926 on 2017/8/8.
 * Function：
 * Version：
 */

public class CircularSecondActivity extends AppCompatActivity{

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_circular_second_layout);
  }

  @Override
  public void onBackPressed() {
    super.onBackPressed();
    overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
  }
}
