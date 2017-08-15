package com.example.heqing.animation.viewpager;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.heqing.animation.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HeQing on 2017/8/14 0014.
 * ViewPager(同时显示左右两边一点)方法二
 */

public class ViewPagerRightLeftActivity extends AppCompatActivity{

    private ViewPager viewPager;
    private PagerAdapter pagerAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_pager_right_left_layout);
        viewPager = (ViewPager) findViewById(R.id.pager);
        List<Integer> dataList = new ArrayList<>(5);
        dataList.add(R.drawable.demo);
        dataList.add(R.drawable.demo1);
        dataList.add(R.drawable.demo2);
        dataList.add(R.drawable.demo7);
        dataList.add(R.drawable.demo4);
        pagerAdapter = new MyPagerAdapter(this,dataList);
        viewPager.setOffscreenPageLimit(3);
        viewPager.setPageTransformer(true, new ZoomOutPageTransform());
        viewPager.setAdapter(pagerAdapter);
    }

    private static class MyPagerAdapter extends PagerAdapter{

        private Context context;
        private List<Integer> imgResIds;

        public MyPagerAdapter(Context context, List<Integer> imgResIds) {
            this.context = context;
            this.imgResIds = imgResIds;
        }

        @Override
        public int getCount() {
            if (imgResIds == null){
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
            ImageView image=new ImageView(context);
            image.setImageResource(imgResIds.get(position));
            image.setScaleType(ImageView.ScaleType.CENTER_CROP);
            container.addView(image);
            return image;
        }
    }

}
