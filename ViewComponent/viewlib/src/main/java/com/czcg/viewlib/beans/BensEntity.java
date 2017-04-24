package com.czcg.viewlib.beans;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/4/19.
 */
public class BensEntity implements Serializable {
    public BensEntity() {
    }

    public BensEntity(String title) {

        this.title = title;
    }

    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
