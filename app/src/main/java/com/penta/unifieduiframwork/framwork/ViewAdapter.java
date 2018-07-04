package com.penta.unifieduiframwork.framwork;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by linyueyang on 2018/6/28.
 */

public class ViewAdapter extends BaseAdapter {

    List<ItemView> itemViewList = new ArrayList<>();
    Context context;
    int viewTypeCount;
    Map<String, Integer> itemTypeMap = new HashMap<>();

    ViewAdapter(Context context) {
        this.context = context;
    }

    public void setItemViewList(List<ItemView> itemViewList) {
        if (null != itemViewList) {
            this.itemViewList.addAll(itemViewList);
        } else {
            this.itemViewList.clear();
        }
    }

    @Override
    public int getCount() {
        return itemViewList.size();
    }

    @Override
    public ItemView getItem(int position) {
        return itemViewList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ItemView itemView = getItem(position);

        if (null == convertView) {
            convertView = itemView.initView(context);
        }

        itemView.bindDataToView(convertView,position);

        return convertView;
    }

    public void setItemTypeSet(Set<String> itemTypeSet) {
        this.viewTypeCount = itemTypeSet.size();
        int i = 0;
        for (String str : itemTypeSet) {
            itemTypeMap.put(str, i);
            i++;
        }

    }

    @Override
    public int getViewTypeCount() {
        return viewTypeCount;
    }

    @Override
    public int getItemViewType(int position) {
        return itemTypeMap.get(getItem(position).getItemType());
    }

}
