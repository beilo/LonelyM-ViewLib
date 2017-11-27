package com.lonelymushroom.viewlib.beans;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by LeiP on 2016/4/13.
 */
public class AlertBean implements Parcelable{

    /**
     * title : ssss
     * message : 这是一条消息
     * isJudgment : 用于判断是否有多键
     */

    private String title = "";
    private String message = "";
    private String confirm = "";
    private String cancel = "";
    private boolean isJudgment = false;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.message);
        dest.writeString(this.confirm);
        dest.writeString(this.cancel);
        dest.writeByte(this.isJudgment ? (byte) 1 : (byte) 0);
    }

    public AlertBean() {
    }

    protected AlertBean(Parcel in) {
        this.title = in.readString();
        this.message = in.readString();
        this.confirm = in.readString();
        this.cancel = in.readString();
        this.isJudgment = in.readByte() != 0;
    }

    public static final Creator<AlertBean> CREATOR = new Creator<AlertBean>() {
        @Override
        public AlertBean createFromParcel(Parcel source) {
            return new AlertBean(source);
        }

        @Override
        public AlertBean[] newArray(int size) {
            return new AlertBean[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getConfirm() {
        return confirm;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }

    public String getCancel() {
        return cancel;
    }

    public void setCancel(String cancel) {
        this.cancel = cancel;
    }

    public boolean isJudgment() {
        return isJudgment;
    }

    public void setJudgment(boolean judgment) {
        isJudgment = judgment;
    }
}
