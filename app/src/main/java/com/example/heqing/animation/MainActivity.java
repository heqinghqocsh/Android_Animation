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
import com.example.heqing.animation.model.ListItemModel;
import com.example.heqing.animation.scene.SceneActivity;
import com.example.heqing.animation.svg.SvgCircleAnimActivity;
import com.example.heqing.animation.viewpager.ViewPagerMainActivity;
import com.example.heqing.animation.zooming.ZoomingActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HeQing on 2017/7/31.
 */
public class MainActivity extends AppCompatActivity
        implements AdapterView.OnItemClickListener {

    private List<ListItemModel> dataList = new ArrayList(16);
    private ListView listView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listview);

        dataList.add(new ListItemModel("ViewPager动画集合", ViewPagerMainActivity.class));
        dataList.add(new ListItemModel("Crossfading Two Views", CrossfadeActivity.class));
        dataList.add(new ListItemModel("Card Flip Animations", CardFlipActivity.class));
        dataList.add(new ListItemModel("Zooming a View", ZoomingActivity.class));
        dataList.add(new ListItemModel("scene", SceneActivity.class));
        dataList.add(new ListItemModel("SVG Circle Anim", SvgCircleAnimActivity.class));
        dataList.add(new ListItemModel("LayoutChangeAnim", LayoutChangeAnimActivity.class));
        dataList.add(new ListItemModel("CircularAnim", CircularAnimActivity.class));

        ArrayAdapter<ListItemModel> arrayAdapter = new ArrayAdapter<>(this
                , R.layout.text_list_item, R.id.textview, dataList);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (position < dataList.size()) {
            Intent intent = new Intent();
            intent.setClass(this, dataList.get(position).getCls());
            startActivity(intent);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dataList.clear();
    }

}
