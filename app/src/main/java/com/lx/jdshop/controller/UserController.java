package com.lx.jdshop.controller;

import android.content.Context;


import com.alibaba.fastjson.JSON;
import com.lx.jdshop.Bean.RResult;
import com.lx.jdshop.DB.UserDao;
import com.lx.jdshop.Listenter.IModeChaneListener;
import com.lx.jdshop.Util.AESUtils;
import com.lx.jdshop.Util.NetworkUtil;
import com.lx.jdshop.cons.IdiyMessage;
import com.lx.jdshop.cons.NetworkConst;

import java.util.HashMap;

/**
 * Created by Xia_焱 on 2017/7/19.
 */

public class UserController extends BaseController {

    protected IModeChaneListener mListener;
    private UserDao userDao;

    public UserController(Context ctx) {
        super(ctx);
        userDao = new UserDao(mContext);
    }

    public void setIModeChangeListener(IModeChaneListener Listener) {
        mListener = Listener;
    }

    @Override
    protected void handleMessage(int action, Object... values) {
        switch (action) {
            case IdiyMessage.LOGIN_ACTION:
                //登录请求
                RResult login = LoginOrRegist(NetworkConst.LOGIN_URL, (String) values[0], (String) values[1]);
                mListener.onModeChaned(IdiyMessage.LOGIN_ACTION_RESULT, login);
                break;
            case IdiyMessage.REGISTER_ACTION:
                RResult regist = LoginOrRegist(NetworkConst.REGIST_URL, (String) values[0], (String) values[1]);
                mListener.onModeChaned(IdiyMessage.REGISTER_ACTION_RESULT, regist);
                break;
            case IdiyMessage.SAVE_USERTODB:
                boolean b = SaveUserToDb((String) values[0], (String) values[1]);
                mListener.onModeChaned(IdiyMessage.SAVE_USERTODB_RESULT, b);
                break;
            case IdiyMessage.GET_USER_ACTION:
                UserDao.UserInfo userInfo = aquireUser();
                mListener.onModeChaned(IdiyMessage.GET_USER_ACTION_RESULT, userInfo);
                break;
            case IdiyMessage.CLEAR_USER_ACTION:
                ClearUser();
                mListener.onModeChaned(IdiyMessage.CLEAR_USER_ACTION_RESULT,0);
                break;
        }
    }

    //清空数据
    private void ClearUser() {
        userDao.ClearUser();
    }

    //查找 账号
    private UserDao.UserInfo aquireUser() {
        UserDao.UserInfo userInfo = userDao.LastUsers();
        if (userInfo != null) {
            try {
                userInfo.name = AESUtils.decrypt(userInfo.getName());
                userInfo.pwd = AESUtils.decrypt(userInfo.getPwd());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return userInfo;
        }
        return null;

    }

    //保存 账号
    private boolean SaveUserToDb(String name, String pwd) {

        userDao.ClearUser();
        try {
            name = AESUtils.encrypt(name);
            pwd = AESUtils.encrypt(pwd);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userDao.SaveUser(name, pwd);
    }

    //登录
    private RResult LoginOrRegist(String url, String name, String pwd) {
        HashMap<String, String> map = new HashMap<>();
        map.put("username", name);
        map.put("pwd", pwd);
        String jsonStr = NetworkUtil.doPost(url, map);
        return JSON.parseObject(jsonStr, RResult.class);
    }

}
