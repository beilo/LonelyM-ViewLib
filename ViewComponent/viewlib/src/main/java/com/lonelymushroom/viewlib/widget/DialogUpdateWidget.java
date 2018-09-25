package com.lonelymushroom.viewlib.widget;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import android.widget.TextView;
import com.daimajia.numberprogressbar.NumberProgressBar;
import com.lonelymushroom.viewlib.R;
import com.lonelymushroom.viewlib.utils.BaseNiceDialog;

/**
 * @author 被咯苏州
 */

public class DialogUpdateWidget extends BaseNiceDialog {

    public static DialogUpdateWidget init() {
        return new DialogUpdateWidget();
    }

    private NumberProgressBar mProgressBar;
    private TextView tvTitle;

    private String title;
    private int mInitProgress = 0;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mProgressBar = (NumberProgressBar) view.findViewById(R.id.progress_bar);
        tvTitle = (TextView) view.findViewById(R.id.tv_1);
        mProgressBar.setProgress(mInitProgress);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (title != null) {
            tvTitle.setText(title);
        }
    }

    @Override
    public void onDestroyView() {
        if (getDialog() != null && getRetainInstance())
            getDialog().setDismissMessage(null);
        super.onDestroyView();
    }

    /**
     * 设置进度条的数值
     * 如果需要在show的时候立马调用,则需要用new Handler().post(setProgress(?)),或者调用initProgress(?)方法
     *
     * @param progress 1~100
     */
    public void setProgress(int progress) {
        if (mProgressBar != null) {
            mProgressBar.setProgress(progress);
        }
    }

    public DialogUpdateWidget setTitle(String title) {
        this.title = title;
        return this;
    }

    @Override
    public int intLayoutId() {
        return R.layout.dialog_update;
    }

    public DialogUpdateWidget setmInitProgress(int mInitProgress) {
        this.mInitProgress = mInitProgress;
        return this;
    }
}
