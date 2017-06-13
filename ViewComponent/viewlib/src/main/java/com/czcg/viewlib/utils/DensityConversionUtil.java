package com.czcg.viewlib.utils;

import android.app.Activity;
import android.content.Context;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by 被咯苏州 on 2016/8/16.
 */
public class DensityConversionUtil {
    public static int dpToPx(Context context, int dp) {
        //先获得设备密度
        float density = context.getResources().getDisplayMetrics().density;
        // dp = px / 设备密度
        //所以进行公式计算即可
        int px = (int) (dp * density + 0.5f);     //进行四舍五入
        return px;
    }

    public static int pxTopPd(Context context, int px) {
        float density = context.getResources().getDisplayMetrics().density;

        int pd = (int) (px / density + 0.5f);  //进行四舍五入防止失真

        return pd;
    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm =
                (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(activity.getWindow().getDecorView().getWindowToken(), 0);
    }
}
