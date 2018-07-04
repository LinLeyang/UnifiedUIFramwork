package com.penta.unifieduiframwork.itemview;

import android.net.Uri;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.penta.unifieduiframwork.framwork.BaseItemView;
import com.penta.unifieduiframwork.framwork.Event;
import com.penta.unifieduiframwork.model.Boy;
import com.penta.unifieduiframwork.R;

/**
 * Created by linyueyang on 2018/7/4.
 */

public class BoyItemView extends BaseItemView<Boy, BoyItemView.BoyViewHolder> {

    @Override
    public String getItemType() {
        return "boy";
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_boy;
    }

    @Override
    public BoyViewHolder onCreateViewHolder(View convertView) {
        return new BoyViewHolder(convertView);
    }

    @Override
    public void onBindDataToView(BoyViewHolder viewHolder, final int position) {
        Uri uri = Uri.parse(getData().getUrl());
        viewHolder.iv1.setImageURI(uri);
        viewHolder.tv1.setText(getData().getWho());
        viewHolder.tv2.setText(getData().getDesc());
        viewHolder.iv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Event event = new Event() {
                    @Override
                    public String getEventId() {
                        return "boyPic";
                    }
                };
                event.setPosition(position);
                event.setData(getData());
                getListener().onEvent(event);
            }
        });
    }

    public class BoyViewHolder extends BaseItemView.BaseViewHolder {

        SimpleDraweeView iv1;
        TextView tv1;
        TextView tv2;

        BoyViewHolder(View view) {
            super(view);
            iv1 = view.findViewById(R.id.iv1);
            tv1 = view.findViewById(R.id.tv1);
            tv2 = view.findViewById(R.id.tv2);
        }
    }

}
