package com.lx.jdshop.Fragment;

import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.lx.jdshop.Adapter.ShopCarAdapter;
import com.lx.jdshop.Bean.RShopcar;
import com.lx.jdshop.Listenter.IShopcarCheckChanngeListener;
import com.lx.jdshop.R;
import com.lx.jdshop.UI.FlexiListView;
import com.lx.jdshop.cons.IdiyMessage;
import com.lx.jdshop.controller.ShopCarController;

import java.util.List;

/**
 * Created by Xia_焱 on 2017/8/20.
 */

public class ShopCarFragment extends BaseFragment implements AdapterView.OnItemClickListener,IShopcarCheckChanngeListener {

    private FlexiListView mShopCarLv;
    private ShopCarAdapter mShopCarAdapter;
    private ShopCarController mController;
    private TextView mSettle;

    @Override
    protected void handlerMessage(Message msg) {
        switch (msg.what){
            case IdiyMessage.SHOPCAR_LIST_ACTION_RESULT:
                handLoadShopCar((List<RShopcar>)msg.obj);
                break;
        }
    }

    private void handLoadShopCar(List<RShopcar> data) {
        mShopCarAdapter.setDatas(data);
        mShopCarAdapter.notifyDataSetChanged();
    }

    @Override
    public View initView(LayoutInflater inflater) {
        view = inflater.inflate(R.layout.fragmet_shopcar, null);
        initUI(view);
        initData();
        return view;
    }

    private void initUI(View view) {
        mShopCarLv = (FlexiListView) view.findViewById(R.id.shop_car_lv);
        mSettle = (TextView) view.findViewById(R.id.settle_tv);
        ImageView id_back = (ImageView) view.findViewById(R.id.id_back);
        id_back.setVisibility(View.GONE);
        mShopCarAdapter = new ShopCarAdapter(getActivity());
        mShopCarAdapter.setIShopcarCheckChanngeListener(this);
        mShopCarLv.setAdapter(mShopCarAdapter);
        mShopCarLv.setOnItemClickListener(this);
    }

    @Override
    protected void initData() {
        mController = new ShopCarController(getActivity());
        mController.setIModeChangeListener(this);
        mController.sendAsyncMessage(IdiyMessage.SHOPCAR_LIST_ACTION,0);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        mShopCarAdapter.setCheck(position);
    }

    @Override
    public void onBuyCountChanged(int count) {
        //修改
        mSettle.setText("去结算 ("+count+")");
    }

    @Override
    public void onTotalPriceChanged(double newestPrice) {

    }
}
