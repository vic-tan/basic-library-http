package com.tlf.basic.utils;

import android.content.Context;

import java.net.SocketTimeoutException;

/**
 * 本app自己定义的异常
 * Created by tanlifei on 15/12/14.
 */
public class AppException extends Exception {

    public static final String TAG = "AppException";

    public static void illegalArgument(String msg, Object... params) {
        throw new IllegalArgumentException(String.format(msg, params));
    }


    /**
     * 请求过程正常完成,自己的后服务器返回异常处理
     *
     * @param mContext
     * @param msgCode
     */
    public AppException(Context mContext, String msgCode) {
        super(msgCode);
        if (StringUtils.isEquals(msgCode, ExceptionConstants.CODE_DATA_ERROR)) {
            ToastUtils.show(mContext, "数据异常");
        } else if (StringUtils.isEquals(msgCode, ExceptionConstants.CODE_VALUE_0014)) {
            ToastUtils.show(mContext, "在另一台设备登录");
        } else {
            ToastUtils.show(mContext, msgCode);
            Logger.e(TAG, msgCode);
        }

    }

    /**
     * 请求过程未知异常处理,如超时,网络,数据等异常时处理
     *
     * @param mContext
     * @param e
     */
    public AppException(Context mContext, Exception e) {
        super(e.getMessage());
        if (e instanceof SocketTimeoutException) {//超时
            ToastUtils.show(mContext, "请求超时");
        } else {
            if (!NetUtils.isConnected(mContext)) {
                ToastUtils.show(mContext, mContext.getResources().getString(com.tlf.basic.R.string.common_net_error));
                return;
            }
            Logger.e(TAG, e.toString());
        }

    }

}
