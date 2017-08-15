package com.example.heqing.animation.viewpager;

import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.heqing.animation.R;

import java.util.List;

public class CardPagerAdapter extends PagerAdapter{

    private List<Integer> imgResIds;

    public CardPagerAdapter(List<Integer> imgResIds) {
        this.imgResIds = imgResIds;
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
        return cardView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

}
