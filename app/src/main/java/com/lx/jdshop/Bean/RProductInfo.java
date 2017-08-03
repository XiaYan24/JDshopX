package com.lx.jdshop.Bean;

public class RProductInfo {

	private long id;
	private String name;
	private String imgUrls;
	private double price;
	private boolean ifSaleOneself;
	private String typeList;
	private int stockCount;
	
	private int commentCount;
	private int favcomRate;
    private long recomProductId;
    private String recomProduct;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImgUrls() {
		return imgUrls;
	}
	public void setImgUrls(String imgUrls) {
		this.imgUrls = imgUrls;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public boolean isIfSaleOneself() {
		return ifSaleOneself;
	}
	public void setIfSaleOneself(boolean ifSaleOneself) {
		this.ifSaleOneself = ifSaleOneself;
	}
	public String getTypeList() {
		return typeList;
	}
	public void setTypeList(String typeList) {
		this.typeList = typeList;
	}
	public int getStockCount() {
		return stockCount;
	}
	public void setStockCount(int stockCount) {
		this.stockCount = stockCount;
	}
	public int getCommentCount() {
		return commentCount;
	}
	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}
	public int getFavcomRate() {
		return favcomRate;
	}
	public void setFavcomRate(int favcomRate) {
		this.favcomRate = favcomRate;
	}
	public long getRecomProductId() {
		return recomProductId;
	}
	public void setRecomProductId(long recomProductId) {
		this.recomProductId = recomProductId;
	}
	public String getRecomProduct() {
		return recomProduct;
	}
	public void setRecomProduct(String recomProduct) {
		this.recomProduct = recomProduct;
	}
    
}
