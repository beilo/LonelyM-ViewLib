package com.lonelymushroom.viewlib.widget;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.ContextThemeWrapper;

import com.lonelymushroom.viewlib.R;
import com.lonelymushroom.viewlib.beans.AlertBean;

/**
 * alert 弹出框
 *
 * @version 0.1.3 修复了builder在内存重启下的泄漏问题(在onDestroy调用dismiss)
 * Created by leipe on 2017/11/24.
 */

public class AlertWidget {

    public static volatile AlertWidget widget;

    private AlertDialog mDialog;
    private AlertManagerListener alertManagerListener;

    public static AlertWidget init() {
        if (widget == null) {
            widget = new AlertWidget();
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

        String confirm = (alertBean.getConfirm() == null || "".equals(alertBean.getConfirm()))
                ? "确定" : alertBean.getConfirm();

        String cancel = (alertBean.getCancel() == null || "".equals(alertBean.getCancel()))
                ? "取消" : alertBean.getCancel();

        if (alertBean.isJudgment()) {
            builder.setNegativeButton(cancel, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    callback(false);
                }
            });
        }
        builder.setPositiveButton(confirm, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                callback(true);
            }
        });
        mDialog = builder.show();
    }

    public void dismiss() {
        // https://stackoverflow.com/questions/2745061/java-lang-illegalargumentexception-view-not-attached-to-window-manager
        try {
            if ((mDialog != null) && mDialog.isShowing()) {
                mDialog.dismiss();
            }
        } catch (final IllegalArgumentException e) {
            // Handle or log or ignore
        } catch (final Exception e) {
            // Handle or log or ignore
        } finally {
            this.mDialog = null;
            alertManagerListener = null;
        }
    }

    public interface AlertManagerListener {
        void listener(boolean flag);
    }

    private void callback(boolean flag) {
        if (alertManagerListener != null)
            alertManagerListener.listener(flag);
    }
}
