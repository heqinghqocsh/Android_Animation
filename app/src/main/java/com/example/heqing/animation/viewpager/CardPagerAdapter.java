package com.example.heqing.animation.viewpager;

import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.heqing.animation.R;

import java.util.ArrayList;
import java.util.List;

public class CardPagerAdapter extends PagerAdapter implements CardAdapter {

    private List<CardView> mViews;
    private List<Integer> imgResIds;
    private float mBaseElevation;

    public CardPagerAdapter(List<Integer> imgResIds) {
        this.imgResIds = imgResIds;
        mViews = new ArrayList<>();
        if (null != imgResIds){
            int size = imgResIds.size();
            for(int i=0;i<size;i++){
                mViews.add(null);
            }
        }
    }

    public float getBaseElevation() {
        return mBaseElevation;
    }

    @Override
    public CardView getCardViewAt(int position) {
        if (position < 0 || position >= mViews.size()){
            return null;
        }
        return mViews.get(position);
    }

    @Override
    public int getCount() {
        if (null == imgResIds){
            return 0;
        }
        return imgResIds.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        CardView cardView = (CardView)LayoutInflater.from(container.getContext())
                .inflate(R.layout.viewpager_card_item, container, false);
        container.addView(cardView);
        ImageView image = (ImageView) cardView.findViewById(R.id.image);
        image.setImageResource(imgResIds.get(position));
        if (mBaseElevation == 0) {
            mBaseElevation = cardView.getCardElevation();
        }
        cardView.setMaxCardElevation(mBaseElevation * MAX_ELEVATION_FACTOR);
        mViews.set(position, cardView);
        return cardView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
        mViews.set(position, null);
    }

}
