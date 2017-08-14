package com.example.heqing.animation.model;

/**
 * Created by HeQing on 2017/8/14 0014.
 */

public class ListItemModel {

    private String title;
    private Class cls;

    public ListItemModel() {
    }

    public ListItemModel(String title, Class cls) {
        this.title = title;
        this.cls = cls;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Class getCls() {
        return cls;
    }

    public void setCls(Class cls) {
        this.cls = cls;
    }

    @Override
    public String toString() {
        return title;
    }
}
