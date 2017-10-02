package com.lx.jdshop.Fragment;

import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lx.jdshop.Activity.SettleActivity;
import com.lx.jdshop.Adapter.ShopCarAdapter;
import com.lx.jdshop.Bean.RResult;
import com.lx.jdshop.Bean.RShopcar;
import com.lx.jdshop.Listenter.CallbackState;
import com.lx.jdshop.Listenter.IShopcarCheckChanngeListener;
import com.lx.jdshop.Listenter.IShopcarDeleteLister;
import com.lx.jdshop.R;
import com.lx.jdshop.UI.FlexiListView;
import com.lx.jdshop.Util.ActivityUtil;
import com.lx.jdshop.cons.IdiyMessage;
import com.lx.jdshop.controller.ShopCarController;

import java.util.List;

/**
 * Created by Xia_焱 on 2017/8/20.
 */

public class ShopCarFragment extends BaseFragment implements AdapterView.OnItemClickListener, IShopcarCheckChanngeListener, CompoundButton.OnCheckedChangeListener, IShopcarDeleteLister, CallbackState, View.OnClickListener {

    private FlexiListView mShopCarLv;
    private ShopCarAdapter mShopCarAdapter;
    private ShopCarController mController;
    private TextView mSettle;
    private TextView mAllMoneyTv;
    private CheckBox mAllCbx;

    @Override
    protected void handlerMessage(Message msg) {
        switch (msg.what) {
            case IdiyMessage.SHOPCAR_LIST_ACTION_RESULT:
                handLoadShopCar((List<RShopcar>) msg.obj);
                break;
            case IdiyMessage.DELET_SHOPCAR_ACTION_RESULT:
                handDeletShopCar((RResult) msg.obj);
                break;
        }
    }

    private void handDeletShopCar(RResult obj) {
        if (obj.isSuccess()) {
            mController.sendAsyncMessage(IdiyMessage.SHOPCAR_LIST_ACTION, 0);
        } else {
            Toast.makeText(getContext(), "刪除失敗", Toast.LENGTH_SHORT).show();
        }
    }


    private void handLoadShopCar(List<RShopcar> data) {
        mShopCarAdapter.setDatas(data);
        mShopCarAdapter.notifyDataSetChanged();

        mSettle.setText("去结算(0)");
        mAllMoneyTv.setText("总额: ￥ 0");
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
        mSettle.setOnClickListener(this);
        mAllMoneyTv = (TextView) view.findViewById(R.id.all_money_tv);
        mAllCbx = (CheckBox) view.findViewById(R.id.all_cbx);
        mAllCbx.setOnCheckedChangeListener(this);
        ImageView id_back = (ImageView) view.findViewById(R.id.id_back);
        id_back.setVisibility(View.GONE);
        mShopCarAdapter = new ShopCarAdapter(getActivity());
        mShopCarAdapter.setIShopcarCheckChanngeListener(this);
        mShopCarAdapter.setIShopcarDeleteLister(this);
        mShopCarAdapter.SetCallbackStateLister(this);
        mShopCarLv.setAdapter(mShopCarAdapter);
        mShopCarLv.setOnItemClickListener(this);
    }

    @Override
    protected void initData() {
        mController = new ShopCarController(getActivity());
        mController.setIModeChangeListener(this);
        mController.sendAsyncMessage(IdiyMessage.SHOPCAR_LIST_ACTION, 0);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        mShopCarAdapter.setCheck(position);
    }

    @Override
    public void onBuyCountChanged(int count) {
        //修改
        mSettle.setText("去结算 (" + count + ")");
    }

    @Override
    public void onTotalPriceChanged(double newestPrice) {
        //修改总金额
        mAllMoneyTv.setText("总额: ￥" + newestPrice);
    }

    //全选按钮的监听器
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        mShopCarAdapter.checkAll(isChecked);
    }

    @Override
    public void onItemDelete(long ShopCarId) {
        //发送一个删除购物车的操作
        mController.sendAsyncMessage(IdiyMessage.DELET_SHOPCAR_ACTION, ShopCarId);
    }

    @Override
    public void onCheckBoxState(boolean state) {
        mAllCbx.setChecked(state);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.settle_tv:
                //判断是否选中
                if (!mShopCarAdapter.ifItemChecked()) {
                    Toast.makeText(getContext(), "请选择购买的商品", Toast.LENGTH_SHORT).show();
                    return;
                }
                ActivityUtil.start(getActivity(), SettleActivity.class, false);
                break;
        }
    }
}
