package com.penta.unifieduiframwork;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.penta.unifieduiframwork.framwork.Event;
import com.penta.unifieduiframwork.framwork.Listener;
import com.penta.unifieduiframwork.framwork.ViewController;
import com.penta.unifieduiframwork.itemview.BoyItemView;
import com.penta.unifieduiframwork.itemview.GirlItemView;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Fresco.initialize(this);
        ListView listView = findViewById(R.id.lv);

        viewController = new ViewController();
        viewController.init(this, listView);
        viewController.setListener(this);
        getData();
    }

    public void getData() {

        OkHttpClient mOkHttpClient = new OkHttpClient();
        final Request request = new Request.Builder()
                .url("http://gank.io/api/data/%E7%A6%8F%E5%88%A9/10/2")
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
                        JSONObject jsonObject1 = jsonList.getJSONObject(i);
                        if (jsonObject1.getString("desc").equals("2018-06-13")) {
                            Boy boy = JSON.parseObject(jsonObject1.toJSONString(), Boy.class);
                            BoyItemView boyItemView = new BoyItemView();
                            boyItemView.setData(boy);
                            viewController.addItem(boyItemView);

                        } else {
                            Girl girl = JSON.parseObject(jsonObject1.toJSONString(), Girl.class);
                            GirlItemView girlItemView = new GirlItemView();
                            girlItemView.setData(girl);
                            viewController.addItem(girlItemView);
                        }
                    }
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        viewController.execute();
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
