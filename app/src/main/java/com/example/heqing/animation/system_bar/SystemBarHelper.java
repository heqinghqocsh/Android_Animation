package com.example.heqing.animation.system_bar;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.FloatRange;
import android.view.View;
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

}
