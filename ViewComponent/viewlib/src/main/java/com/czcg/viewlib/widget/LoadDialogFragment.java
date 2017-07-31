package com.czcg.viewlib.widget;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.res.ResourcesCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.czcg.viewlib.R;
import com.czcg.viewlib.ui.LoadingView;
import com.czcg.viewlib.utils.DensityConversionUtil;

/**
 * Created by leipe on 2017/7/10.
 */

public class LoadDialogFragment extends DialogFragment {

    private FragmentActivity _mActivity;
    private LoadingView loadingView;

    public static LoadDialogFragment newInstance() {
        LoadDialogFragment fragment = new LoadDialogFragment();
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        _mActivity = (FragmentActivity) context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.view_load, container, false);
        loadingView = (LoadingView) inflate.findViewById(R.id.loadView);
        loadingView.setIndicatorColor(ResourcesCompat.getColor(_mActivity.getResources(), R.color.orange, null));
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        return inflate;
    }

    @Override
    public void onStart() {
        super.onStart();
        // 这里设置大小,布局大小没有效果
        getDialog().getWindow().setLayout(DensityConversionUtil.dpToPx(_mActivity, 180),
                DensityConversionUtil.dpToPx(_mActivity, 120));
        getDialog().getWindow().setBackgroundDrawable(ResourcesCompat.getDrawable(_mActivity.getResources()
                ,R.drawable.shape_load_view,null));
        // //对话框背景色 原有边框会自动消失
        getDialog().getWindow().setDimAmount(0);//背景黑暗度
        setCancelable(false);
    }

    public void dismissDialog() {
        if (_mActivity != null) {
            dismiss();
        }
    }
}
