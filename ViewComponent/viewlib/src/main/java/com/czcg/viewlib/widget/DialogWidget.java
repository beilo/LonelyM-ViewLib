package com.czcg.viewlib.widget;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;


/**
 * Created by lee on 2015/12/14.
 */
public class DialogWidget {
    private static DialogWidget widget = new DialogWidget();

    private DialogWidget() {
    }

    public static DialogWidget getInstance() {
        return widget;
    }

    public void show(Context context) {

        if (context instanceof FragmentActivity){
            FragmentActivity activity = (FragmentActivity) context;
            FragmentTransaction ft = activity.getSupportFragmentManager().beginTransaction();
            Fragment prev = activity.getSupportFragmentManager().findFragmentByTag("dialog");
            if (prev != null) {
                ft.remove(prev);
            }
            ft.addToBackStack(null);
            LoadDialogFragment.newInstance().show(ft, "dialog");
        }
    }
}
