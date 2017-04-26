package com.czcg.viewlib.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.czcg.viewlib.R;
import com.czcg.viewlib.beans.BensEntity;
import com.czcg.viewlib.widget.SheetViewWidget;

import java.util.List;

/**
 * Created by Administrator on 2016/4/19.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    public Context context;
    public List<BensEntity> stringList;
    public ItemClickListener itemClickListener;


    public RecyclerViewAdapter(Context context, List<BensEntity> stringList, ItemClickListener itemClickListener) {
        this.context = context;
        this.stringList = stringList;
        this.itemClickListener = itemClickListener;
    }

    public RecyclerViewAdapter() {

    }

    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.dialogfragment_list_item, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapter.ViewHolder holder, final int position) {
        final BensEntity btnsEntity = stringList.get(position);
        holder.mText.setText(btnsEntity.getTitle());
        holder.mText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ((MainActivity)context).alertSheet(btnsEntity.getTitle());
                SheetViewWidget.getInstance().getTitle(btnsEntity.getTitle());
                itemClickListener.dismiss();
            }
        });
    }


    @Override
    public int getItemCount() {
        return stringList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public View view;
        public TextView mText;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            mText = (TextView) view.findViewById(R.id.text);
            mText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });
        }
    }

    public interface ItemClickListener{
        public void dismiss();
    }
}
