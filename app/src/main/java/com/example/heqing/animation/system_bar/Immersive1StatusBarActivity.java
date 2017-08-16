package com.example.heqing.animation.system_bar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import com.example.heqing.animation.R;

/**
 * Created by 29926 on 2017/8/16.
 * Function：沉浸式状态栏效果一
 * Version：
 */

public class Immersive1StatusBarActivity extends AppCompatActivity{

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    //注意该activity没有actionBar是因为使用了样式，见AndroidManifest.xml
    setContentView(R.layout.immersive_1_activity_layout);
    SystemBarHelper.immersiveStatusBar(this);

  }
}
