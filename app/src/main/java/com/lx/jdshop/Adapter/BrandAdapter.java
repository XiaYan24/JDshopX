package com.lx.jdshop.Adapter;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.lx.jdshop.Bean.RBrand;
import com.lx.jdshop.R;


public class BrandAdapter extends JDBaseAdapter<RBrand> {

    public int mCurrentTabPosition = 1;

    public BrandAdapter(Context c) {
        super(c);
    }

    class ViewHolder {
        Button BrandBtn;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView brandBtn;
        if (convertView==null) {
            convertView=mInflater.inflate(R.layout.brand_lv_item_tv, parent,false);
            brandBtn=(TextView) convertView.findViewById(R.id.brand_tv);
            convertView.setTag(brandBtn);
        }else {
            brandBtn=(TextView) convertView.getTag();
        }
        RBrand bean = mDatas.get(position);
        brandBtn.setText(bean.getName());
        //2.修改样式
        brandBtn.setSelected(position==mCurrentTabPosition);
        return convertView;
    }
    @Override
    public Object getItem(int position) {
        return mDatas!=null?mDatas.get(position):null;
    }

    @Override
    public long getItemId(int position) {
        return mDatas!=null?mDatas.get(position).getId():0;
    }

}
