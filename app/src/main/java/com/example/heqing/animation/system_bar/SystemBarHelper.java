package com.example.heqing.animation.system_bar;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.FloatRange;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by 29926 on 2017/8/16.
 * Function：状态栏帮助类
 * Version：
 */

public class SystemBarHelper {
  private static float DEFAULT_ALPHA = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP ? 0.2f : 0.3f;

  public static void immersiveStatusBar(Activity activity) {
    immersiveStatusBar(activity, DEFAULT_ALPHA);
  }

  public static void immersiveStatusBar(Activity activity, @FloatRange(from = 0.0, to = 1.0) float alpha) {
    immersiveStatusBar(activity.getWindow(), alpha);
  }

  public static void immersiveStatusBar(Window window, @FloatRange(from = 0.0, to = 1.0) float alpha) {
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
      return;
    }
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
      window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
      window.setStatusBarColor(Color.TRANSPARENT);

      int systemUiVisibility = window.getDecorView().getSystemUiVisibility();
      systemUiVisibility |= View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
      systemUiVisibility |= View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
      window.getDecorView().setSystemUiVisibility(systemUiVisibility);
    } else {
      window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
    }
  }

  /** 增加View的高度以及paddingTop,增加的值为状态栏高度.一般是在沉浸式全屏给ToolBar用的 */
  public static void setHeightAndPadding(Context context, View view) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
      ViewGroup.LayoutParams lp = view.getLayoutParams();
      lp.height += getStatusBarHeight(context);//增高
      view.setPadding(view.getPaddingLeft(), view.getPaddingTop() + getStatusBarHeight(context),
          view.getPaddingRight(), view.getPaddingBottom());
    }
  }

  /** 获取状态栏高度 */
  public static int getStatusBarHeight(Context context) {
    int result = 0;
    int resId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
    if (resId > 0) {
      result = context.getResources().getDimensionPixelSize(resId);
    }
    return result;
  }

}
