package com.example.heqing.animation.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 29926 on 2017/8/16.
 * Function：
 * Version：
 */

public abstract class BaseQuickAdapter<T> extends RecyclerView.Adapter<BaseViewHolder>{

  private Context context;
  private List<T> dataList;
  private LayoutInflater layoutInflater;
  private int layoutResId;

  public BaseQuickAdapter(Context context) {
    this(context,null);
  }

  public BaseQuickAdapter(Context context, List<T> dataList) {
    this.context = context;
    layoutInflater = LayoutInflater.from(context);
    if (null == dataList){
      this.dataList = new ArrayList<>();
    }else {
      this.dataList = dataList;
    }
    layoutResId = getLayoutId();
    if (layoutResId == 0){
      throw new IllegalArgumentException("Layout resource ID must be valid!");
    }
  }

  @LayoutRes
  protected abstract int getLayoutId();

  protected abstract void convert(BaseViewHolder viewHolder,T data);

  @Override
  public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return onCreateDefViewHolder(parent,viewType);
  }

  protected BaseViewHolder onCreateDefViewHolder(ViewGroup parent, int viewType) {
    return createBaseViewHolder(parent, layoutResId);
  }

  protected BaseViewHolder createBaseViewHolder(ViewGroup parent, int layoutResId) {
    View view = layoutInflater.inflate(layoutResId, parent, false);
    return new BaseViewHolder(view);
  }

  @Override
  public void onBindViewHolder(BaseViewHolder holder, int position) {
    convert(holder,dataList.get(position));
  }

  public void addData(T data){
    if (null != data){
      dataList.add(data);
      notifyItemInserted(dataList.size() - 1);
    }
  }

  public void setDataList(List<T> dataList){
    if (dataList != null){
      this.dataList = dataList;
    }
    notifyDataSetChanged();
  }

  @Override
  public int getItemCount() {
    if (null == dataList){
      return 0;
    }
    return dataList.size();
  }
}
