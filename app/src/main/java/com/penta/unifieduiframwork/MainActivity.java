package com.penta.unifieduiframwork;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.header.bezierlayout.BezierLayout;
import com.lcodecore.tkrefreshlayout.header.progresslayout.ProgressLayout;
import com.penta.library.Event;
import com.penta.library.Listener;
import com.penta.library.ViewController;
import com.penta.unifieduiframwork.itemview.BoyItem;
import com.penta.unifieduiframwork.itemview.GirlItem;
import com.penta.unifieduiframwork.model.Boy;
import com.penta.unifieduiframwork.model.Girl;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements Listener {

    ViewController viewController;
    int pageNum = 0;
    TwinklingRefreshLayout refreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Fresco.initialize(this);
        ListView listView = findViewById(R.id.lv);

        refreshLayout = findViewById(R.id.refreshLayout);

        viewController = new ViewController();
        viewController.init(this, listView, 2);
        viewController.setListener(this);

        ProgressLayout headerView = new ProgressLayout(this);
        headerView.setColorSchemeResources(R.color.girl_bg);
        refreshLayout.setHeaderView(headerView);
        refreshLayout.setOverScrollRefreshShow(false);
        refreshLayout.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(final TwinklingRefreshLayout refreshLayout) {
                getData(true);
            }

            @Override
            public void onLoadMore(final TwinklingRefreshLayout refreshLayout) {
                getData(false);
            }
        });
        getData(false);
    }

    public void getData(final boolean isRefresh) {
        if (isRefresh) {
            pageNum = 0;
            viewController.clear();
        }
        pageNum += 1;

        OkHttpClient mOkHttpClient = new OkHttpClient();
        final Request request = new Request.Builder()
                .url("http://gank.io/api/data/%E7%A6%8F%E5%88%A9/10/" + pageNum)
                .build();
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                JSONArray jsonList = null;
                String htmlStr = response.body().string();
                JSONObject jsonObject = JSON.parseObject(htmlStr);
                String results = jsonObject.getString("results");

                if (null != results)
                    jsonList = JSON.parseArray(results);

                if (null != jsonList) {
                    for (int i = 0; i < jsonList.size(); i++) {
                        JSONObject jsonObjectItem = jsonList.getJSONObject(i);

                        String desc = jsonObjectItem.getString("desc");
                        int date = 0;
                        try {
                            if (null != desc) {
                                String[] descList = desc.split("-");
                                date = Integer.parseInt(descList[descList.length - 1]);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        if (date % 2 == 1) {
                            Boy boy = JSON.parseObject(jsonObjectItem.toJSONString(), Boy.class);
                            BoyItem boyItemView = new BoyItem();
                            boyItemView.setData(boy);
                            viewController.addItem(boyItemView);

                        } else {
                            Girl girl = JSON.parseObject(jsonObjectItem.toJSONString(), Girl.class);
                            GirlItem girlItemView = new GirlItem();
                            girlItemView.setData(girl);
                            viewController.addItem(girlItemView);
                        }
                    }
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        viewController.refresh();
                        if (isRefresh) {
                            refreshLayout.finishRefreshing();
                        } else {
                            refreshLayout.finishLoadmore();
                        }

                    }
                });
            }
        });
    }

    @Override
    public void onEvent(Event event) {
        switch (event.getEventId()) {
            case "click":
                Toast.makeText(this, "EventId:" + event.getEventId() + "\nPosition:" + event.getPosition(), Toast.LENGTH_LONG).show();
                break;
            case "boyPic":
                Boy boy = (Boy) event.getData();
                Toast.makeText(this, "EventId:" + event.getEventId() + "\nPosition:" + event.getPosition() + "\nWho:" + boy.getWho(), Toast.LENGTH_LONG).show();
                break;
            default:
                break;
        }
    }
}
