package com.tlf.basic.http;

import android.content.Context;

import com.google.gson.Gson;
import com.tlf.basic.R;
import com.tlf.basic.bean.BaseJson;
import com.tlf.basic.http.okhttp.callback.Callback;
import com.tlf.basic.uikit.kprogresshud.KProgressHUD;
import com.tlf.basic.utils.AppException;
import com.tlf.basic.utils.ExceptionConstants;
import com.tlf.basic.utils.Logger;
import com.tlf.basic.utils.StringUtils;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;


/**
 * 多次请求只显示同一个提示框加载基类，
 * 所有的提示框都得继承本类，
 * Created by tanlifei on 15/12/14.
 */
public abstract class MultipleCallback extends Callback<BaseJson> {

    protected KProgressHUD hud;
    protected Context mContext;
    protected boolean frist, last;


    /**
     * 第一个接口调用 这个方法
     *
     * @param mContext
     */
    public MultipleCallback(Context mContext) {
        this.mContext = mContext;
        Logger.d(mContext.getClass().getName());
        frist = true;
        this.last = false;
        this.hud = KProgressHUD.create(mContext)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setDimAmount(0.5f)
                .setLabel(mContext.getResources().getString(R.string.common_dialog_loading))
                .setCancellable(true);

    }



    /**
     * 除了了第一个外接口调用这个方法
     *
     * @param mContext
     * @param hud
     * @param last 是不是最后一个接口，true 表示最后一个，则请求完隐藏提示框
     */
    public MultipleCallback(Context mContext, KProgressHUD hud, boolean last) {
        this.mContext = mContext;
        this.last = last;
        this.hud = hud;
        frist = false;
    }


    @Override
    public void onAfter() {
        super.onAfter();
        if (last) {
            hud.dismiss();
        }
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
                onCusResponse(response, hud);
            } else {
                last = true;
                throw new AppException(mContext, response.getMsg());
            }
        } catch (AppException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBefore(Request request) {
        super.onBefore(request);
        if (frist) {
            hud.show();
        }
    }

    @Override
    public void onError(Call call, Exception e) {
        super.onError(call, e);
        hud.dismiss();
        try {
            throw new AppException(mContext, e);
        } catch (AppException e1) {
            e1.printStackTrace();
        }
    }

    public abstract void onCusResponse(BaseJson response, KProgressHUD hud);


}
