package com.penta.unifieduiframwork.itemview;

import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.penta.library.UUBaseViewHolder;
import com.penta.unifieduiframwork.R;

public class GirlViewHolder extends UUBaseViewHolder {

    SimpleDraweeView iv1;
    TextView tv1;
    TextView tv2;

    public GirlViewHolder(View view) {
        super(view);
        iv1 = view.findViewById(R.id.iv1);
        tv1 = view.findViewById(R.id.tv1);
        tv2 = view.findViewById(R.id.tv2);
    }

}