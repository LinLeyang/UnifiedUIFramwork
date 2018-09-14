package com.penta.library;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by linyueyang on 2018/6/28.
 * <p>
 * 所有View控制类
 */

public class UUViewController {

    UUAdapterInterface adapter;
    List<UUItem> itemViewList = new ArrayList<>();
    Set<String> itemTypeSet = new HashSet<>();
    Map<String, UUItem> itemMap = new HashMap();
    Context context;

    UUListener listener;

    public UUViewController init(Context context, ListView listView) {
        return init(context, listView, 1);
    }

    public UUViewController init(Context context, RecyclerView recyclerView) {
        this.context = context;
        UURvAdapter uuRvAdapter = new UURvAdapter(context);
        adapter = uuRvAdapter;
        uuRvAdapter.setItemViewList(itemViewList);
        recyclerView.setAdapter(uuRvAdapter);
        return this;
    }

    public UUViewController init(Context context, ListView listView, int viewTypeCount) {
        this.context = context;
        UUViewAdapter uuViewAdapter = new UUViewAdapter(context);
        adapter = uuViewAdapter;
        uuViewAdapter.setItemViewList(itemViewList);
        uuViewAdapter.setViewTypeCount(viewTypeCount);
        listView.setAdapter(uuViewAdapter);

        return this;
    }

    public UUViewController addItem(UUItem itemView) {
        itemViewList.add(itemView);
        itemTypeSet.add(itemView.getItemType());
        itemMap.put(itemView.getItemType(), itemView);
        if (null != listener) {
            itemView.setListener(listener);
        }
        return this;
    }

    public void refresh() {
        adapter.setItemViewMap(itemMap);
        adapter.notifyDataSetChanged();
    }

    public void clear() {
        itemViewList.clear();
        itemTypeSet.clear();
    }

    public void setListener(UUListener listener) {
        this.listener = listener;
    }

    public UUListener getListener() {
        return listener;
    }
}
