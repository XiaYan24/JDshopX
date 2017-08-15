package com.lx.jdshop.Adapter;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.lx.jdshop.Bean.RProductComment;
import com.lx.jdshop.R;
import com.lx.jdshop.Util.RatingBar;
import com.lx.jdshop.cons.NetworkConst;

import java.util.List;

import image.SmartImageView;

/**
 * Created by Xia_焱 on 2017/8/14.
 */

public class CommentAdapter extends JDBaseAdapter<RProductComment> {

    public CommentAdapter(Context c) {
        super(c);
    }
    class ViewHolder{
        SmartImageView userIconIv;
        TextView userNameTv;
        TextView commentTimeTv;
        RatingBar commentLevelRb;
        TextView commentTv;
        TextView buyTimeTv;
        TextView buyVersionTv;
        LinearLayout imageContainerLl;
        TextView lovecountTv;
        TextView subcommentTv;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holer=null;
        if (convertView==null) {
            convertView=mInflater.inflate(R.layout.comment_item_view, parent,false);
            holer=new ViewHolder();
            holer.userIconIv=(SmartImageView) convertView.findViewById(R.id.icon_iv);
            holer.userNameTv=(TextView) convertView.findViewById(R.id.name_tv);
            holer.commentTimeTv=(TextView) convertView.findViewById(R.id.time_tv);
            holer.commentLevelRb=(RatingBar) convertView.findViewById(R.id.rating_bar);
            holer.commentTv=(TextView) convertView.findViewById(R.id.content_tv);
            holer.buyTimeTv=(TextView) convertView.findViewById(R.id.buytime_tv);
            holer.buyVersionTv=(TextView) convertView.findViewById(R.id.buyversion_tv);
            holer.imageContainerLl=(LinearLayout) convertView.findViewById(R.id.iamges_container);
            holer.lovecountTv=(TextView) convertView.findViewById(R.id.lovecount_tv);
            holer.subcommentTv=(TextView) convertView.findViewById(R.id.subcomment_tv);

            convertView.setTag(holer);
        }else {
            holer=(ViewHolder) convertView.getTag();
        }
        RProductComment bean = mDatas.get(position);
        holer.userIconIv.setImageUrl(NetworkConst.BASE_URL+bean.getUserImg());
        holer.userNameTv.setText(bean.getUserName());
        holer.commentTimeTv.setText(bean.getCommentTime());
        holer.commentLevelRb.setRating(bean.getRate());
        holer.commentTv.setText(bean.getComment());
        holer.buyTimeTv.setText("购买时间:"+bean.getBuyTime());
        holer.buyVersionTv.setText("型号:"+bean.getProductType());

        initImageContainer(holer.imageContainerLl, bean.getImgUrls());

        holer.lovecountTv.setText("喜欢("+bean.getLoveCount()+")");
        holer.subcommentTv.setText("回复("+bean.getSubComment()+")");

        return convertView;
    }

    private void initImageContainer(LinearLayout containerLl, String imageUrlJson) {
        // 1.知道容器 holer.imageContainerLl
        // 2.知道容器里面到底要添加多少内容吗?
        // 如果放回的数据是3: 显示3
        // 如果放回的数据是5: 显示4
        int childCount = containerLl.getChildCount();
        List<String> imgUrls = JSON.parseArray(imageUrlJson, String.class);
        int dataSize = imgUrls.size();
        int realSize = Math.min(childCount, dataSize);
        // 清空老数据
        for (int i = 0; i < childCount; i++) {
            SmartImageView smiv = (SmartImageView) containerLl.getChildAt(i);
            smiv.setImageDrawable(new BitmapDrawable());
        }
        // 设置新的数据
        for (int i = 0; i < realSize; i++) {
            SmartImageView smiv = (SmartImageView) containerLl.getChildAt(i);
            smiv.setImageUrl(NetworkConst.BASE_URL + imgUrls.get(i));
        }
        containerLl.setVisibility(realSize > 0 ? View.VISIBLE
                : View.GONE);
    }
}