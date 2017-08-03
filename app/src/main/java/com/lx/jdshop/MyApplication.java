package com.lx.jdshop;

import android.app.Application;

import com.lx.jdshop.Bean.RLoginresult;


/**
 * Created by Xia_焱 on 2017/7/20.
 */

public class MyApplication extends Application {

    public RLoginresult mLoginResult;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public  void setRLoginResult(RLoginresult bean) {
        this.mLoginResult = bean;
    }
}
