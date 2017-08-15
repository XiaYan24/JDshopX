package com.lx.jdshop.Fragment;

import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.lx.jdshop.Listenter.IModeChaneListener;
import com.lx.jdshop.controller.BaseController;

/**
 * Created by Xia_焱 on 2017/8/14.
 */

public abstract class BaseFragmentX  extends Fragment implements IModeChaneListener {
    protected BaseController mController;
    protected Handler mHandler = new Handler() {

        public void handleMessage(android.os.Message msg) {
            handlerMessage(msg);
        }

    };

    protected abstract void initUI();

    protected void handlerMessage(Message msg) {
        // default Empty implementn
    }

    protected void initController() {
        // default Empty implementn
    }
    @Override
    public void onModeChaned(int action, Object... values) {
        mHandler.obtainMessage(action, values[0]).sendToTarget();
    }
    public void tip(String tipStr) {
        Toast.makeText(getActivity(), tipStr, Toast.LENGTH_SHORT).show();
    }
}
