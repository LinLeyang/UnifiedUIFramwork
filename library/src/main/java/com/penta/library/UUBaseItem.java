package com.penta.library;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;

/**
 * Created by linyueyang on 2018/7/3.
 */

public abstract class UUBaseItem<T, VH extends UUBaseViewHolder> implements UUItem<T, VH> {

    T t;
    protected Context context;

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
    public VH onCreateViewHolder(View convertView) {
        VH vh = null;
        Class<VH> entityClass = (Class<VH>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
        Class cls[] = new Class[] { View.class };
        try {
            Constructor<VH> con = entityClass.getConstructor(cls);
            vh = con.newInstance(convertView);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return vh;
    }

    @Override
    public View initView(Context context, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(getLayoutId(), parent, false);
        this.context = context;
        return view;
    }

    @Override
    public void setListener(UUListener listener) {
        this.listener = listener;
    }

    public UUListener getListener() {
        return listener;
    }

    //public abstract VH onCreateViewHolder(View convertView);

    public abstract void onBindDataToView(VH viewHolder, int position);

    @Override
    public void bindDataToView(View convertView, int position) {
        VH viewHolder;
        if (null != convertView.getTag() && convertView.getTag() instanceof UUBaseViewHolder) {
            viewHolder = (VH) convertView.getTag();
        } else {
            viewHolder = onCreateViewHolder(convertView);
            convertView.setTag(viewHolder);
        }

        onBindDataToView(viewHolder, position);

    }


}
