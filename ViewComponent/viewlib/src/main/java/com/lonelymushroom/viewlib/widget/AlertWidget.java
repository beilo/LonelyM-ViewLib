package com.lonelymushroom.viewlib.widget;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.ContextThemeWrapper;

import com.lonelymushroom.viewlib.R;
import com.lonelymushroom.viewlib.beans.AlertBean;

/**
 * alert 弹出框
 * Created by leipe on 2017/11/24.
 */

public class AlertWidget {

    public static volatile AlertWidget widget;

    private AlertManagerListener alertManagerListener;

    public static AlertWidget init(){
        if (widget == null){
            synchronized (AlertWidget.class){
                if (widget == null){
                    widget = new AlertWidget();
                }
            }
        }
        return widget;
    }

    public void createAlert(Context context, AlertBean alertBean, AlertManagerListener listener) {
        alertManagerListener = listener;
        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(context, R.style.AlertDialogCustom))
                .setTitle(alertBean.getTitle())
                .setMessage(alertBean.getMessage())
                // .setView(inflate)
                // http://www.cnblogs.com/howlaa/p/4126536.html 使alertDialog.builder不会点击外面和按返回键消失
                .setCancelable(false);
        if (alertBean.isJudgment()) {
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
