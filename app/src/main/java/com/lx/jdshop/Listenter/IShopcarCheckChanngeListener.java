package com.lx.jdshop.Listenter;

/**
 * Created by Xia_焱 on 2017/9/10.
 */

public interface IShopcarCheckChanngeListener {

    void onBuyCountChanged(int count);

    void onTotalPriceChanged(double newestPrice);
}
