package com.example.heqing.animation.viewpager;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.heqing.animation.R;
import com.example.heqing.animation.model.ListItemModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HeQing on 2017/8/14 0014.
 * 包含各种ViewPager动画效果
 */

public class ViewPagerMainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private List<ListItemModel> dataList = new ArrayList(16);
    private ListView listView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listview);

        dataList.add(new ListItemModel("ViewPager动画（缩放）", ViewPagerZoomActivity.class));
        dataList.add(new ListItemModel("ViewPager动画（Depth）", ViewPagerDepthActivity.class));
        dataList.add(new ListItemModel("ViewPager(显示左右两边一点)方法一", ViewPager3Activity.class));
        dataList.add(new ListItemModel("ViewPager(显示左右两边一点)方法二", ViewPagerRightLeftActivity.class));

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
