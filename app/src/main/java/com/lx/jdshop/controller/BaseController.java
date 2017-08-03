package com.lx.jdshop.controller;

import android.content.Context;

import com.lx.jdshop.Listenter.IModeChaneListener;


/**
 * Created by Xia_焱 on 2017/7/18.
 */

public abstract class BaseController {
    protected Context mContext;
    protected IModeChaneListener mListener;

    public void setIModeChangeListener(IModeChaneListener listener) {
        this.mListener=listener;
    }
    public BaseController(Context ctx) {
        this.mContext = ctx;
    }

    /**
     * 一个页面可能有多个网络请求  action区别网络请求
     * values 请求的数据
     */
    public void sendAsyncMessage(final int action, final Object... values) {
        new Thread() {
            @Override
            public void run() {
                handleMessage(action, values);
            }
        }.start();
    }

    protected abstract void handleMessage(int action, Object... values);

}
