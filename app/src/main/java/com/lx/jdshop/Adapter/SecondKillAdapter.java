package com.lx.jdshop.Adapter;


import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lx.jdshop.Bean.RSecondKill;
import com.lx.jdshop.R;
import com.lx.jdshop.UI.RoundImageView;
import com.lx.jdshop.cons.NetworkConst;

import java.util.List;

/**
 * Created by Xia_焱 on 2017/8/14.
 */

public class SecondKillAdapter extends RecyclerView.Adapter<SecondKillAdapter.ViewHolderX> {

    private Context mContext;
    private List<RSecondKill> mList;

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public SecondKillAdapter(Context context, List<RSecondKill> list) {
        this.mContext = context;
        this.mList = list;
    }

    @Override
    public ViewHolderX onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.home_seckill_item, parent, false);
        ViewHolderX holder = new ViewHolderX(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolderX holder, int position) {
        Glide.with(mContext).load(NetworkConst.BASE_URL + mList.get(position).getIconUrl()).placeholder(R.mipmap.ic_loading_22_s).into(holder.image_iv);
        holder.nowprice_tv.setText("¥ " + mList.get(position).getPointPrice());
        holder.normalprice_tv.setText("¥ " + mList.get(position).getAllPrice());
        // 如果设置了回调，则设置点击事件
        if (mOnItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickListener.onItemClick(holder.itemView, pos);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mList != null ? mList.size() : 0;
    }

    @Override
    public long getItemId(int position) {
        return mList != null ? mList.get(position).getProductId() : 0;
    }

    public class ViewHolderX extends RecyclerView.ViewHolder {
        private RoundImageView image_iv;
        private TextView nowprice_tv;
        private TextView normalprice_tv;

        public ViewHolderX(View itemView) {
            super(itemView);
            image_iv = (RoundImageView) itemView.findViewById(R.id.image_iv);
            nowprice_tv = (TextView) itemView.findViewById(R.id.nowprice_tv);
            normalprice_tv = (TextView) itemView.findViewById(R.id.normalprice_tv);
        }
    }

}
