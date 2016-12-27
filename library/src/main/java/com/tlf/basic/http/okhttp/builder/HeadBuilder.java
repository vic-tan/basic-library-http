package com.tlf.basic.http.okhttp.builder;


import com.tlf.basic.http.okhttp.OkHttpUtils;
import com.tlf.basic.http.okhttp.request.OtherRequest;
import com.tlf.basic.http.okhttp.request.RequestCall;

/**
 * Created by tanlifei on 16/3/2.
 */
public class HeadBuilder extends GetBuilder
{
    @Override
    public RequestCall build()
    {
        return new OtherRequest(null, null, OkHttpUtils.METHOD.HEAD, url, tag, params, headers).build();
    }
}
