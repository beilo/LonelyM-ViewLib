package com.lonelymushroom.viewlib.beans;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2016/4/19.
 */
public class BensEntity implements Parcelable {
    private String title;
    private String icon;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.icon);
    }

    public BensEntity() {
    }

    protected BensEntity(Parcel in) {
        this.title = in.readString();
        this.icon = in.readString();
    }

    public static final Creator<BensEntity> CREATOR = new Creator<BensEntity>() {
        @Override
        public BensEntity createFromParcel(Parcel source) {
            return new BensEntity(source);
        }

        @Override
        public BensEntity[] newArray(int size) {
            return new BensEntity[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
