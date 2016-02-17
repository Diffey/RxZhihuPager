package com.diffey.view.rxzhihu.bean;

/**
 * Created by diff on 2016/2/6.
 */
public class MenuItem {
    private String title;
    private int resId;

    public MenuItem(int resId, String title) {
        this.resId = resId;
        this.title = title;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
