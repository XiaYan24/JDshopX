package com.lx.jdshop.Bean;

public class RTopCategory {

    private long id;
    private String bannerUrl;
    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBannerUrl() {
        return bannerUrl;
    }

    public void setBannerUrl(String bannerUrl) {
        this.bannerUrl = bannerUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "RTopCategory{" +
                "id=" + id +
                ", bannerUrl='" + bannerUrl + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
