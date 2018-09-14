package com.penta.library;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by linyueyang on 2018/6/28.
 */

public interface UUItem<T, VH extends UUBaseViewHolder> {

    String getItemType();

    T getData();

    void setData(T t);

    int getLayoutId();

    View initView(Context context, ViewGroup parent);

    VH onCreateViewHolder(View convertView);

    void onBindDataToView(VH viewHolder, int position);

    void bindDataToView(View convertView, int position);

    void setListener(UUListener listener);
}
