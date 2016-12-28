package com.tlf.basic.http;

import com.tlf.basic.bean.BaseJson;

import okhttp3.Call;
import okhttp3.Request;

/**
 * Created by tanlifei on 16/8/19.
 */
public interface HttpListener {
    void onBefore(Request request);
    void onCusResponse(BaseJson response);
    void onError(Call call, Exception e);
    void onAfter() ;
}
