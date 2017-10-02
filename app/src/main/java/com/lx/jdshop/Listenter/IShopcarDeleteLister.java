package com.lx.jdshop.Listenter;

/**
 * Created by Xia_焱 on 2017/10/2.
 */

public interface IShopcarDeleteLister {
    //被删除的购物车表的ID
    void onItemDelete(long ShopCarId);
}
