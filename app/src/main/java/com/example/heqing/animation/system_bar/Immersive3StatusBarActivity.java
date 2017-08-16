package com.example.heqing.animation.system_bar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.heqing.animation.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 29926 on 2017/8/16.
 * Function：沉浸式状态栏效果三
 * Version：
 */

public class Immersive3StatusBarActivity extends AppCompatActivity {

  @BindView(R.id.tool_bar)
  Toolbar toolBar;
  @BindView(R.id.app_bar_layout)
  AppBarLayout appBarLayout;
  @BindView(R.id.tabs)
  TabLayout tabs;
  @BindView(R.id.viewpager)
  ViewPager viewpager;
  @BindView(R.id.floating_action_btn)
  FloatingActionButton floatingActionBtn;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    //注意该activity没有actionBar是因为使用了样式，见AndroidManifest.xml
    setContentView(R.layout.immersive_3_activity_layout);
    ButterKnife.bind(this);
    setSupportActionBar(toolBar);
    getSupportActionBar().setTitle("标题");
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    toolBar.setNavigationOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        onBackPressed();
      }
    });
    SystemBarHelper.immersiveStatusBar(this);
    SystemBarHelper.setHeightAndPadding(this, toolBar);

    SimpleViewPagerAdapter adapter = new SimpleViewPagerAdapter(getSupportFragmentManager());
    adapter.addFragment(SimpleFragment.newInstance("Android"), "Android");
    adapter.addFragment(SimpleFragment.newInstance("iOS"), "iOS");
    adapter.addFragment(SimpleFragment.newInstance("Windows"), "Windows");
    viewpager.setAdapter(adapter);
    tabs.setupWithViewPager(viewpager);
  }

  class SimpleViewPagerAdapter extends FragmentPagerAdapter {
    private final List<Fragment> mFragments = new ArrayList<>();
    private final List<String> mFragmentTitles = new ArrayList<>();

    public SimpleViewPagerAdapter(FragmentManager fm) {
      super(fm);
    }

    public void addFragment(Fragment fragment, String title) {
      mFragments.add(fragment);
      mFragmentTitles.add(title);
    }

    @Override
    public Fragment getItem(int position) {
      return mFragments.get(position);
    }

    @Override
    public int getCount() {
      return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
      return mFragmentTitles.get(position);
    }
  }


}
