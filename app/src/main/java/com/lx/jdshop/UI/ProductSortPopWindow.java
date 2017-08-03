package com.lx.jdshop.UI;


import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.lx.jdshop.Listenter.IProductSortChanegListener;
import com.lx.jdshop.R;


/**
 * Created by Xia_焱 on 2017/7/24.
 */

public class ProductSortPopWindow extends BasePopupWindow implements View.OnClickListener {


    private IProductSortChanegListener mListener;

    public ProductSortPopWindow(Context c) {
        super(c);
    }

    public void setListener(IProductSortChanegListener listener) {
        mListener = listener;
    }

    @Override
    protected void initUI() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.product_sort_pop_view, null, false);

        view.findViewById(R.id.left_v).setOnClickListener(this);

        view.findViewById(R.id.all_sort).setOnClickListener(this);
        view.findViewById(R.id.new_sort).setOnClickListener(this);
        view.findViewById(R.id.comment_sort).setOnClickListener(this);
        popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.update();
    }

    @Override
    public void onShow(View anchor) {
        if (popupWindow != null) {
            popupWindow.showAsDropDown(anchor, 0, 0);
        }
    }

    @Override
    public void onClick(View v) {
        if (mListener!=null){
            switch (v.getId()) {
                case R.id.all_sort:
                    mListener.onSortChanged(IProductSortChanegListener.ALLSORT);
                    break;
                case R.id.new_sort:
                    mListener.onSortChanged(IProductSortChanegListener.NEWSSORT);
                    break;
                case R.id.comment_sort:
                    mListener.onSortChanged(IProductSortChanegListener.COMMENTSORT);
                    break;
            }
        }
        onDismiss();
    }
}
