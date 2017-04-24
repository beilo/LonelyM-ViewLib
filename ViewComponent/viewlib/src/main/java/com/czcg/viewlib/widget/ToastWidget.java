package com.czcg.viewlib.widget;

import android.content.Context;
import android.widget.Toast;

import es.dmoral.toasty.Toasty;

/**
 * Created by lee on 2016/4/27
 */
public class ToastWidget {
    public static final String STATUS_SUCCESS = "success";
    public static final String STATUS_WARM = "warm";
    public static final String STATUS_ERROR = "error";

    private static ToastWidget widget = new ToastWidget();
    private ToastWidget() {
    }
    public static ToastWidget getInstance(){
        return widget;
    }

    public Toast createToast(Context context, String msg, String type) {
        switch (type) {
            case STATUS_SUCCESS:
                return Toasty.success(context, msg, Toast.LENGTH_SHORT);
            case STATUS_WARM:
                return Toasty.warning(context, msg, Toast.LENGTH_SHORT);
            case STATUS_ERROR:
                return Toasty.error(context, msg, Toast.LENGTH_SHORT);
            default:
                return Toasty.normal(context, msg, Toast.LENGTH_SHORT);
        }
    }
}
