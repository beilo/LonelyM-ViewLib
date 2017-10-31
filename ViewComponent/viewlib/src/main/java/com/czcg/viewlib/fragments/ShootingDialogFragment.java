package com.czcg.viewlib.fragments;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.czcg.viewlib.R;
import com.czcg.viewlib.adapters.RecyclerViewAdapter;
import com.czcg.viewlib.beans.SheetBean;


/**
 *
 * @author Administrator
 * @date 2015/10/23
 */
public class ShootingDialogFragment extends DialogFragment {

    public static Fragment newInstance(SheetBean sheetBean) {
        ShootingDialogFragment fragment = new ShootingDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("sheetList",sheetBean);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onStart() {
        super.onStart();
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        Window win = getDialog().getWindow();
        win.setLayout(dm.widthPixels, getActivity().getWindow().getAttributes().height);

        win.setBackgroundDrawable(new ColorDrawable(00000000));
        win.getAttributes().gravity = Gravity.BOTTOM;
    }

    RecyclerView recyView;
    TextView text;
    RecyclerViewAdapter recyclerViewAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialogfragment_shooting, container);
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        Bundle arguments = getArguments();
        SheetBean sheetBean = (SheetBean) arguments.getSerializable("sheetList");
        recyView = (RecyclerView) view.findViewById(R.id.recyView);

        text = (TextView) view.findViewById(R.id.tv_cancel);
        recyclerViewAdapter = new RecyclerViewAdapter(getActivity(), sheetBean.getBtns(), new RecyclerViewAdapter.ItemClickListener() {
            @Override
            public void dismiss() {
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
        return view;
    }

}
