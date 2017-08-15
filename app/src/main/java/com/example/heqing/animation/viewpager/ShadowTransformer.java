package com.example.heqing.animation.viewpager;

import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.view.View;


public class ShadowTransformer implements ViewPager.OnPageChangeListener, ViewPager.PageTransformer {

    private CardAdapter mAdapter;
    private float mLastOffset;

    public ShadowTransformer(ViewPager viewPager, CardAdapter adapter) {
        viewPager.addOnPageChangeListener(this);
        mAdapter = adapter;
    }

    @Override
    public void transformPage(View page, float position) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        int realCurrentPosition;
        int nextPosition;
        float baseElevation = mAdapter.getBaseElevation();
        float realOffset;
        boolean goingLeft = mLastOffset > positionOffset;

        if (goingLeft) {
            realCurrentPosition = position + 1;
            nextPosition = position;
            realOffset = 1 - positionOffset;
        } else {
            nextPosition = position + 1;
            realCurrentPosition = position;
            realOffset = positionOffset;
        }

        if (nextPosition > mAdapter.getCount() - 1
                || realCurrentPosition > mAdapter.getCount() - 1) {
            return;
        }

        CardView currentCard = mAdapter.getCardViewAt(realCurrentPosition);

        if (currentCard != null) {
            currentCard.setScaleX((float) (1 + 0.1 * (1 - realOffset)));
            currentCard.setScaleY((float) (1 + 0.1 * (1 - realOffset)));
            currentCard.setCardElevation((baseElevation + baseElevation
                    * (CardAdapter.MAX_ELEVATION_FACTOR - 1) * (1 - realOffset)));
        }

        CardView nextCard = mAdapter.getCardViewAt(nextPosition);

        if (nextCard != null) {
            nextCard.setScaleX((float) (1 + 0.1 * (realOffset)));
            nextCard.setScaleY((float) (1 + 0.1 * (realOffset)));
            nextCard.setCardElevation((baseElevation + baseElevation
                    * (CardAdapter.MAX_ELEVATION_FACTOR - 1) * (realOffset)));
        }
        mLastOffset = positionOffset;
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
