package com.czcg.viewlib.widget;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.view.ContextThemeWrapper;

import com.czcg.viewlib.R;
import com.czcg.viewlib.beans.AlertBean;


/**
 * Created by 被咯苏州 on 2016/8/19.
 */
public class AlertWidget {
    private static AlertWidget widget = new AlertWidget();
    private AlertWidget() {
    }
    public static AlertWidget getInstance(){
        return widget;
    }
    public AlertManagerListener alertManagerListener;
    public void createFragment(Context context, AlertBean alertBean, AlertManagerListener listener) {
        alertManagerListener = listener;
        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(context, R.style.AlertDialogCustom))
                .setTitle(alertBean.getTitle())
                .setMessage(alertBean.getMessage())
                // .setView(inflate)
                // http://www.cnblogs.com/howlaa/p/4126536.html 使alertDialog.builder不会点击外面和按返回键消失
                .setCancelable(false);
        if (alertBean.isIsJudgment()) {
            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    callback(false);
                }
            });
        }
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                callback(true);
            }
        });
        builder.show();
    }

    public interface AlertManagerListener {
        void listener(boolean flag);
    }

    public void callback(boolean flag) {
        alertManagerListener.listener(flag);
    }
}
