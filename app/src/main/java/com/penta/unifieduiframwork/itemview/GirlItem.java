package com.penta.unifieduiframwork.itemview;

import android.net.Uri;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.penta.library.UUBaseItem;
import com.penta.library.UUBaseViewHolder;
import com.penta.library.UUViewAdapter;
import com.penta.unifieduiframwork.R;
import com.penta.unifieduiframwork.model.Girl;

/**
 * Created by linyueyang on 2018/7/3.
 */

public class GirlItem extends UUBaseItem<Girl, GirlViewHolder> {

    @Override
    public String getItemType() {
        return "girl";
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_girl;
    }

    @Override
    public void onBindDataToView(GirlViewHolder viewHolder, int position) {
        Uri uri = Uri.parse(getData().getUrl());
        viewHolder.iv1.setImageURI(uri);
        viewHolder.tv1.setText(getData().getWho());
        viewHolder.tv2.setText(getData().getDesc());
    }
}
