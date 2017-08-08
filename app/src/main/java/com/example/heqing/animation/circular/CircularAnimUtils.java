package com.example.heqing.animation.circular;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.widget.ImageView;

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

  public static CircularActivityBuilder showActivity(Activity activity,View triggerView) {
    return new CircularActivityBuilder(activity,triggerView);
  }

  public static class CircularViewBuilder {
    private View animView, triggerView;
    private boolean show;
    private int duration = DEFAULT_ANIM_DURATION;
    private Float startRadius, endRadius;

    public CircularViewBuilder(View animView, boolean show) {
      this.animView = animView;
      this.show = show;
      if (show) {
        startRadius = 0f;
      } else {
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

      if (show && null == endRadius) {//显示
        endRadius = maxRadius + 0f;
      } else if (!show && null == startRadius) {//隐藏
        startRadius = maxRadius + 0f;
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

    private void doEnd() {
      if (show) {
        animView.setVisibility(View.VISIBLE);
      } else {
        animView.setVisibility(View.GONE);
      }
    }
  }

  public static class CircularActivityBuilder {
    private Activity activity;
    private View triggerView;
    private float minRadius = 0,maxRadius;
    private int colorOrImageRes = android.R.color.white;
    private int durationMills = DEFAULT_ANIM_DURATION;
    private OnAnimationEndListener onAnimationEndListener;
    private int enterAnim = android.R.anim.fade_in, exitAnim = android.R.anim.fade_out;
    private ImageView animImageView;
    private int cx,cy;
    private ViewGroup decorView;

    public CircularActivityBuilder(Activity activity, View triggerView) {
      this.activity = activity;
      this.triggerView = triggerView;
      decorView = (ViewGroup) activity.getWindow().getDecorView();
    }

    public CircularActivityBuilder startRadius(float startRadius) {
      this.minRadius = startRadius;
      return this;
    }

    public CircularActivityBuilder colorOrImgRes(int resId) {
      this.colorOrImageRes = resId;
      return this;
    }

    public CircularActivityBuilder duration(int duration) {
      durationMills = duration;
      return this;
    }

    public CircularActivityBuilder overridePendingTransition(int enterAnim, int exitAnim) {
      this.enterAnim = enterAnim;
      this.exitAnim = exitAnim;
      return this;
    }

    public void start(@Nullable OnAnimationEndListener onAnimationEndListener) {
      this.onAnimationEndListener = onAnimationEndListener;
      int[] triggerLocation = new int[2];
      triggerView.getLocationInWindow(triggerLocation);
      cx = triggerLocation[0] + triggerView.getWidth() / 2;
      cy = triggerLocation[1] + triggerView.getHeight() / 2;
      animImageView = new ImageView(activity);
      animImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
      animImageView.setImageResource(colorOrImageRes);
      final int w = decorView.getWidth();
      final int h = decorView.getHeight();
      decorView.addView(animImageView, w, h);

      final int maxW = Math.max(cx, w - cx);
      final int maxH = Math.max(cy, h - cy);
      maxRadius = (int) Math.sqrt(maxW * maxW + maxH * maxH) + 1;

      Animator anim = ViewAnimationUtils.createCircularReveal(animImageView, cx, cy, minRadius, maxRadius);
      anim.setDuration(durationMills);
      anim.addListener(new AnimatorListenerAdapter() {
        @Override
        public void onAnimationEnd(Animator animation) {
          super.onAnimationEnd(animation);
          doEnd();
          //处理返回至当前activity的动画
          dealReturnAnim();
        }
      });
      anim.start();
    }

    private void doEnd() {
      if (null != onAnimationEndListener) {
        onAnimationEndListener.onAnimationEnd();
        activity.overridePendingTransition(enterAnim, exitAnim);
      }
    }

    private void dealReturnAnim(){
      triggerView.postDelayed(new Runnable() {
        @Override
        public void run() {
          if (null == activity || activity.isFinishing()
              || null == animImageView || null == decorView){
            return;
          }
          Animator animator = ViewAnimationUtils.createCircularReveal(animImageView,cx,cy,maxRadius,minRadius);
          animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
              super.onAnimationEnd(animation);
              decorView.removeView(animImageView);
            }
          });
          animator.setDuration(durationMills);
          animator.start();
        }
      },500);
    }


  }

  public interface OnAnimationEndListener {
    void onAnimationEnd();
  }
}
