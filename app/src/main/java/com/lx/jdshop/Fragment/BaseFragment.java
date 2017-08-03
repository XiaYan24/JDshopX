package com.lx.jdshop.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lx.jdshop.Listenter.IModeChaneListener;
import com.lx.jdshop.controller.BaseController;

/**
 * Created by Xia_焱 on 2017/7/19.
 */

public abstract class BaseFragment extends Fragment implements IModeChaneListener {

    protected BaseController mController;
    protected Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            handlerMessage(msg);
        }
    };
    public View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = initView(inflater);
        return view;

    }

    protected void initData(){

    }
    protected void handlerMessage(Message msg) {
    }


    @Override
    public void onModeChaned(int action, Object... values) {
        mHandler.obtainMessage(action, values[0]).sendToTarget();
    }
    public abstract View initView(LayoutInflater inflater);
}
