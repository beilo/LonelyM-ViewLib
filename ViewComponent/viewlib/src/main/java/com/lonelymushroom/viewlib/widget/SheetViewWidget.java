package com.lonelymushroom.viewlib.widget;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.lonelymushroom.viewlib.R;
import com.lonelymushroom.viewlib.adapters.RecyclerViewAdapter;
import com.lonelymushroom.viewlib.beans.SheetBean;
import com.lonelymushroom.viewlib.utils.BaseNiceDialog;

import java.io.Serializable;


/**
 * @author Administrator
 */
public class SheetViewWidget extends BaseNiceDialog {

    private static final String SHOOT_LISTENER = "shootingListener";

    RecyclerView recyView;
    TextView text;
    RecyclerViewAdapter recyclerViewAdapter;

    private ShootingListener shootingListener;

    public static SheetViewWidget newInstance(SheetBean sheetBean, String defType, String defPackage) {

        SheetViewWidget fragment = new SheetViewWidget();
        Bundle bundle = new Bundle();
        bundle.putParcelable("sheetList", sheetBean);
        bundle.putString("defType", defType);
        bundle.putString("defPackage", defPackage);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setShowBottom(true);
        setAnimStyle(0);
        if (savedInstanceState != null) {
            shootingListener = (ShootingListener) savedInstanceState.getSerializable(SHOOT_LISTENER);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle arguments = getArguments();
        SheetBean sheetBean = arguments.getParcelable("sheetList");
        String defType = arguments.getString("defType");
        String defPackage = arguments.getString("defPackage");
        if (sheetBean == null){
            return;
        }

        recyView = (RecyclerView) view.findViewById(R.id.viewlib_recyView);
        text = (TextView) view.findViewById(R.id.viewlib_tv_cancel);

        recyclerViewAdapter = new RecyclerViewAdapter(sheetBean.getBtns(), _mActivity, defType, defPackage);
        recyclerViewAdapter.setItemClickListener(new RecyclerViewAdapter.ItemClickListener() {
            @Override
            public void itemClick(View view, String title) {
                shootingListener.callbackSheetView(title);
                getDialog().dismiss();
            }
        });

        recyView.setLayoutManager(new LinearLayoutManager(recyView.getContext()));
        recyView.setAdapter(recyclerViewAdapter);

        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(SHOOT_LISTENER, shootingListener);
    }

    @Override
    public void onDestroyView() {
        recyclerViewAdapter.setItemClickListener(null);
        text.setOnClickListener(null);
        shootingListener = null;
        super.onDestroyView();
    }

    public void setShootingListener(ShootingListener shootingListener) {
        this.shootingListener = shootingListener;
    }

    public interface ShootingListener extends Serializable {
        void callbackSheetView(String title);
    }

    @Override
    public int intLayoutId() {
        return R.layout.dialogfragment_shooting;
    }
}
