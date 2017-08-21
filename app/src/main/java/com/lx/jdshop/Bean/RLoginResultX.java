package com.lx.jdshop.Bean;

/**
 * Created by Xia_焱 on 2017/8/21.
 */

public class RLoginResultX {
    private long id;
    private String userName;
    private String userIcon;//头像路径
    private int waitPayCount;//待付款数
    private int waitReceiveCount;//待收货数
    private int userLevel;//1注册会员2铜牌会员3银牌会员4金牌会员5钻石会员
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getUserIcon() {
        return userIcon;
    }
    public void setUserIcon(String userIcon) {
        this.userIcon = userIcon;
    }
    public int getWaitPayCount() {
        return waitPayCount;
    }
    public void setWaitPayCount(int waitPayCount) {
        this.waitPayCount = waitPayCount;
    }
    public int getWaitReceiveCount() {
        return waitReceiveCount;
    }
    public void setWaitReceiveCount(int waitReceiveCount) {
        this.waitReceiveCount = waitReceiveCount;
    }
    public int getUserLevel() {
        return userLevel;
    }
    public void setUserLevel(int userLevel) {
        this.userLevel = userLevel;
    }

}
