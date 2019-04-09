package com.mwj.depthview.depthview_master;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.alibaba.fastjson.JSON;
import com.mwj.depthview.depthview_lib.DepthDataBean;
import com.mwj.depthview.depthview_lib.DepthMapView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.dep)
    DepthMapView dep;

    private String jsonData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        dep.setPriceLimit(6);//设置位数
        dep.setCountLimit(6);

        setData();

    }

    private void setData() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Random ran = new Random();
                int Value = ran.nextInt(4) + 1;
                switch (Value) {
                    case 1:
                        jsonData = DepthData.depthA;
                        break;
                    case 2:
                        jsonData = DepthData.depthB;
                        break;
                    case 3:
                        jsonData = DepthData.depthC;
                        break;
                    case 4:
                        jsonData = DepthData.depthD;
                        break;

                }

                newDepth();
                handler.postDelayed(this, 5000);
            }
        }, 1000);
        jsonData = DepthData.depthB;

        new Thread(new Runnable() {
            @Override
            public void run() {
                newDepth();
            }
        }).start();
    }

    private void newDepth() {
        final List<DepthDataBean> listDepthBuy = new ArrayList<>();
        final List<DepthDataBean> listDepthSell = new ArrayList<>();

        DepthList depthList = JSON.parseObject(jsonData, DepthList.class);
        if (depthList == null) return;

        final List<String[]> bids = depthList.getBids();
        final List<String[]> asks = depthList.getAsks();

        if (bids != null) {
            for (int i = 0; i < bids.size(); i++) {
                DepthDataBean obj = new DepthDataBean();
                obj.setPrice(Float.parseFloat(bids.get(i)[0]));
                obj.setVolume(Float.parseFloat(bids.get(i)[1]));
                listDepthBuy.add(obj);
            }
        }
        if (asks != null) {
            for (int i = 0; i < asks.size(); i++) {
                DepthDataBean obj = new DepthDataBean();
                obj.setPrice(Float.parseFloat(asks.get(i)[0]));
                obj.setVolume(Float.parseFloat(asks.get(i)[1]));
                listDepthSell.add(obj);
            }
        }


        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                dep.setData(listDepthBuy, listDepthSell);
            }
        });


    }

}
