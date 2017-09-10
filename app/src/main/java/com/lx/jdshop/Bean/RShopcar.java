package com.lx.jdshop.Bean;

/**
 * Created by Xia_焱 on 2017/9/10.
 */

public class RShopcar {
    private long id;//购物车明细id
    private long pid;
    private String pname;
    private String pimageUrl;
    private double pprice;
    private int buyCount;
    private String pversion;
    private int stockCount;

    private long storeId;
    private String storeName;
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public long getPid() {
        return pid;
    }
    public void setPid(long pid) {
        this.pid = pid;
    }
    public String getPname() {
        return pname;
    }
    public void setPname(String pname) {
        this.pname = pname;
    }
    public String getPimageUrl() {
        return pimageUrl;
    }
    public void setPimageUrl(String pimageUrl) {
        this.pimageUrl = pimageUrl;
    }
    public double getPprice() {
        return pprice;
    }
    public void setPprice(double pprice) {
        this.pprice = pprice;
    }
    public int getBuyCount() {
        return buyCount;
    }
    public void setBuyCount(int buyCount) {
        this.buyCount = buyCount;
    }
    public String getPversion() {
        return pversion;
    }
    public void setPversion(String pversion) {
        this.pversion = pversion;
    }
    public int getStockCount() {
        return stockCount;
    }
    public void setStockCount(int stockCount) {
        this.stockCount = stockCount;
    }
    public long getStoreId() {
        return storeId;
    }
    public void setStoreId(long storeId) {
        this.storeId = storeId;
    }
    public String getStoreName() {
        return storeName;
    }
    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }
}
