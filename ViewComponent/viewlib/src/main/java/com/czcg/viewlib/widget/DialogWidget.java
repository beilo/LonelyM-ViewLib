package com.czcg.viewlib.widget;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.czcg.viewlib.R;
import com.czcg.viewlib.utils.BaseNiceDialog;
import com.czcg.viewlib.utils.ViewConvertListener;
import com.czcg.viewlib.utils.ViewHolder;


/**
 * 等候框
 *
 * @author leipeng
 */
public class DialogWidget extends BaseNiceDialog {


    public static DialogWidget init() {
        return new DialogWidget();
    }


    @Override
    public int intLayoutId() {
        return R.layout.new_dialog_load;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setConvertListener(new ViewConvertListener() {
            @Override
            public void convertView(ViewHolder holder, BaseNiceDialog dialog) {
                ImageView imageView = holder.getView(R.id.img);
                // 加载动画
                Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(
                        _mActivity, R.anim.loading_animation);
                // 使用ImageView显示动画
                imageView.startAnimation(hyperspaceJumpAnimation);
            }
        });
    }
}
