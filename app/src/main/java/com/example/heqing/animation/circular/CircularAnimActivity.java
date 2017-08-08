package com.example.heqing.animation.circular;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.example.heqing.animation.R;

/**
 * Created by HeQing on 2017/8/8.
 */

public class CircularAnimActivity extends AppCompatActivity {

  private Button button1;
  private ProgressBar progressBar1;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_circular_anim_layout);
    button1 = (Button) findViewById(R.id.button_1);
    progressBar1 = (ProgressBar) findViewById(R.id.progress_1);
    button1.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        CircularAnimUtils.hideView(button1).start();
        progressBar1.setVisibility(View.VISIBLE);
      }
    });

    progressBar1.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        progressBar1.setVisibility(View.VISIBLE);
        CircularAnimUtils.showView(button1).start();
      }
    });

  }
}
