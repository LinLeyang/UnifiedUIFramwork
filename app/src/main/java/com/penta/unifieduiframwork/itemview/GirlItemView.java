package com.penta.unifieduiframwork.itemview;

import android.net.Uri;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.penta.unifieduiframwork.framwork.BaseItemView;
import com.penta.unifieduiframwork.model.Girl;
import com.penta.unifieduiframwork.R;

/**
 * Created by linyueyang on 2018/7/3.
 */

public class GirlItemView extends BaseItemView<Girl, GirlItemView.GirlViewHolder> {

    @Override
    public String getItemType() {
        return "girl";
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_girl;
    }

    @Override
    public GirlViewHolder onCreateViewHolder(View convertView) {
        return new GirlViewHolder(convertView);
    }

    @Override
    public void onBindDataToView(GirlViewHolder viewHolder, int position) {
        Uri uri = Uri.parse(getData().getUrl());
        viewHolder.iv1.setImageURI(uri);
        viewHolder.tv1.setText(getData().getWho());
        viewHolder.tv2.setText(getData().getDesc());
    }

    public class GirlViewHolder extends BaseItemView.BaseViewHolder {

        SimpleDraweeView iv1;
        TextView tv1;
        TextView tv2;

        GirlViewHolder(View view) {
            super(view);
            iv1 = view.findViewById(R.id.iv1);
            tv1 = view.findViewById(R.id.tv1);
            tv2 = view.findViewById(R.id.tv2);
        }

    }

}
