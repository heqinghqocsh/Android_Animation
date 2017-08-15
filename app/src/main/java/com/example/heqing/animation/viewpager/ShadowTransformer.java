package com.example.heqing.animation.viewpager;

import android.support.v4.view.ViewPager;
import android.view.View;


public class ShadowTransformer implements ViewPager.PageTransformer {

    private ViewPager mViewPager;

    public ShadowTransformer() {
    }

    @Override
    public void transformPage(View page, float position) {
        if (mViewPager == null) {
            mViewPager = (ViewPager) page.getParent();
        }

        int leftInScreen = page.getLeft() - mViewPager.getScrollX();
        int centerXInViewPager = leftInScreen + page.getMeasuredWidth() / 2;
        int offsetX = centerXInViewPager - mViewPager.getMeasuredWidth() / 2;
        float offsetRate = (float) offsetX * 0.15f / mViewPager.getMeasuredWidth();
        float scaleFactor = 1 - Math.abs(offsetRate);

        if (scaleFactor > 0) {
            page.setScaleX(scaleFactor);
            page.setScaleY(scaleFactor);
        }
    }
}
