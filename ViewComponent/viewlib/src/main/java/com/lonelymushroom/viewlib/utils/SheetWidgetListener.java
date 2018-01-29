package com.lonelymushroom.viewlib.utils;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by leipe on 2018/1/29.
 */

public abstract class SheetWidgetListener implements Parcelable {
    public abstract void listener(String title);

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    public SheetWidgetListener() {
    }

    protected SheetWidgetListener(Parcel in) {
    }

    public final Creator<SheetWidgetListener> CREATOR = new Creator<SheetWidgetListener>() {
        @Override
        public SheetWidgetListener createFromParcel(Parcel source) {
            return new SheetWidgetListener(source) {
                @Override
                public void listener(String title) {

                }
            };
        }

        @Override
        public SheetWidgetListener[] newArray(int size) {
            return new SheetWidgetListener[size];
        }
    };
}
