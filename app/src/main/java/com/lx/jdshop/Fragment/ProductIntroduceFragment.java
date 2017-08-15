package com.lx.jdshop.Fragment;

import android.os.Message;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;


import com.alibaba.fastjson.JSON;
import com.lx.jdshop.Activity.ProductDetailsActivity;
import com.lx.jdshop.Adapter.ProductAdAdapter;
import com.lx.jdshop.Adapter.TypeListAdapter;
import com.lx.jdshop.Bean.RProductInfo;
import com.lx.jdshop.R;
import com.lx.jdshop.UI.NumberInputView;
import com.lx.jdshop.Util.FixedViewUtil;
import com.lx.jdshop.cons.IdiyMessage;
import com.lx.jdshop.controller.ProductDetailsController;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Xia_焱 on 2017/7/25.
 */

public class ProductIntroduceFragment extends BaseFragment implements AdapterView.OnItemClickListener {
    private ViewPager mAdVp;
    private ProductAdAdapter mAdAdapter;
    private ProductDetailsController mController;
    private ProductDetailsActivity mActivity;
    private TextView mAdIndicator;
    private Timer mTimer;
    private TextView mProductNameTv;
    private TextView mSelfSaleTv;
    private TextView mRecommendProductTv;
    private TextView mGoodCommentRateTv;
    private TextView mGoodCommentTv;
    private GridView mTypeListLv;
    private TypeListAdapter mTypeListAdapter;
    private NumberInputView mNumberInputView;

    @Override
    protected void handlerMessage(Message msg) {
        switch (msg.what) {
            case IdiyMessage.PRODUCT_INFO_ACTION_RESULT:
                handleProductInfo(msg.obj);
                break;
        }
    }


    @Override
    public View initView(LayoutInflater inflater) {
        view = inflater.inflate(R.layout.fragment_product_introduce, null);
        mActivity = (ProductDetailsActivity) getActivity();
        initData();
        initUI(view);
        return view;
    }

    @Override
    protected void initData() {
        mController = new ProductDetailsController(getActivity());
        mController.setIModeChangeListener(this);
        mController.sendAsyncMessage(IdiyMessage.PRODUCT_INFO_ACTION, mActivity.mProductID);
    }


    private void initUI(View view) {
        mAdVp = (ViewPager) view.findViewById(R.id.advp);
        mAdAdapter = new ProductAdAdapter();
        mAdVp.setAdapter(mAdAdapter);
        mAdIndicator = (TextView) view.findViewById(R.id.vp_number_x);
        mProductNameTv = (TextView) view.findViewById(R.id.name_tv);
        mSelfSaleTv = (TextView) view.findViewById(R.id.self_sale_tv);
        mRecommendProductTv = (TextView) view.findViewById(R.id.recommend_p_tv);
        mGoodCommentRateTv = (TextView) view.findViewById(R.id.good_rate_tv);
        mGoodCommentTv = (TextView) view.findViewById(R.id.good_comment_tv);
        mTypeListLv = (GridView) view.findViewById(R.id.product_versions_lv);
        mTypeListAdapter = new TypeListAdapter(getActivity());
        mTypeListLv.setAdapter(mTypeListAdapter);
        mTypeListLv.setOnItemClickListener(this);
        mNumberInputView = (NumberInputView) view.findViewById(R.id.number_input_et);

//        mGoodCommentLv = (ListView) view.findViewById(R.id.good_comment_lv);
//        mGoodCommentAdapter = new GoodCommentAdapter(getActivity());
//        mGoodCommentLv.setAdapter(mGoodCommentAdapter);
    }

    private void handleProductInfo(Object obj) {
        if (obj == null) {
            Toast.makeText(getContext(), "数据异常", Toast.LENGTH_SHORT).show();
            mActivity.finish();
            return;
        }
        RProductInfo bean = (RProductInfo) obj;
        handleAdBanner(bean.getImgUrls());
        mProductNameTv.setText(bean.getName());
        mSelfSaleTv.setVisibility(bean.isIfSaleOneself()? View.VISIBLE: View.INVISIBLE);
        mRecommendProductTv.setText(bean.getRecomProduct());
        handleTypeListLv(bean.getTypeList());
        mNumberInputView.setMax(bean.getStockCount());
        mGoodCommentRateTv.setText(bean.getFavcomRate()+"%好评");
        mGoodCommentTv.setText(bean.getCommentCount()+"人评论");

    }

    private void handleTypeListLv(String typeListJson) {
        List<String> datas = JSON.parseArray(typeListJson,String.class);
        mTypeListAdapter.setDatas(datas);
        mTypeListAdapter.notifyDataSetChanged();
        FixedViewUtil.setGridViewHeightBasedOnChildren(mTypeListLv,3);
    }

    private void handleAdBanner(String imgUrls) {
        final List<String> imageUrList = JSON.parseArray(imgUrls, String.class);
        mAdAdapter.setDatas(getActivity(), imageUrList);
        mAdAdapter.notifyDataSetChanged();
        mAdIndicator.setText(1 + "/" + imageUrList.size());
        initAdBannerTimer(imageUrList);
    }

    private void initAdBannerTimer(final List<String> imageUrList) {
        mTimer = new Timer();
        mTimer.schedule(new TimerTask() {

            @Override
            public void run() {
                translateAdBannerItem(imageUrList);
            }

        }, 3 * 1000, 3 * 1000);
    }

    /**
     * 指定广告的item
     */
    private void translateAdBannerItem(final List<String> imageUrList) {
        if (imageUrList != null && imageUrList.size() != 0) {
            if (getActivity() == null)
                return;
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    int currentItem = mAdVp.getCurrentItem();
                    currentItem++;
                    if (currentItem > imageUrList.size() - 1) {
                        currentItem = 0;
                    }
                    mAdVp.setCurrentItem(currentItem);
                    mAdIndicator.setText((currentItem + 1) + "/" + imageUrList.size());
                }
            });
        }else{
            mAdVp.setVisibility(View.GONE);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        mTypeListAdapter.mCurrentTabPosition = position;
        mTypeListAdapter.notifyDataSetChanged();
    }
}
