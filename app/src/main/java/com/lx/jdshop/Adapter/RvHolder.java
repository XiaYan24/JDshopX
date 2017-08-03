package com.lx.jdshop.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Xia_焱 on 2017/7/14.
 */

public abstract class RvHolder<T> extends RecyclerView.ViewHolder{
    private RvListener mListener;

    public RvHolder(View itemView, int type, RvListener listener) {
        super(itemView);
        this.mListener = listener;
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onItemClick(view.getId(),getAdapterPosition());
            }
        });
    }
    public abstract void bindHolder(T t, int position, Context ctx);
}
