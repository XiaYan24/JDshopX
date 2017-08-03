package com.lx.jdshop.controller;

import android.content.Context;


import com.alibaba.fastjson.JSON;
import com.lx.jdshop.Bean.Banner;
import com.lx.jdshop.Bean.RRecommndProduct;
import com.lx.jdshop.Bean.RResult;
import com.lx.jdshop.Bean.RSecondKill;
import com.lx.jdshop.Util.NetworkUtil;
import com.lx.jdshop.cons.IdiyMessage;
import com.lx.jdshop.cons.NetworkConst;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Xia_焱 on 2017/7/20.
 */

public class HomeController extends BaseController {

    public HomeController(Context ctx) {
        super(ctx);
    }

    @Override
    protected void handleMessage(int action, Object... values) {
        switch (action) {
            case IdiyMessage.ACTION_LOAD_AD1:
                mListener.onModeChaned(IdiyMessage.ACTION_LOAD_AD1_RESULT,
                        loadBanner((Integer) values[0]));
                break;
            case IdiyMessage.ACTION_LOAD_AD2:
                mListener.onModeChaned(IdiyMessage.ACTION_LOAD_AD2_RESULT,
                        loadBanner((Integer) values[0]));
                break;
            case IdiyMessage.SECOND_KILL_ACTION:
                mListener.onModeChaned(IdiyMessage.SECOND_KILL_ACTION_RESULT,
                        loadSecondKill());
                break;
            case IdiyMessage.RECOMMEND_ACTION:
                mListener.onModeChaned(IdiyMessage.RECOMMEND_ACTION_RESULT,
                        loadRecommend());
                break;
        }
    }
    private List<RRecommndProduct> loadRecommend(){
        String jsonStr = NetworkUtil.doGet(NetworkConst.GETYOURFAV_URL);
        RResult resultBean = JSON.parseObject(jsonStr, RResult.class);
        if (resultBean.isSuccess()){
            try {
                JSONObject object = new JSONObject(resultBean.getResult());
                String rows = object.getString("rows");
                return JSON.parseArray(rows, RRecommndProduct.class);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return new ArrayList<RRecommndProduct>();
    }
    private List<RSecondKill> loadSecondKill(){
        String jsonStr = NetworkUtil.doGet(NetworkConst.SECKILL_URL);
        RResult resultBean = JSON.parseObject(jsonStr, RResult.class);
        if (resultBean.isSuccess()){
            try {
                JSONObject object = new JSONObject(resultBean.getResult());
                String rows = object.getString("rows");
                return JSON.parseArray(rows, RSecondKill.class);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return new ArrayList<RSecondKill>();
    }
    private List<Banner> loadBanner(int type) {
        ArrayList<Banner> result = new ArrayList<Banner>();
        String urlPath = NetworkConst.BANNER_URL + "?adKind=" + type;
        String jsonStr = NetworkUtil.doGet(urlPath);
        RResult resultBean = JSON.parseObject(jsonStr, RResult.class);
        if (resultBean.isSuccess()) {
            return JSON.parseArray(resultBean.getResult(), Banner.class);
        }
        return result;
    }
}
