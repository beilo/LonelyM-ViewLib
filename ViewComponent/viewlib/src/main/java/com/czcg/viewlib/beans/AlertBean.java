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
    private String confirm = "";
    private String cancel = "";
    private boolean isJudgment = false;


    public AlertBean() {
    }

    public AlertBean(String title, String message, String confirm, String cancel, boolean isJudgment) {
        this.title = title;
        this.message = message;
        this.confirm = confirm;
        this.cancel = cancel;
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
