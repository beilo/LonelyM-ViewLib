package com.lonelymushroom.viewlib.widget;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.lonelymushroom.viewlib.R;
import com.lonelymushroom.viewlib.utils.BaseNiceDialog;
import com.daimajia.numberprogressbar.NumberProgressBar;

/**
 * @author 被咯苏州
 */

public class DialogUpdateWidget extends BaseNiceDialog {

    public static DialogUpdateWidget init() {
        return new DialogUpdateWidget();
    }

    private NumberProgressBar mProgressBar;
    private int mInitProgress = 0;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mProgressBar = (NumberProgressBar) view.findViewById(R.id.progress_bar);
        mProgressBar.setProgress(mInitProgress);
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

    @Override
    public int intLayoutId() {
        return R.layout.dialog_update;
    }

    public DialogUpdateWidget setmInitProgress(int mInitProgress) {
        this.mInitProgress = mInitProgress;
        return this;
    }
}
