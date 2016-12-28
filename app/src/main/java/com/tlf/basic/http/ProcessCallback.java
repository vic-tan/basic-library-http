package com.tlf.basic.http;

import android.content.Context;

import com.google.gson.Gson;
import com.tlf.basic.bean.BaseJson;
import com.tlf.basic.http.okhttp.callback.Callback;
import com.tlf.basic.utils.AppException;
import com.tlf.basic.utils.ExceptionConstants;
import com.tlf.basic.utils.Logger;
import com.tlf.basic.utils.StringUtils;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 每个方法都返回给调用者
 * Created by tanlifei on 15/12/14.
 */
public class ProcessCallback extends Callback<BaseJson> {
    private Context mContext;
    private HttpListener httpListener;

    public ProcessCallback(Context mContext, HttpListener httpListener) {
        this.mContext = mContext;
        Logger.d(mContext.getClass().getName());
        this.httpListener = httpListener;
    }


    @Override
    public void onAfter() {
        super.onAfter();
        httpListener.onAfter();
    }

    @Override
    public BaseJson parseNetworkResponse(Response response) throws Exception {
        String string = response.body().string();
        BaseJson jsonBean = new Gson().fromJson(replaceId(new String(string)), BaseJson.class);
        return jsonBean;
    }

    @Override
    public void onResponse(BaseJson response) {
        try {
            if (null == response) {
                throw new AppException(mContext, ExceptionConstants.CODE_DATA_ERROR);
            }
            if (StringUtils.isEquals(response.getCode(), ExceptionConstants.CODE_SUCCEE)) {
                httpListener.onCusResponse(response);
            } else {
                throw new AppException(mContext, response.getMsg());
            }
        } catch (AppException e) {
            e.printStackTrace();
        } finally {
        }
    }

    @Override
    public void onBefore(Request request) {
        super.onBefore(request);
        httpListener.onBefore(request);
    }

    @Override
    public void onError(Call call, Exception e) {
        super.onError(call, e);
        httpListener.onError(call, e);
        try {
            throw new AppException(mContext, e);
        } catch (AppException e1) {
            e1.printStackTrace();
        }
    }
}
