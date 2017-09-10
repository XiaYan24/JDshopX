package com.lx.jdshop.controller;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.lx.jdshop.Bean.RResult;
import com.lx.jdshop.Bean.RShopcar;
import com.lx.jdshop.Util.NetworkUtil;
import com.lx.jdshop.cons.IdiyMessage;
import com.lx.jdshop.cons.NetworkConst;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Xia_焱 on 2017/9/10.
 */

public class ShopCarController extends UserController {
    public ShopCarController(Context ctx) {
        super(ctx);
    }

    @Override
    protected void handleMessage(int action, Object... values) {
        switch (action) {
            case IdiyMessage.SHOPCAR_LIST_ACTION:
                mListener.onModeChaned(IdiyMessage.SHOPCAR_LIST_ACTION_RESULT, loadShopCarList());
                break;
        }
    }

    private List<RShopcar> loadShopCarList() {
        HashMap<String, String> param = new HashMap<>();
        param.put("userId", mUserId + "");
        String jsonStr = NetworkUtil.doPost(NetworkConst.SHOPCAR_URL, param);
        RResult rResult = JSON.parseObject(jsonStr, RResult.class);
        if (rResult.isSuccess()) {
            return JSON.parseArray(rResult.getResult(), RShopcar.class);
        }
        return new ArrayList<RShopcar>();
    }
}
