package com.lx.jdshop.Adapter;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lx.jdshop.Bean.RTopCategory;
import com.lx.jdshop.R;


public class TopCategoryAdapter extends JDBaseAdapter<RTopCategory> {

	public int mCurrentTabPosition=-1;

	public TopCategoryAdapter(Context c) {
		super(c);
	}

	class ViewHolder{
		View dividerView;
		TextView nameTv;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holer=null;
		if (convertView==null) {
			convertView=mInflater.inflate(R.layout.top_category_item, parent,false);
			holer=new ViewHolder();
			holer.dividerView=convertView.findViewById(R.id.divider);
			holer.nameTv=(TextView) convertView.findViewById(R.id.tv);
			convertView.setTag(holer);
		}else {
			holer=(ViewHolder) convertView.getTag();
		}
		RTopCategory bean = mDatas.get(position);
		holer.nameTv.setText(bean.getName());
		//2.修改样式
		if (position==mCurrentTabPosition) {
			holer.nameTv.setSelected(true);
			holer.nameTv.setBackgroundResource(R.mipmap.tongcheng_all_bg01);
			holer.dividerView.setVisibility(View.INVISIBLE);
		}else {
			holer.nameTv.setSelected(false);
			holer.nameTv.setBackgroundColor(0xFFFAFAFA);
			holer.dividerView.setVisibility(View.VISIBLE);
		}
		return convertView;
	}

	@Override
	public Object getItem(int position) {
		return mDatas!=null?mDatas.get(position):null;
	}

}
