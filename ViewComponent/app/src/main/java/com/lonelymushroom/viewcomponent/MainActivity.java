package com.lonelymushroom.viewcomponent;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Toast;

import com.lonelymushroom.viewlib.beans.AlertBean;
import com.lonelymushroom.viewlib.beans.BensEntity;
import com.lonelymushroom.viewlib.beans.SheetBean;
import com.lonelymushroom.viewlib.widget.AlertWidget;
import com.lonelymushroom.viewlib.widget.AlertWidgetCustom;
import com.lonelymushroom.viewlib.widget.DialogUpdateWidget;
import com.lonelymushroom.viewlib.widget.DialogWidget;
import com.lonelymushroom.viewlib.widget.SheetViewWidget;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends FragmentActivity implements View.OnClickListener {
    Context context;

    Timer timer = new Timer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_main);

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Random random = new Random();//默认构造方法
                final int i2 = random.nextInt(100);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (dialogUpdateWidget != null) {
                            dialogUpdateWidget.setProgress(i2);
                        }
                    }
                });
            }
        }, 0, 3000);
    }

    private DialogUpdateWidget dialogUpdateWidget;

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
        if (fragment instanceof DialogUpdateWidget) {
            dialogUpdateWidget = (DialogUpdateWidget) fragment;
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_1:
                dialogUpdateWidget = (DialogUpdateWidget) DialogUpdateWidget.init()
                        .setTitle("11111")
                        .setmInitProgress(30)
                        .setOutCancel(true)
                        .setWidth(250)
                        .setHeight(100);
                dialogUpdateWidget.show(getSupportFragmentManager(), "111");
                break;
            case R.id.bt_2:
                // ToastWidget.init().createToast(context, "给我提示", ToastWidget.STATUS_SUCCESS).show();
                Toast.makeText(context, "给我提示", Toast.LENGTH_SHORT).show();
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
                ArrayList<BensEntity> list = new ArrayList<>();

                BensEntity bensEntity1 = new BensEntity();
                bensEntity1.setIcon("i_driver");
                bensEntity1.setTitle("1");
                list.add(bensEntity1);

                BensEntity bensEntity2 = new BensEntity();
                bensEntity2.setIcon("i_ship");
                bensEntity2.setTitle("2");
                list.add(bensEntity2);

                BensEntity bensEntity3 = new BensEntity();
                bensEntity3.setIcon("i_track");
                bensEntity3.setTitle("3");
                list.add(bensEntity3);
                bean.setBtns(list);

                SheetViewWidget sheetView = SheetViewWidget.newInstance(bean, "drawable", "com.lonelymushroom.viewcomponent");
                sheetView.setSheetWidgetListener(new SheetViewWidget.SheetWidgetListener() {
                    @Override
                    public void listener(String title) {
                        Toast.makeText(context, title, Toast.LENGTH_SHORT).show();
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
                        .createAlert(this, alertBean, new AlertWidget.AlertManagerListener() {
                            @Override
                            public void listener(boolean flag) {
                                if (flag) {
                                    Toast.makeText(context, "确认按钮", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(context, "取消按钮", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                AlertWidget.init()
                        .createAlert(this, alertBean, new AlertWidget.AlertManagerListener() {
                            @Override
                            public void listener(boolean flag) {
                                if (flag) {
                                    Toast.makeText(context, "11", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(context, "22", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                break;
            case R.id.bt_6:
                final AlertBean alertBean1 = new AlertBean();
                alertBean1.setTitle("测试");
                alertBean1.setMessage("1.adfafasfadf122222222222222222222222222222222222222222222222222222222222222222sadf\n2.3245678909876tryh122222222222222222222222222222222222222ksdfjhgsadf\n3.ksafdsafuoasjfaf\n4.\n5.\n6.");
                alertBean1.setJudgment(true);
                alertBean1.setConfirm("我是确定");
                alertBean1.setCancel("我是取消");
                AlertWidgetCustom
                        .init()
                        .setAlertBean(alertBean1)
                        .setListener(new AlertWidgetCustom.AlertListener() {
                            @Override
                            public void callback(String string) {
                                Toast.makeText(context, string, Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show(getSupportFragmentManager(), "111");
                break;
            case R.id.bt_7:


                break;
            default:
                break;
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        timer.cancel();

        AlertWidget.init()
                .dismiss();
    }
}