package com.lx.jdshop.Bean;

/**
 * Created by Xia_焱 on 2017/7/20.
 */

public class Banner {
    private long id;
    private int type;
    private String adUrl;
    private String webUrl;
    private int adKind;
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public int getType() {
        return type;
    }
    public void setType(int type) {
        this.type = type;
    }
    public String getAdUrl() {
        return adUrl;
    }
    public void setAdUrl(String adUrl) {
        this.adUrl = adUrl;
    }
    public String getWebUrl() {
        return webUrl;
    }
    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }
    public int getAdKind() {
        return adKind;
    }
    public void setAdKind(int adKind) {
        this.adKind = adKind;
    }

}
