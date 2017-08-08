package com.example.heqing.animation.circular;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.ViewAnimationUtils;

/**
 * Created by HeQing on 2017/8/8.
 */

public class CircularAnimUtils {

  private static final int DEFAULT_ANIM_DURATION = 600;

  public static CircularViewBuilder showView(View animView) {
    return new CircularViewBuilder(animView, true);
  }

  public static CircularViewBuilder hideView(View animView) {
    return new CircularViewBuilder(animView, false);
  }

  public static class CircularViewBuilder {
    private View animView, triggerView;
    private boolean show;
    private int duration = DEFAULT_ANIM_DURATION;
    private Float startRadius, endRadius;

    public CircularViewBuilder(View animView, boolean show) {
      this.animView = animView;
      this.show = show;
      if (show){
        startRadius = 0f;
      }else {
        endRadius = 0f;
      }
    }

    public CircularViewBuilder triggerView(View triggerView) {
      this.triggerView = triggerView;
      return this;
    }

    public CircularViewBuilder duration(int duration) {
      this.duration = duration;
      return this;
    }

    public CircularViewBuilder startRadius(float startRadius) {
      this.startRadius = startRadius;
      return this;
    }

    public CircularViewBuilder endRadius(float endRadius) {
      this.endRadius = endRadius;
      return this;
    }

    public void start() {
      if (null == animView) {
        return;
      }
      int centerX, centerY, maxRadius;
      if (null != triggerView) {
        int[] triggerLocation = new int[2];
        triggerView.getLocationInWindow(triggerLocation);//获取触发view 的坐标
        final int triggerCenterX = triggerLocation[0] + triggerView.getWidth() / 2;//获取触发view 的中心坐标x
        final int triggerCenterY = triggerLocation[1] + triggerView.getHeight() / 2;//获取触发view 的中心坐标y
        int[] animLocation = new int[2];
        animView.getLocationInWindow(animLocation);

        int triggerX = Math.max(animLocation[0], triggerCenterX);
        triggerX = Math.min(triggerX, animLocation[0] + animView.getWidth());
        int triggerY = Math.max(animLocation[1], triggerCenterY);
        triggerY = Math.min(triggerY, animLocation[1] + animView.getHeight());

        centerX = triggerX - animLocation[0];
        centerY = triggerY - animLocation[1];

        // 计算圆形中心点至 @animView 边界的最大距离
        final int maxH = Math.max(centerX, animView.getWidth() - centerX);
        final int maxW = Math.max(centerY, animView.getHeight() - centerY);
        maxRadius = (int) Math.sqrt(maxW * maxW + maxH * maxH) + 1;
      } else {
        centerX = (animView.getLeft() + animView.getRight()) / 2;
        centerY = (animView.getTop() + animView.getBottom()) / 2;
        int w = animView.getWidth();
        int h = animView.getHeight();
        maxRadius = (int) Math.sqrt(w * w + h * h) + 1;
      }

      if (show && null == endRadius){//显示
        endRadius = maxRadius+0f;
      }else if(!show && null == startRadius){//隐藏
        startRadius = maxRadius+0f;
      }
      Animator animator = ViewAnimationUtils.createCircularReveal(animView, centerX, centerY, startRadius, endRadius);
      animView.setVisibility(View.VISIBLE);
      animator.setDuration(duration);
      animator.addListener(new AnimatorListenerAdapter() {
        @Override
        public void onAnimationEnd(Animator animation) {
          super.onAnimationEnd(animation);
          doEnd();
        }
      });
      animator.start();
    }

    private void doEnd(){
      if (show){
        animView.setVisibility(View.VISIBLE);
      }else {
        animView.setVisibility(View.GONE);
      }
    }
  }
}
