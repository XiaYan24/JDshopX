package com.lx.jdshop.Adapter;

import java.util.List;

import android.content.Context;

import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.lx.jdshop.Bean.RGoodComment;
import com.lx.jdshop.R;
import com.lx.jdshop.Util.RatingBar;
import com.lx.jdshop.cons.NetworkConst;

import image.SmartImageView;


public class GoodCommentAdapter extends JDBaseAdapter<RGoodComment> {

	public GoodCommentAdapter(Context c) {
		super(c);
	}

	class ViewHolder {
		RatingBar commentLevelRb;
		TextView nameTv;
		TextView commentContentTv;
		LinearLayout imageContainerLl;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holer = null;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.good_comment_item_view,
					parent, false);
			holer = new ViewHolder();
			holer.commentLevelRb = (RatingBar) convertView
					.findViewById(R.id.rating_bar);
			holer.nameTv = (TextView) convertView.findViewById(R.id.name_tv);
			holer.commentContentTv = (TextView) convertView
					.findViewById(R.id.content_tv);
			holer.imageContainerLl = (LinearLayout) convertView
					.findViewById(R.id.iamges_container);
			convertView.setTag(holer);
		} else {
			holer = (ViewHolder) convertView.getTag();
		}
		RGoodComment bean = mDatas.get(position);
		holer.commentLevelRb.setRating(bean.getRate());
		holer.nameTv.setText(bean.getUserName());
		holer.commentContentTv.setText(bean.getComment());

		initImageContainer(holer, bean);
		return convertView;
	}

	private void initImageContainer(ViewHolder holer, RGoodComment bean) {
		// 1.知道容器 holer.imageContainerLl
		// 2.知道容器里面到底要添加多少内容吗?
		// 如果放回的数据是3: 显示3
		// 如果放回的数据是5: 显示4
		int childCount = holer.imageContainerLl.getChildCount();
		List<String> imgUrls = JSON.parseArray(bean.getImgUrls(), String.class);
		int dataSize = imgUrls.size();
		int realSize = Math.min(childCount, dataSize);
		// 清空老数据
		for (int i = 0; i < childCount; i++) {
			SmartImageView smiv = (SmartImageView) holer.imageContainerLl
					.getChildAt(i);
			smiv.setImageDrawable(new BitmapDrawable());
		}
		// 设置新的数据
		for (int i = 0; i < realSize; i++) {
			SmartImageView smiv = (SmartImageView) holer.imageContainerLl
					.getChildAt(i);
			smiv.setImageUrl(NetworkConst.BASE_URL + imgUrls.get(i));
		}
		holer.imageContainerLl.setVisibility(realSize > 0 ? View.VISIBLE
				: View.GONE);
	}

}
