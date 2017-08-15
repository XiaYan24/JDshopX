package com.lx.jdshop.Bean;

/**
 * Created by Xia_焱 on 2017/8/14.
 */

public class RProductComment {
    private long id;
    private int rate;//星星数
    private String comment;
    private String imgUrls;//JSON
    private String buyTime;
    private String productType;

    private int userLevel;
    private String userName;
    private String userImg;
    private String commentTime;

    private int loveCount;//喜欢数
    private int subComment;//回复评论数
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public int getRate() {
        return rate;
    }
    public void setRate(int rate) {
        this.rate = rate;
    }
    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
    public String getImgUrls() {
        return imgUrls;
    }
    public void setImgUrls(String imgUrls) {
        this.imgUrls = imgUrls;
    }
    public String getBuyTime() {
        return buyTime;
    }
    public void setBuyTime(String buyTime) {
        this.buyTime = buyTime;
    }
    public String getProductType() {
        return productType;
    }
    public void setProductType(String productType) {
        this.productType = productType;
    }
    public int getUserLevel() {
        return userLevel;
    }
    public void setUserLevel(int userLevel) {
        this.userLevel = userLevel;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getUserImg() {
        return userImg;
    }
    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }
    public String getCommentTime() {
        return commentTime;
    }
    public void setCommentTime(String commentTime) {
        this.commentTime = commentTime;
    }
    public int getLoveCount() {
        return loveCount;
    }
    public void setLoveCount(int loveCount) {
        this.loveCount = loveCount;
    }
    public int getSubComment() {
        return subComment;
    }
    public void setSubComment(int subComment) {
        this.subComment = subComment;
    }
}
