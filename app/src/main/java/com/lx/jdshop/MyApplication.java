package com.lx.jdshop;

import android.app.Application;

import com.lx.jdshop.Bean.RLoginResultX;


/**
 * Created by Xia_焱 on 2017/7/20.
 */

public class MyApplication extends Application {

    public RLoginResultX mRLoginResult;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public void setRLoginResult(RLoginResultX bean) {
        mRLoginResult=bean;
    }
}
