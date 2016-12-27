package com.tlf.basic.http.okhttp.builder;

import java.util.Map;

/**
 * Created by tanlifei on 16/3/1.
 */
public interface HasParamsable
{
    public abstract OkHttpRequestBuilder params(Map<String, String> params);

    public abstract OkHttpRequestBuilder paramsForJson(Map<String, Object> params);

    public abstract OkHttpRequestBuilder addParams(String key, String val);

    public abstract OkHttpRequestBuilder addParamsForJson(String key, String val);

}
