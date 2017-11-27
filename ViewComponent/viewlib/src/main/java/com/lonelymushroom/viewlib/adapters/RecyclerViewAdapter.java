package com.lonelymushroom.viewlib.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lonelymushroom.viewlib.R;
import com.lonelymushroom.viewlib.beans.BensEntity;

import java.util.List;

/**
 * Created by Administrator on 2016/4/19.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private List<BensEntity> stringList;
    private Context mContext;
    private String mDefType, mDefPackage;

    private ItemClickListener itemClickListener;


    public RecyclerViewAdapter(List<BensEntity> stringList, Context context, String defType, String defPackage) {
        this.stringList = stringList;
        this.mContext = context;
        this.mDefType = defType;
        this.mDefPackage = defPackage;
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
        if (btnsEntity.getIcon() == null || "".equals(btnsEntity.getIcon())) {
            holder.mIcon.setVisibility(View.GONE);
        } else {
            int identifier = mContext.getResources().getIdentifier(btnsEntity.getIcon(), mDefType, mDefPackage);
            holder.mIcon.setImageResource(identifier);
        }
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener.itemClick(v, btnsEntity.getTitle());
            }
        });
    }


    @Override
    public int getItemCount() {
        return stringList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View view;
        TextView mText;
        ImageView mIcon;

        ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            mText = (TextView) view.findViewById(R.id.viewlib_text);
            mIcon = (ImageView) view.findViewById(R.id.viewlib_img_icon);
        }
    }

    public interface ItemClickListener {
        void itemClick(View view, String title);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }
}
