package com.example.heqing.animation.system_bar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.heqing.animation.R;
import com.example.heqing.animation.adapter.BaseQuickAdapter;
import com.example.heqing.animation.adapter.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 29926 on 2017/8/16.
 * Function：沉浸式状态栏效果二
 * Version：
 */

public class Immersive2StatusBarActivity extends AppCompatActivity {

  @BindView(R.id.tool_bar)
  Toolbar toolBar;
  @BindView(R.id.app_bar_layout)
  AppBarLayout appBarLayout;
  @BindView(R.id.recycler_view)
  RecyclerView recyclerView;
  @BindView(R.id.floating_action_btn)
  FloatingActionButton floatingActionBtn;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    //注意该activity没有actionBar是因为使用了样式，见AndroidManifest.xml
    setContentView(R.layout.immersive_2_activity_layout);
    ButterKnife.bind(this);
    setSupportActionBar(toolBar);
    getSupportActionBar().setTitle("标题");
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    SystemBarHelper.immersiveStatusBar(this);
    SystemBarHelper.setHeightAndPadding(this,toolBar);

    List<String> dataList = new ArrayList<>(20);
    for(int i=0;i<20;i++){
      dataList.add("记录："+i);
    }
    recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
    recyclerView.setAdapter(new BaseQuickAdapter<String>(this,dataList) {
      @Override
      protected int getLayoutId() {
        return R.layout.text_list_item;
      }

      @Override
      protected void convert(BaseViewHolder viewHolder, String data) {
        viewHolder.setText(R.id.textview,data);
      }
    });

  }



}
