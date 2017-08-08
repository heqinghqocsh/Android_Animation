package com.example.heqing.animation;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.heqing.animation.cardflip.CardFlipActivity;
import com.example.heqing.animation.circular.CircularAnimActivity;
import com.example.heqing.animation.crossfading.CrossfadeActivity;
import com.example.heqing.animation.layout_anim.LayoutChangeAnimActivity;
import com.example.heqing.animation.scene.SceneActivity;
import com.example.heqing.animation.svg.SvgCircleAnimActivity;
import com.example.heqing.animation.viewpager.ScreenSlideActivity;
import com.example.heqing.animation.zooming.ZoomingActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by HeQing on 2017/7/31.
 */
public class MainActivity extends AppCompatActivity
        implements AdapterView.OnItemClickListener {

    private HashMap<String, Class> map = new HashMap<>(16);
    private List<String> dataList;
    private ListView listView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listview);

        map.put("ViewPager动画（缩放）", ScreenSlideActivity.class);
        map.put("ViewPager动画（Depth）", ScreenSlideActivity.class);
        map.put("Crossfading Two Views", CrossfadeActivity.class);
        map.put("Card Flip Animations", CardFlipActivity.class);
        map.put("Zooming a View", ZoomingActivity.class);
        map.put("scene", SceneActivity.class);
        map.put("SVG Circle Anim", SvgCircleAnimActivity.class);
        map.put("LayoutChangeAnim", LayoutChangeAnimActivity.class);
        map.put("CircularAnim", CircularAnimActivity.class);

        dataList = new ArrayList<>(map.keySet());
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this
                , R.layout.text_list_item, R.id.textview, dataList);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (position < dataList.size()) {
            Intent intent = new Intent();
            intent.setClass(this, map.get(dataList.get(position)));
            startActivity(intent);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        map.clear();
    }

}
