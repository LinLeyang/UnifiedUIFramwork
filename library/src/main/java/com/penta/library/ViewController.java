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

public class ViewController {

    ViewAdapter adapter;
    ListView listView;
    List<Item> itemViewList = new ArrayList<>();
    Set<String> itemTypeSet = new HashSet<>();
    Context context;

    Listener listener;

    public ViewController init(Context context, ListView listView) {
        return init(context, listView, 1);
    }

    public ViewController init(Context context, ListView listView, int viewTypeCount) {
        this.context = context;
        this.listView = listView;
        adapter = new ViewAdapter(context);
        adapter.setItemViewList(itemViewList);
        adapter.setViewTypeCount(viewTypeCount);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Event event = new Event() {
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

    public ViewController addItem(Item itemView) {
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

    public void setListener(Listener listener) {
        this.listener = listener;
    }
}
