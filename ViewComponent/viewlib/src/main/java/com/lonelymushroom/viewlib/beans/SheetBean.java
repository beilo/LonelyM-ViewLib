package com.lonelymushroom.viewlib.beans;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/4/19.
 */
public class SheetBean implements Parcelable {


    /**
     * title :
     * btns : [{"title":"按钮1"},{"title":"按钮2"},{"title":"按钮3"}]
     */

    private String title = "";
    /**
     * title : 按钮1
     */

    private ArrayList<BensEntity> btns = new ArrayList<>();


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeTypedList(this.btns);
    }

    public SheetBean() {
    }

    protected SheetBean(Parcel in) {
        this.title = in.readString();
        this.btns = in.createTypedArrayList(BensEntity.CREATOR);
    }

    public static final Parcelable.Creator<SheetBean> CREATOR = new Parcelable.Creator<SheetBean>() {
        @Override
        public SheetBean createFromParcel(Parcel source) {
            return new SheetBean(source);
        }

        @Override
        public SheetBean[] newArray(int size) {
            return new SheetBean[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<BensEntity> getBtns() {
        return btns;
    }

    public void setBtns(ArrayList<BensEntity> btns) {
        this.btns = btns;
    }
}
