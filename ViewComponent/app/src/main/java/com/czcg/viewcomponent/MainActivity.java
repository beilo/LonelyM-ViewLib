package com.czcg.viewcomponent;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.czcg.viewlib.beans.AlertBean;
import com.czcg.viewlib.beans.BensEntity;
import com.czcg.viewlib.beans.SheetBean;
import com.czcg.viewlib.utils.BaseNiceDialog;
import com.czcg.viewlib.utils.ClickListener;
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
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_1:
                final DialogUpdateWidget dialogUpdateWidget = (DialogUpdateWidget) DialogUpdateWidget.init()
                        .setmInitProgress(30)
                        .setOutCancel(true)
                        .setWidth(250)
                        .setHeight(100);
                dialogUpdateWidget.show(getSupportFragmentManager());
                new Handler().post(new Runnable() {
                    @Override
                    public void run() {
                        dialogUpdateWidget.setProgress(89);
                    }
                });
                break;
            case R.id.bt_2:
                ToastWidget.init().createToast(context, "给我提示", ToastWidget.STATUS_SUCCESS).show();
                break;
            case R.id.bt_3:

                DialogWidget dialogWidget = (DialogWidget) DialogWidget.init()
                        .setOutCancel(true)
                        .setHeight(60)
                        .setWidth(60);
                dialogWidget.show(getSupportFragmentManager());

                break;
            case R.id.bt_4:
                SheetBean bean = new SheetBean();
                bean.setTitle("测试");
                List<BensEntity> list = new ArrayList<>();

                int identifier1 = getResources().getIdentifier("i_driver", "drawable", "com.czcg.viewcomponent");
                int identifier2 = getResources().getIdentifier("i_ship", "drawable", "com.czcg.viewcomponent");
                int identifier3 = getResources().getIdentifier("i_track", "drawable", "com.czcg.gwt");
                list.add(new BensEntity(identifier1,"1"));
                list.add(new BensEntity(identifier2,"2"));
                list.add(new BensEntity(identifier3,"3"));
                bean.setBtns(list);

                SheetViewWidget sheetView = SheetViewWidget.newInstance(bean);
                sheetView.setShootingListener(new SheetViewWidget.ShootingListener() {
                    @Override
                    public void callbackSheetView(String title) {
                        ToastWidget.init().createToast(context, title, ToastWidget.STATUS_SUCCESS).show();
                    }
                });
                sheetView.show(getSupportFragmentManager());
                break;
            case R.id.bt_5:
                final AlertBean alertBean = new AlertBean();
                alertBean.setTitle("测试");
                alertBean.setMessage("1.adfafasfadf122222222222222222222222222222222222222222222222222222222222222222sadf\n2.3245678909876tryh122222222222222222222222222222222222222ksdfjhgsadf\n3.ksafdsafuoasjfaf\n4.\n5.\n6.");
                alertBean.setJudgment(true);
                alertBean.setConfirm("我是确定");
                alertBean.setCancel("我是取消");
                AlertWidget.init()
                        .setAlertBean(alertBean)
                        .setClickListener(new ClickListener() {
                            @Override
                            public void listener(BaseNiceDialog dialog, boolean flag) {
                                if (flag) {
                                    Toast.makeText(context, "确认按钮", Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();
                                } else {
                                    dialog.dismiss();
                                }
                            }
                        })
                        .setOutCancel(false)
                        .setWidth(300)
                        .show(getSupportFragmentManager());
                break;
            case R.id.bt_6:

                break;
            case R.id.bt_7:
                startActivity(new Intent(this, MainActivity.class));
                break;
            default:
                break;
        }
    }

}