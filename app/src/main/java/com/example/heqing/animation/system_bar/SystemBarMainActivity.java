package com.example.heqing.animation.system_bar;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.heqing.animation.R;
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
 * Created by 29926 on 2017/8/16.
 * Function：沉浸式状态栏
 * Version：
 */

public class SystemBarMainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

  private List<ListItemModel> dataList = new ArrayList(16);
  private ListView listView;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    listView = (ListView) findViewById(R.id.listview);

    dataList.add(new ListItemModel("沉浸式状态栏（效果一）", Immersive1StatusBarActivity.class));
    dataList.add(new ListItemModel("沉浸式状态栏（效果二）", Immersive2StatusBarActivity.class));
    dataList.add(new ListItemModel("沉浸式状态栏（效果三）", Immersive3StatusBarActivity.class));

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
