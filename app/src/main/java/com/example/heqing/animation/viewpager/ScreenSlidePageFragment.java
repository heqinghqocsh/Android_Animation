package com.example.heqing.animation.viewpager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.heqing.animation.R;

/**
 * Created by HeQing on 2017/7/31.
 */

public class ScreenSlidePageFragment extends Fragment {

    private static final String TEXT = "text";
    private static final String COLOR = "color";

    private TextView textView;

    private String text;
    private int color;

    public static ScreenSlidePageFragment create(String text, int color) {
        ScreenSlidePageFragment fragment = new ScreenSlidePageFragment();
        Bundle bundle = new Bundle();
        bundle.putString(TEXT, text);
        bundle.putInt(COLOR, color);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        text = bundle.getString(TEXT);
        color = bundle.getInt(COLOR);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_screen_slide_page, container, false);
        view.setBackgroundColor(color);
        textView = view.findViewById(R.id.content);
        textView.setText(text);
        return view;
    }

}
