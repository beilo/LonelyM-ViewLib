package com.czcg.viewlib.beans;

import java.io.Serializable;

/**
 * Created by LeiP on 2016/4/13.
 */
public class AlertBean implements Serializable{

    /**
     * title : ssss
     * message : 这是一条消息
     * isJudgment : 用于判断是否有多键
     */

    private String title = "";
    private String message = "";
    private boolean isJudgment = false;


    public AlertBean() {
    }

    public AlertBean(String title, String message, boolean isJudgment) {
        this.title = title;
        this.message = message;
        this.isJudgment = isJudgment;
    }

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

    public boolean isIsJudgment() {
        return isJudgment;
    }

    public void setIsJudgment(boolean isJudgment) {
        this.isJudgment = isJudgment;
    }


}
