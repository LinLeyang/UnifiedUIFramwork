package com.penta.library;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by linyueyang on 2018/7/3.
 */

public abstract class UUBaseItem<T, VH extends UUBaseItem.UUBaseViewHolder> implements UUItem<T> {

    T t;
    @Override
    public T getData() {
        return t;
    }

    @Override
    public void setData(T t) {
        this.t = t;
    }

    UUListener listener;

    @Override
    public View initView(Context context) {
        View view = LayoutInflater.from(context).inflate(getLayoutId(), null);
        return view;
    }

    @Override
    public void setListener(UUListener listener) {
        this.listener = listener;
    }

    public UUListener getListener() {
        return listener;
    }

    public abstract VH onCreateViewHolder(View convertView);

    public abstract void onBindDataToView(VH viewHolder, int position);

    @Override
    public void bindDataToView(View convertView, int position) {
        VH viewHolder;
        if (null != convertView.getTag() && convertView.getTag() instanceof UUBaseItem.UUBaseViewHolder) {
            viewHolder = (VH) convertView.getTag();
        } else {
            viewHolder = onCreateViewHolder(convertView);
            convertView.setTag(viewHolder);
        }

        onBindDataToView(viewHolder, position);

    }

    public static abstract class UUBaseViewHolder {
        public UUBaseViewHolder(View itemView) {
            if (itemView == null) {
                throw new IllegalArgumentException("itemView may not be null");
            }
        }
    }

}
