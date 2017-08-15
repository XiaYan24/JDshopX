package com.lx.jdshop.Bean;

/**
 * Created by Xia_焱 on 2017/8/14.
 */

public class RGoodComment {

    private String imgUrls;//JSON
    private String time;//评论时间
    private String userName;//评论用户名
    private int rate;//星星数 最多5个
    private int type;//评论类型
    private String comment;//评论内容

    public String getImgUrls() {
        return imgUrls;
    }

    public void setImgUrls(String imgUrls) {
        this.imgUrls = imgUrls;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
