package com.penta.library;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by linyueyang on 2018/8/16.
 */

public class UURvAdapter extends RecyclerView.Adapter<UUBaseViewHolder> implements UUAdapterInterface {

    List<UUItem> itemViewList;
    Context context;
    Map<String, Integer> itemTypeMap = new HashMap<>();
    Map<Integer, UUItem> itemMap = new HashMap<>();

    public UURvAdapter(Context context) {
        this.context = context;
    }

    public void setItemViewList(List<UUItem> itemViewList) {
        this.itemViewList = itemViewList;
    }

    @Override
    public UUBaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        UUItem itemView = itemMap.get(viewType);
        return itemView.onCreateViewHolder(itemView.initView(context,parent));
    }

    @Override
    public void onBindViewHolder(UUBaseViewHolder holder, int position) {
        UUItem itemView = getItem(position);
        itemView.onBindDataToView(holder, position);
    }

    @Override
    public int getItemCount() {
        if (null != itemViewList)
            return itemViewList.size();
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        int re = 0;

        if (null != itemTypeMap)
            re = itemTypeMap.get(getItem(position).getItemType());
        return re;
    }

    public UUItem getItem(int position) {
        return itemViewList.get(position);
    }

//    public void setItemViewMap(Set<String> itemTypeSet) {
////        this.viewTypeCount = itemTypeSet.size();
//        int i = 0;
//        for (String str : itemTypeSet) {
//            itemTypeMap.put(str, i);
//            i++;
//        }
//
//    }

    @Override
    public void setItemViewMap(Map<String, UUItem> map) {
        int i = 0;
        for (String str : map.keySet()) {
            itemTypeMap.put(str, i);
            itemMap.put(i, map.get(str));
            i++;
        }
    }
}
