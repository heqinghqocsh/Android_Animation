package com.example.heqing.animation.system_bar;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.heqing.animation.R;
import com.example.heqing.animation.adapter.BaseQuickAdapter;
import com.example.heqing.animation.adapter.BaseViewHolder;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SimpleFragment extends Fragment {
  private static final String ARG_TAB_NAME = "tab_name";
  @BindView(R.id.recycler_view)
  RecyclerView mRecyclerView;
  private Context mContext;
  private String mTabName;
  private ArrayList<String> dataList = new ArrayList<>();

  public static SimpleFragment newInstance(String tabName) {
    SimpleFragment fragment = new SimpleFragment();
    Bundle args = new Bundle();
    args.putSerializable(ARG_TAB_NAME, tabName);
    fragment.setArguments(args);
    return fragment;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (getArguments() != null) {
      mTabName = getArguments().getString(ARG_TAB_NAME);
    }
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    mContext = getActivity();

    View view = inflater.inflate(R.layout.recycler_view_layout, container, false);
    ButterKnife.bind(this, view);
    return view;
  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    for (int i = 0; i < 20; i++) {
      dataList.add(mTabName);
    }
    mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));

    mRecyclerView.setAdapter(new BaseQuickAdapter<String>(getActivity(),dataList) {
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

  @Override
  public void onDestroyView() {
    super.onDestroyView();
  }
}
