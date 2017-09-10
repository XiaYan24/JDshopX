package com.lx.jdshop.Adapter;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.lx.jdshop.Bean.RShopcar;
import com.lx.jdshop.Fragment.ShopCarFragment;
import com.lx.jdshop.R;
import com.lx.jdshop.cons.NetworkConst;

import java.util.ArrayList;
import java.util.List;

import image.SmartImageView;


public class ShopCarAdapter extends JDBaseAdapter<RShopcar> {

    private static ArrayList<Boolean>mItemChecked = new ArrayList<>();
    private ShopCarFragment mListener;

    @Override
    public void setDatas(List<RShopcar> datas) {
        super.setDatas(datas);
        for (int i =0;i<datas.size();i++){
            mItemChecked.add(false);
        }
    }

    public ShopCarAdapter(Context c) {
        super(c);
    }
    //设置item 是否选中
    public void setCheck(int check) {
        mItemChecked.set(check,!mItemChecked.get(check));
        notifyDataSetChanged();
        if (mListener !=null){
            int count = 0;
            for (int i =0;i<mItemChecked.size();i++){
                if (mItemChecked.get(i)){
                    count++;
                }
            }
            mListener.onBuyCountChanged(count);
        }
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.shopcar_lv_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.mItemCbxBox = (CheckBox) convertView.findViewById(R.id.cbx);
            viewHolder.smiv = (SmartImageView) convertView.findViewById(R.id.product_iv);
            viewHolder.mProductName = (TextView) convertView.findViewById(R.id.name_tv);
            viewHolder.productVersionTv = (TextView) convertView.findViewById(R.id.pversion_tv);
            viewHolder.pPriceTv = (TextView) convertView.findViewById(R.id.price_tv);
            viewHolder.buyCountTv = (TextView) convertView.findViewById(R.id.buyCount_tv);
            viewHolder.deleteBtn = (Button) convertView.findViewById(R.id.delete_product);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        RShopcar bean = mDatas.get(position);
        viewHolder.smiv.setImageUrl(NetworkConst.BASE_URL+bean.getPimageUrl());
        viewHolder.mProductName.setText(bean.getPname());
        viewHolder.productVersionTv.setText(bean.getPversion());
        viewHolder.pPriceTv.setText(" ¥ "+bean.getPprice());
        viewHolder.buyCountTv.setText("x "+bean.getBuyCount());
        viewHolder.mItemCbxBox.setChecked(mItemChecked.get(position));
        return convertView;
    }

    public void setIShopcarCheckChanngeListener(ShopCarFragment Listener) {
        this.mListener = Listener;
    }

    class ViewHolder {
        CheckBox mItemCbxBox;
        SmartImageView smiv;
        TextView mProductName;
        TextView productVersionTv;
        TextView pPriceTv;
        TextView buyCountTv;
        Button deleteBtn;
    }

}
