package com.lx.jdshop.UI;

import android.content.Context;
import android.view.View;
import android.widget.PopupWindow;

/**
 * Created by Xia_焱 on 2017/7/24.
 */

public abstract class BasePopupWindow {
    protected PopupWindow popupWindow;
    protected Context mContext;

    public BasePopupWindow(Context c) {
        mContext=c;
        initUI();
    }

    protected abstract void initUI();

    public abstract void onShow(View anchor);

    public void onDismiss(){
        if (popupWindow!=null&&popupWindow.isShowing()) {
            popupWindow.dismiss();
        }
    }
}
