package com.lx.jdshop.controller;

import android.content.Context;


import com.alibaba.fastjson.JSON;
import com.lx.jdshop.Bean.RProductInfo;
import com.lx.jdshop.Bean.RResult;
import com.lx.jdshop.Util.NetworkUtil;
import com.lx.jdshop.cons.IdiyMessage;
import com.lx.jdshop.cons.NetworkConst;

/**
 * Created by Xia_焱 on 2017/7/25.
 */

public class ProductDetailsController extends BaseController {
    public ProductDetailsController(Context ctx) {
        super(ctx);
    }

    @Override
    protected void handleMessage(int action, Object... values) {
        switch (action){
            case IdiyMessage.PRODUCT_INFO_ACTION:
                mListener.onModeChaned(IdiyMessage.PRODUCT_INFO_ACTION_RESULT,loadProductInfo((Long) values[0]));
                break;

        }
    }

    private RProductInfo loadProductInfo(Long pid) {
        String jsonStr = NetworkUtil.doGet(NetworkConst.PRODUCTINFO_URL
                + "?id=" + pid);
        RResult resultBean = JSON.parseObject(jsonStr, RResult.class);
        if (resultBean.isSuccess()) {
            return JSON.parseObject(resultBean.getResult(), RProductInfo.class);
        }
        return null;
    }
}
