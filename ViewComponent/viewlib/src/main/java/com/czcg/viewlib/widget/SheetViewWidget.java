package com.czcg.viewlib.widget;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

import com.czcg.viewlib.beans.SheetBean;
import com.czcg.viewlib.fragments.ShootingDialogFragment;


/**
 * @author 被咯苏州
 * @date 2016/4/20
 */
public class SheetViewWidget {

    private static volatile SheetViewWidget sheetViewWidget;
    private SheetViewListener sheetListener;

    public static SheetViewWidget init() {
        if (sheetViewWidget == null) {
            synchronized (SheetViewListener.class) {
                if (sheetViewWidget == null) {
                    sheetViewWidget = new SheetViewWidget();
                }
            }
        }
        return sheetViewWidget;
    }

    public void createFragment(FragmentActivity activity, SheetBean sheetBean, SheetViewListener listener) {
        sheetListener = listener;
        ShootingDialogFragment dialogFragment = (ShootingDialogFragment) ShootingDialogFragment.newInstance(sheetBean);
        FragmentTransaction ft = activity.getSupportFragmentManager().beginTransaction();
        ft.add(dialogFragment, "shootingDialogFragment");
        ft.commitAllowingStateLoss();
    }

    public interface SheetViewListener {
        void listener(String title);
    }

    public void getTitle(String title) {
        sheetListener.listener(title);
    }
}
