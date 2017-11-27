//package com.czcg.viewlib.widget;
//
//import android.content.Context;
//import android.widget.Toast;
//
//import es.dmoral.toasty.Toasty;
//
///**
// * 三种颜色提示 Toast
// * @author 被咯苏州
// */
//public class ToastWidget {
//    public static final String STATUS_SUCCESS = "success";
//    public static final String STATUS_WARM = "warm";
//    public static final String STATUS_ERROR = "error";
//
//    private static volatile ToastWidget widget;
//
//    public static ToastWidget init(){
//        if (widget == null){
//            synchronized (ToastWidget.class){
//                if (widget == null){
//                    widget = new ToastWidget();
//                }
//            }
//        }
//        return widget;
//    }
//
//    public Toast createToast(Context context, String msg, String type) {
//        switch (type) {
//            case STATUS_SUCCESS:
//                return Toasty.success(context, msg, Toast.LENGTH_SHORT);
//            case STATUS_WARM:
//                return Toasty.warning(context, msg, Toast.LENGTH_SHORT);
//            case STATUS_ERROR:
//                return Toasty.error(context, msg, Toast.LENGTH_SHORT);
//            default:
//                return Toasty.normal(context, msg, Toast.LENGTH_SHORT);
//        }
//    }
//}
