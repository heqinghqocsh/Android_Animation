package com.example.heqing.animation.viewpager;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Created by HeQing on 2017/7/31.
 */

public class ZoomOutPageTransform implements ViewPager.PageTransformer {

  private static final float MIN_SCALE = 0.7f;

  @Override
  public void transformPage(View page, float position) {
    if (position < -1 || position > 1) {
      page.setScaleX(MIN_SCALE);
      page.setScaleY(MIN_SCALE);
    } else if (position <= 1) { // [-1,1]
      float scale = 1 - 0.2f * Math.abs(position);
      page.setScaleX(scale);
      page.setScaleY(scale);
    }
  }
}
