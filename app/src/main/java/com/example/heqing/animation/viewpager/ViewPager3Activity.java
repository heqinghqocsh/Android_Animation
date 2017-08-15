package com.example.heqing.animation.viewpager;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.heqing.animation.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HeQing on 2017/8/14 0014.
 */

public class ViewPager3Activity extends AppCompatActivity {

  private ViewPager viewPager1;
  private ViewPager viewPager2;
  private PagerAdapter pager1Adapter;
  private CardPagerAdapter pager2Adapter;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.view_pager3_layout);
    viewPager1 = (ViewPager) findViewById(R.id.pager_1);
    viewPager2 = (ViewPager) findViewById(R.id.pager_2);


    List<Integer> dataList = new ArrayList<>(5);
    dataList.add(R.drawable.demo);
    dataList.add(R.drawable.demo1);
    dataList.add(R.drawable.demo2);
    dataList.add(R.drawable.demo7);
    dataList.add(R.drawable.demo4);
    pager1Adapter = new MyPagerAdapter(this, dataList);
    viewPager1.setPageMargin(20);
    viewPager1.setAdapter(pager1Adapter);

    pager2Adapter = new CardPagerAdapter(dataList);
    viewPager2.setPageMargin(20);
    viewPager2.setPageTransformer(false,new ZoomOutPageTransform());//new ScaleTransformer();
    viewPager2.setAdapter(pager2Adapter);
  }

  private static class MyPagerAdapter extends PagerAdapter {

    private Context context;
    private List<Integer> imgResIds;

    public MyPagerAdapter(Context context, List<Integer> imgResIds) {
      this.context = context;
      this.imgResIds = imgResIds;
    }

    @Override
    public int getCount() {
      if (imgResIds == null) {
        return 0;
      }
      return imgResIds.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
      return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
      container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
      View view = LayoutInflater.from(context).inflate(R.layout.viewpager_card_item, container, false);
      ImageView image = (ImageView) view.findViewById(R.id.image);
      image.setImageResource(imgResIds.get(position));
      container.addView(view);
      return view;
    }
  }

}
