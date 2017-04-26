package com.czcg.viewlib.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/4/19.
 */
public class SheetBean implements Serializable {


    /**
     * title :
     * btns : [{"title":"按钮1"},{"title":"按钮2"},{"title":"按钮3"}]
     */

    private String title = "";
    /**
     * title : 按钮1
     */

    private List<BensEntity> btns = new ArrayList<>();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<BensEntity> getBtns() {
        return btns;
    }

    public void setBtns(List<BensEntity> btns) {
        this.btns = btns;
    }

}
