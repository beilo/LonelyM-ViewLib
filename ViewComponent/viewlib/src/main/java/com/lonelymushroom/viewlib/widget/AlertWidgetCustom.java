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

/**
 * 对话框
 * 显示确认取消的对话框
 * @version 0.1.3 修复了内存重启后闪退的问题
 *
 * @author leipeng
 */

public class AlertWidgetCustom extends BaseNiceDialog {

    private static String ALERT_BEAN = "alertBean";

    private AlertBean mAlertBean;

    private AlertListener listener;


    public static AlertWidgetCustom init() {
        return new AlertWidgetCustom();
    }

    @Override
    public int intLayoutId() {
        return R.layout.dialog_alert;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            mAlertBean = savedInstanceState.getParcelable(ALERT_BEAN);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(ALERT_BEAN, mAlertBean);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView tvTitle = (TextView) view.findViewById(R.id.tv_title);
        TextView tvContent = (TextView) view.findViewById(R.id.tv_content);

        Button btnSuccess = (Button) view.findViewById(R.id.btn_success);
        Button btnCancel = (Button) view.findViewById(R.id.btn_cancel);

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

        btnSuccess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                invokeListener("成功");
                dismiss();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                invokeListener("失败");
                dismiss();
            }
        });
    }

    @Override
    public void onDestroyView() {
        listener = null;
        super.onDestroyView();
    }

    public AlertWidgetCustom setAlertBean(AlertBean mAlertBean) {
        this.mAlertBean = mAlertBean;
        return this;
    }

    public AlertWidgetCustom setListener(AlertListener listener) {
        this.listener = listener;
        return this;
    }

    private void invokeListener(String string) {
        if (listener != null) {
            listener.callback(string);
        }
    }

    public interface AlertListener {
        void callback(String string);
    }

}
