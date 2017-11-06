package com.czcg.viewlib.beans;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/4/19.
 */
public class BensEntity implements Serializable {
    public BensEntity() {
    }

    public BensEntity(int icon,String title) {
        this.icon = icon;
        this.title = title;
    }

    private String title;
    private int icon;

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
