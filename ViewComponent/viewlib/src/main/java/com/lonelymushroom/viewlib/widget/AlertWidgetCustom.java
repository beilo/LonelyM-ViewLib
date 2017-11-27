package com.lonelymushroom.viewlib.widget;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.res.ResourcesCompat;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lonelymushroom.viewlib.R;
import com.lonelymushroom.viewlib.beans.AlertBean;
import com.lonelymushroom.viewlib.utils.BaseNiceDialog;
import com.lonelymushroom.viewlib.utils.ClickListener;
import com.lonelymushroom.viewlib.utils.ViewConvertListener;
import com.lonelymushroom.viewlib.utils.ViewHolder;

/**
 * 对话框
 * 显示确认取消的对话框
 *
 * @author leipeng
 */

public class AlertWidgetCustom extends BaseNiceDialog {

    private static String ALERT_BEAN = "alertBean";
    private static String CLICK_LISTENER = "click_listener";

    private AlertBean mAlertBean;

    private TextView tvTitle, tvContent;
    private Button btnSuccess, btnCancel;


    public static AlertWidgetCustom init() {
        return new AlertWidgetCustom();
    }

    private ClickListener clickListener;

    @Override
    public int intLayoutId() {
        return R.layout.dialog_alert;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setConvertListener(new ViewConvertListener() {
            @Override
            public void convertView(ViewHolder holder, final BaseNiceDialog dialog) {
                holder.setOnClickListener(R.id.btn_cancel, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        clickListener.listener(dialog, false);
                    }
                });
                holder.setOnClickListener(R.id.btn_success, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        clickListener.listener(dialog, true);
                    }
                });
            }
        });
        if (savedInstanceState != null) {
            mAlertBean = savedInstanceState.getParcelable(ALERT_BEAN);
            clickListener = savedInstanceState.getParcelable(CLICK_LISTENER);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvTitle = (TextView) view.findViewById(R.id.tv_title);
        tvContent = (TextView) view.findViewById(R.id.tv_content);

        btnSuccess = (Button) view.findViewById(R.id.btn_success);
        btnCancel = (Button) view.findViewById(R.id.btn_cancel);

        if (mAlertBean == null) {
            return;
        }

        if (mAlertBean.getTitle() == null || "".equals(mAlertBean.getTitle())) {
            tvTitle.setVisibility(View.GONE);
            tvContent.setGravity(Gravity.CENTER);
        } else {
            tvTitle.setText(mAlertBean.getTitle());
        }

        tvContent.setText(mAlertBean.getMessage());

        if (mAlertBean.getConfirm() == null || "".equals(mAlertBean.getConfirm())) {
            btnSuccess.setText("确认");
        } else {
            btnSuccess.setText(mAlertBean.getConfirm());
        }

        if (mAlertBean.getCancel() == null || "".equals(mAlertBean.getCancel())) {
            btnCancel.setText("取消");
        } else {
            btnCancel.setText(mAlertBean.getCancel());
        }

        if (!mAlertBean.isJudgment()) {
            btnSuccess.setBackground(ResourcesCompat.getDrawable(_mActivity.getResources(), R.drawable.layer_alert_center, null));
            btnCancel.setVisibility(View.GONE);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(ALERT_BEAN, mAlertBean);
        outState.putParcelable(CLICK_LISTENER, clickListener);
    }

    public AlertWidgetCustom setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
        return this;
    }

    public AlertWidgetCustom setAlertBean(AlertBean mAlertBean) {
        this.mAlertBean = mAlertBean;
        return this;
    }

    @Override
    public void onDestroyView() {
        this.clickListener = null;
        super.onDestroyView();
    }

}
