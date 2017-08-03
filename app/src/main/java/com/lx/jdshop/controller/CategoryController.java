package com.lx.jdshop.controller;

import android.content.Context;


import com.alibaba.fastjson.JSON;
import com.lx.jdshop.Bean.RBrand;
import com.lx.jdshop.Bean.RProductList;
import com.lx.jdshop.Bean.RResult;
import com.lx.jdshop.Bean.RSubCategory;
import com.lx.jdshop.Bean.RTopCategory;
import com.lx.jdshop.Bean.SProductList;
import com.lx.jdshop.Util.NetworkUtil;
import com.lx.jdshop.cons.IdiyMessage;
import com.lx.jdshop.cons.NetworkConst;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CategoryController extends BaseController {

	public CategoryController(Context ctx) {
		super(ctx);
	}

	@Override
	protected void handleMessage(int action, Object... values) {
		switch (action) {
			case IdiyMessage.TOPCATEGORY_ACTION:
				mListener.onModeChaned(IdiyMessage.TOPCATEGORY_ACTION_RESULT,
						loadTopCategory());
				break;
			case IdiyMessage.SUBCATEGORY_ACTION:
				mListener.onModeChaned(IdiyMessage.SUBCATEGORY_ACTION_RESULT,loadSubcategory((Long)values[0]));
				break;
			case IdiyMessage.BRAND_ACTION:
				mListener.onModeChaned(IdiyMessage.BRAND_ACTION_RESULT,
						loadBrand((Long) values[0]));
				break;
			case IdiyMessage.PRODUCT_LIST_ACTION:
				mListener.onModeChaned(IdiyMessage.PRODUCT_LIST_ACTION_RESULT,loadProductList((SProductList) values[0]));
				break;
		}
	}

	private List<RProductList> loadProductList(SProductList sendArgs) {
		HashMap<String, String> params = initProductListParams(sendArgs);
		String jsonStr = NetworkUtil.doPost(NetworkConst.PRODUCTLIST_URL, params);
		RResult rResultBean = JSON.parseObject(jsonStr, RResult.class);
		if (rResultBean.isSuccess()){
			try {
				JSONObject jsonObject = new JSONObject(rResultBean.getResult());
				String rowsJson = jsonObject.getString("rows");
				return JSON.parseArray(rowsJson,RProductList.class);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return new ArrayList<RProductList>();
	}

	private HashMap<String,String> initProductListParams(SProductList sendArgs) {
		HashMap<String, String> params=new HashMap<String, String>();
		params.put("categoryId", sendArgs.getCategoryId()+"");
		params.put("filterType", sendArgs.getFilterType()+"");
		if (sendArgs.getSortType()!=0) {
			params.put("sortType", sendArgs.getSortType()+"");
		}
		params.put("deliverChoose", sendArgs.getDeliverChoose()+"");
		if (sendArgs.getMinPrice()!=0&&sendArgs.getMaxPrice()!=0) {
			params.put("minPrice", sendArgs.getMinPrice()+"");
			params.put("maxPrice", sendArgs.getMaxPrice()+"");
		}
		if (sendArgs.getBrandId()!=0) {
			params.put("brandId", sendArgs.getBrandId()+"");
		}
		return params;
	}

	private List<RBrand> loadBrand(long topCategoryId){
		String jsonStr = NetworkUtil.doGet(NetworkConst.BRAND_URL+"?categoryId="+topCategoryId);
		RResult resultBean = JSON.parseObject(jsonStr,RResult.class);
		if (resultBean.isSuccess()){
			return JSON.parseArray(resultBean.getResult(),RBrand.class);
		}
		return new ArrayList<RBrand>();
	}

	private List<RSubCategory> loadSubcategory(Long parentId) {
		String jsonStr = NetworkUtil.doGet(NetworkConst.CATEGORY_URL+"?parentId="+parentId);
		RResult resultBean = JSON.parseObject(jsonStr, RResult.class);
		if (resultBean.isSuccess()) {
			return JSON.parseArray(resultBean.getResult(), RSubCategory.class);
		}
		return new ArrayList<RSubCategory>();
	}

	private List<RTopCategory> loadTopCategory() {
		String jsonStr = NetworkUtil.doGet(NetworkConst.CATEGORY_URL);
		RResult resultBean = JSON.parseObject(jsonStr, RResult.class);
		if (resultBean.isSuccess()) {
			return JSON.parseArray(resultBean.getResult(), RTopCategory.class);
		}
		return new ArrayList<RTopCategory>();
	}

}
