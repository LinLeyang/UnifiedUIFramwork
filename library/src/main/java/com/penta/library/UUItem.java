package com.penta.library;

import android.content.Context;
import android.view.View;

/**
 * Created by linyueyang on 2018/6/28.
 */

public interface UUItem<T> {

    String getItemType();

    T getData();

    void setData(T t);

    int getLayoutId();

    View initView(Context context);

    void bindDataToView(View convertView, int position);

    void setListener(UUListener listener);
}
