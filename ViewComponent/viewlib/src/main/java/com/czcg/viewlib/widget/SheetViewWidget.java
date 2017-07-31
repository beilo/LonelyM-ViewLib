package com.czcg.viewlib.widget;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

import com.czcg.viewlib.beans.SheetBean;
import com.czcg.viewlib.fragments.ShootingDialogFragment;


/**
 * Created by Administrator on 2016/4/20.
 */
public class SheetViewWidget {

    public static SheetViewListener sheetListener;

    private static SheetViewWidget widget = new SheetViewWidget();
    private SheetViewWidget() {
    }
    public static SheetViewWidget getInstance(){
        return widget;
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
