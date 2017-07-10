package com.czcg.viewcomponent;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.czcg.viewlib.beans.AlertBean;
import com.czcg.viewlib.beans.BensEntity;
import com.czcg.viewlib.beans.SheetBean;
import com.czcg.viewlib.widget.AlertWidget;
import com.czcg.viewlib.widget.DialogUpdateWidget;
import com.czcg.viewlib.widget.DialogWidget;
import com.czcg.viewlib.widget.SheetViewWidget;
import com.czcg.viewlib.widget.ToastWidget;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_main);


        DialogWidget.getInstance().show(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_1:
                DialogUpdateWidget.getInstance().createDialog(context, true).show();
                break;
            case R.id.bt_2:
                ToastWidget.getInstance().createToast(context, "给我提示", ToastWidget.STATUS_SUCCESS).show();
                break;
            case R.id.bt_3:
                DialogWidget.getInstance().show(this);
                break;
            case R.id.bt_4:
                SheetBean bean = new SheetBean();
                bean.setTitle("测试");
                List<BensEntity> list = new ArrayList<>();
                list.add(new BensEntity("1"));
                list.add(new BensEntity("2"));
                list.add(new BensEntity("3"));
                bean.setBtns(list);
                SheetViewWidget.getInstance().createFragment(MainActivity.this, bean, new SheetViewWidget.SheetViewListener() {
                    @Override
                    public void listener(String title) {
                        ToastWidget.getInstance().createToast(context, title, ToastWidget.STATUS_SUCCESS).show();
                    }
                });
                break;
            case R.id.bt_5:
                AlertBean alertBean = new AlertBean();
                alertBean.setTitle("测试");
                alertBean.setMessage("message");
                alertBean.setIsJudgment(true);
                alertBean.setConfirm("我是确定");
                alertBean.setCancel("我是取消");
                AlertWidget.getInstance().createFragment(context, alertBean, new AlertWidget.AlertManagerListener() {
                    @Override
                    public void listener(boolean flag) {
                        ToastWidget.getInstance().createToast(context, flag + "", ToastWidget.STATUS_SUCCESS).show();
                    }
                });
                break;
            case R.id.bt_6:

                break;
            case R.id.bt_7:

                break;
            default:
                break;
        }
    }
}
