package com.lonelymushroom.viewlib.utils;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by leipe on 2017/10/24.
 */

public abstract class ClickListener implements Parcelable {
    public abstract void listener(BaseNiceDialog dialog, boolean flag);

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    public ClickListener() {
    }

    protected ClickListener(Parcel in) {
    }

    public final Creator<ClickListener> CREATOR = new Creator<ClickListener>() {
        @Override
        public ClickListener createFromParcel(Parcel source) {
            return new ClickListener(source) {
                @Override
                public void listener(BaseNiceDialog dialog, boolean flag) {

                }
            };
        }

        @Override
        public ClickListener[] newArray(int size) {
            return new ClickListener[size];
        }
    };
}
