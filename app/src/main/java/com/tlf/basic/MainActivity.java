package com.tlf.basic;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.tlf.basic.bean.BaseJson;
import com.tlf.basic.http.DialogCallback;
import com.tlf.basic.http.HttpListener;
import com.tlf.basic.http.MultipleCallback;
import com.tlf.basic.http.ProcessCallback;
import com.tlf.basic.http.ResultCallback;
import com.tlf.basic.http.okhttp.OkHttpUtils;
import com.tlf.basic.uikit.kprogresshud.KProgressHUD;
import com.tlf.basic.utils.ToastUtils;
import com.tlf.basic.utils.UrlConstants;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Request;

public class MainActivity extends Activity {

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;

    }
    public void A(View v) {
        OkHttpUtils.post().url(UrlConstants.APP_VERSION_UPDATE).paramsForJson(tagList()).build().execute(new ResultCallback(mContext) {

            @Override
            public void onCusResponse(BaseJson response) {
                ToastUtils.show(mContext, response.getData().toString());
            }


        });
       /* OkHttpUtils.post().url("http://192.168.2.11:8080/test.json").build().execute(new DialogCallback(mContext) {
            @Override
            public void onCusResponse(BaseJson response) {
                ToastUtils.show(mContext,response.getData()+"");
            }
        });*/
    }

    public void B(View v) {
        OkHttpUtils.post().url(UrlConstants.APP_VERSION_UPDATE).paramsForJson(tagList()).build().execute(new DialogCallback(mContext) {
            @Override
            public void onCusResponse(BaseJson response) {
                ToastUtils.show(mContext, response.getData() + "");
            }
        });
    }

    public void C(View v) {
        OkHttpUtils.post().url(UrlConstants.APP_VERSION_UPDATE).paramsForJson(tagList()).build().execute(new ProcessCallback(mContext, new HttpListener() {
            @Override
            public void onAfter() {
                ToastUtils.show(mContext, "onAfter");
            }

            @Override
            public void onBefore(Request request) {
                ToastUtils.show(mContext, "onBefore");
            }

            @Override
            public void onError(Call call, Exception e) {
                ToastUtils.show(mContext, "onError");
            }

            @Override
            public void onCusResponse(BaseJson response) {
                ToastUtils.show(mContext, "onCusResponse");
            }
        }));
    }

    public void D(View v) {
        OkHttpUtils.post().url(UrlConstants.APP_VERSION_UPDATE).paramsForJson(tagList()).build().execute(new MultipleCallback(mContext) {
            @Override
            public void onCusResponse(BaseJson response, KProgressHUD hud) {
                scheduleTwo(hud);
                ToastUtils.show(mContext, "接口1");
            }
        });
    }
    public void two(KProgressHUD hud) {
        OkHttpUtils.post().url(UrlConstants.APP_VERSION_UPDATE).paramsForJson(tagList()).build().execute(new MultipleCallback(mContext, hud,false) {
            @Override
            public void onCusResponse(BaseJson response, KProgressHUD hud) {
                ToastUtils.show(mContext, "接口2");
                scheduleThree(hud);
            }
        });
    }


    public void three(KProgressHUD hud) {
        OkHttpUtils.post().url(UrlConstants.APP_VERSION_UPDATE).paramsForJson(tagList()).build().execute(new MultipleCallback(mContext, hud, true) {
            @Override
            public void onCusResponse(BaseJson response, KProgressHUD hud) {
                ToastUtils.show(mContext, "接口3");
            }
        });
    }


    public Map<String, Object> tagList() {
        Map<String, Object> map = new HashMap<>();
        map.put("sid", "ipeiban2016");
        return map;
    }

    private void scheduleTwo(final KProgressHUD hud) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                two(hud);
            }
        }, 2000);
    }

    private void scheduleThree(final KProgressHUD hud) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                three(hud);
            }
        }, 2000);
    }
}
