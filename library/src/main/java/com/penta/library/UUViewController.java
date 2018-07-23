package com.penta.library;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by linyueyang on 2018/6/28.
 * <p>
 * 所有View控制类
 */

public class UUViewController {

    UUViewAdapter adapter;
    ListView listView;
    List<UUItem> itemViewList = new ArrayList<>();
    Set<String> itemTypeSet = new HashSet<>();
    Context context;

    UUListener listener;

    public UUViewController init(Context context, ListView listView) {
        return init(context, listView, 1);
    }

    public UUViewController init(Context context, ListView listView, int viewTypeCount) {
        this.context = context;
        this.listView = listView;
        adapter = new UUViewAdapter(context);
        adapter.setItemViewList(itemViewList);
        adapter.setViewTypeCount(viewTypeCount);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                UUEvent event = new UUEvent() {
                    @Override
                    public String getEventId() {
                        return "click";
                    }
                };
                event.setPosition(position);
                event.setData(parent.getAdapter().getItem(position));

                listener.onEvent(event);
            }
        });
        return this;
    }

    public UUViewController addItem(UUItem itemView) {
        itemViewList.add(itemView);
        itemTypeSet.add(itemView.getItemType());
        if (null != listener) {
            itemView.setListener(listener);
        }
        return this;
    }

    public void refresh() {
        adapter.setItemTypeSet(itemTypeSet);
        adapter.notifyDataSetChanged();
    }

    public void clear() {
        itemViewList.clear();
        itemTypeSet.clear();
    }

    public void setListener(UUListener listener) {
        this.listener = listener;
    }
}
