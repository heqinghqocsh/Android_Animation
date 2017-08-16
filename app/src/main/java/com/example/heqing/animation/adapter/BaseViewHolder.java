package com.example.heqing.animation.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.TextView;

/**
 * Created by 29926 on 2017/8/16.
 * Function：
 * Version：
 */

public class BaseViewHolder extends RecyclerView.ViewHolder{

  private final SparseArray<View> mViews;
  private View convertView;

  public BaseViewHolder(View itemView) {
    super(itemView);
    mViews = new SparseArray<>(10);
    convertView = itemView;
  }

  public View getConvertView(){
    return convertView;
  }

  public BaseViewHolder setText(int viewId,String text){
    TextView textView = getView(viewId);
    if (textView != null){
      textView.setText(text);
    }
    return this;
  }

  public <T extends View> T getView(int viewId){
    View view = mViews.get(viewId);
    if (null == view){
      view = convertView.findViewById(viewId);
      if (null == view){
        return null;
      }
      mViews.put(viewId,view);
    }
    return (T)view;
  }


}
