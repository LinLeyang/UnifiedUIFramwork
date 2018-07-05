package com.penta.library;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by linyueyang on 2018/6/28.
 */

public class ViewAdapter extends BaseAdapter {

    List<Item> itemViewList;
    Context context;
    int viewTypeCount = 1;
    Map<String, Integer> itemTypeMap = new HashMap<>();

    ViewAdapter(Context context) {
        this.context = context;
    }

    public void setItemViewList(List<Item> itemViewList) {
        this.itemViewList = itemViewList;
    }

    @Override
    public int getCount() {
        if (null != itemViewList)
            return itemViewList.size();
        return 0;
    }

    @Override
    public Item getItem(int position) {
        return itemViewList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Item itemView = getItem(position);

        if (null == convertView) {
            convertView = itemView.initView(context);
        }

        itemView.bindDataToView(convertView, position);

        return convertView;
    }

    public void setItemTypeSet(Set<String> itemTypeSet) {
//        this.viewTypeCount = itemTypeSet.size();
        int i = 0;
        for (String str : itemTypeSet) {
            itemTypeMap.put(str, i);
            i++;
        }

    }

    public void setViewTypeCount(int viewTypeCount) {
        this.viewTypeCount = viewTypeCount;
    }

    @Override
    public int getViewTypeCount() {
        return viewTypeCount;
    }

    @Override
    public int getItemViewType(int position) {
        int re = 0;

        if (null != itemTypeMap)
            re = itemTypeMap.get(getItem(position).getItemType());
        return re;

    }

}
