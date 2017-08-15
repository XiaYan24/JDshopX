package com.lx.jdshop.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Xia_焱 on 2017/7/21.
 */


public abstract class RecyclerBaseAdapter<T,VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> implements View.OnClickListener, View.OnLongClickListener {

    protected Context context;
    protected List<T> list;
    protected LayoutInflater inflater;
    private OnItemClickListener onItemClickListener;
    private OnItemLongClickListener onItemLongClickListener;

    public RecyclerBaseAdapter(Context context) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        list = new ArrayList<>();
    }

    public void setList(List<T> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public void addBottom(List<T> list, boolean isClean){
        if (isClean){
            this.list=list;
        } else {
            if (this.list!=null){
                this.list.addAll(list);
            }
        }
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }

    @Override
    public abstract VH onCreateViewHolder(ViewGroup parent, int viewType);

    @Override
    public void onBindViewHolder(VH holder, int position) {
        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(this);
        holder.itemView.setOnLongClickListener(this);
        if (position < list.size()) {
            onBindViewHolder(holder, list.get(position), position);
        } else {
            onBindViewHolder(holder,null,position);
        }
    }

    public abstract void onBindViewHolder(VH holder, T item, int position);

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }


    public T getItem(int pos) {
        return list.get(pos);
    }

    @Override
    public void onClick(View v) {
        int pos = (int) v.getTag();
        if (onItemClickListener != null) {
            onItemClickListener.onItemClick(pos);
        }

    }

    @Override
    public boolean onLongClick(View v) {
        int pos = (int) v.getTag();
        if (onItemLongClickListener != null){
            onItemLongClickListener.onItemLongClick(pos);
        }
        return true;
    }

    public interface OnItemClickListener {
        void onItemClick(int pos);
    }
    public interface OnItemLongClickListener{
        void onItemLongClick(int pos);
    }

}
