package com.lx.jdshop.controller;

import android.content.Context;


import com.alibaba.fastjson.JSON;
import com.lx.jdshop.Bean.RCommentCount;
import com.lx.jdshop.Bean.RGoodComment;
import com.lx.jdshop.Bean.RProductComment;
import com.lx.jdshop.Bean.RProductInfo;
import com.lx.jdshop.Bean.RResult;
import com.lx.jdshop.Fragment.ProductCommentFragment;
import com.lx.jdshop.Util.NetworkUtil;
import com.lx.jdshop.cons.IdiyMessage;
import com.lx.jdshop.cons.NetworkConst;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Xia_焱 on 2017/7/25.
 */

public class ProductDetailsController extends UserController {
    public ProductDetailsController(Context ctx) {
        super(ctx);
    }

    @Override
    protected void handleMessage(int action, Object... values) {
        switch (action) {
            case IdiyMessage.PRODUCT_INFO_ACTION:
                mListener.onModeChaned(IdiyMessage.PRODUCT_INFO_ACTION_RESULT, loadProductInfo((Long) values[0]));
                break;
            case IdiyMessage.GOOD_COMMENT_ACTION:
                mListener.onModeChaned(IdiyMessage.GOOD_COMMENT_ACTION_RESULT,
                        loadGoodComment((Long) values[0]));
                break;
            case IdiyMessage.GET_COMMENT_COUNT_ACTION:
                mListener.onModeChaned(
                        IdiyMessage.GET_COMMENT_COUNT_ACTION_RESULT,
                        loadCommentCount((Long) values[0]));
                break;
            case IdiyMessage.GET_COMMENT_ACTION:
                List<RProductComment> dats = loadComment((Long) values[0],
                        (Integer) values[1]);
                mListener
                        .onModeChaned(IdiyMessage.GET_COMMENT_ACTION_RESULT, dats);
                break;
            case IdiyMessage.ADD2SHOPCAR_ACTION:
                RResult resultBean = add2shopcar((Long) values[0],
                        (Integer) values[1], (String) values[2]);
                mListener.onModeChaned(IdiyMessage.ADD2SHOPCAR_ACTION_RESULT,
                        resultBean);
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
    private RResult add2shopcar(long pid, int buyCount, String pversion) {
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("userId", mUserId + "");
        params.put("productId", pid + "");
        params.put("buyCount", buyCount + "");
        params.put("pversion", pversion);
        String jsonStr = NetworkUtil.doPost(NetworkConst.TOSHOPCAR_URL, params);
        return JSON.parseObject(jsonStr, RResult.class);
    }

    private List<RProductComment> loadComment(long pid, int type) {
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("productId", pid + "");
        if (type == 4) {// 希望有图=全部评论+hasImgCom
            params.put("type", ProductCommentFragment.ALL_COMMENT + "");
            // hasImgCom: required (boolean) 如果需要有图的评论才添加 如果没有则不用添加 比如选中好评
            params.put("hasImgCom", "true");
        } else {
            params.put("type", type + "");
        }
        String jsonStr = NetworkUtil.doPost(NetworkConst.COMMENTDETAIL_URL,
                params);
        RResult resultBean = JSON.parseObject(jsonStr, RResult.class);
        if (resultBean.isSuccess()) {
            return JSON.parseArray(resultBean.getResult(),
                    RProductComment.class);
        }
        return new ArrayList<RProductComment>();
    }
    private RCommentCount loadCommentCount(long pid) {
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("productId", pid + "");
        String jsonStr = NetworkUtil.doPost(NetworkConst.COMMENTCOUNT_URL,
                params);
        RResult resultBean = JSON.parseObject(jsonStr, RResult.class);
        if (resultBean.isSuccess()) {
            return JSON
                    .parseObject(resultBean.getResult(), RCommentCount.class);
        }
        return null;
    }

    private List<RGoodComment> loadGoodComment(long pid) {
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("productId", pid + "");
        params.put("type", "1");
        String jsonStr = NetworkUtil.doPost(NetworkConst.PRODUCTCOMMENT_URL,
                params);
        RResult resultBean = JSON.parseObject(jsonStr, RResult.class);
        if (resultBean.isSuccess()) {
            return JSON.parseArray(resultBean.getResult(), RGoodComment.class);
        }
        return new ArrayList<RGoodComment>();
    }
}
