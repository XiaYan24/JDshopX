package com.lx.jdshop;

import android.app.Application;

import com.lx.jdshop.Bean.RLoginResult;


/**
 * Created by Xia_焱 on 2017/7/20.
 */

public class MyApplication extends Application {

    public RLoginResult mRLoginResult;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public void setRLoginResult(RLoginResult bean) {
        mRLoginResult=bean;
    }
}
