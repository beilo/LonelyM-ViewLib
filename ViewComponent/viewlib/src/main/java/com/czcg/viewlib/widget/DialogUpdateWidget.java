package com.czcg.viewlib.widget;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.RelativeLayout;

import com.czcg.viewlib.R;
import com.czcg.viewlib.utils.DensityConversionUtil;
import com.daimajia.numberprogressbar.NumberProgressBar;

/**
 * Created by 被咯苏州 on 2017/3/29.
 */

public class DialogUpdateWidget {

    private static DialogUpdateWidget dialogUpdateWidget = new DialogUpdateWidget();
    private DialogUpdateWidget() {
    }
    public static DialogUpdateWidget getInstance(){
        return dialogUpdateWidget;
    }

    private Dialog dialog;
    NumberProgressBar progressBar;

    public Dialog createDialog(Context context,boolean isCancel) {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_update, null);// 得到加载view
        progressBar = (NumberProgressBar) view.findViewById(R.id.progress_bar);
        dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);  //http://blog.csdn.net/u011747761/article/details/47419419
        dialog.setCancelable(isCancel);
        dialog.setCanceledOnTouchOutside(isCancel);
        dialog.show();
        dialog.addContentView(view, new RelativeLayout.LayoutParams(
                DensityConversionUtil.dpToPx(context, 250),
                DensityConversionUtil.dpToPx(context, 100)));
        return dialog;
    }


    public void setProgress(int position) {
        progressBar.setProgress(position);
    }
}
