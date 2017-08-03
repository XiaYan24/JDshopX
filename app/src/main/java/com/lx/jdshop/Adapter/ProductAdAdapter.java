package com.lx.jdshop.Adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.lx.jdshop.cons.NetworkConst;

import java.util.ArrayList;
import java.util.List;

import image.SmartImageView;

public class ProductAdAdapter extends PagerAdapter {
    private List<String> mImageUrList;
    private ArrayList<SmartImageView> smartImageViews;

    public void setDatas(Context ctx, List<String> imageUrList) {
        this.mImageUrList = imageUrList;
        smartImageViews = new ArrayList<>();
        for (int i = 0; i < imageUrList.size(); i++) {
            SmartImageView smartImageView = new SmartImageView(ctx);
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            smartImageView.setLayoutParams(params);
            smartImageView.setImageUrl(NetworkConst.BASE_URL + mImageUrList.get(i));
            smartImageViews.add(smartImageView);
        }
    }

    @Override
    public int getCount() {
        return smartImageViews != null ? smartImageViews.size() : 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(smartImageViews.get(position));
        return smartImageViews.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(smartImageViews.get(position));
    }
}
