package com.lx.jdshop.Activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.DrawerLayout;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;


import com.lx.jdshop.Adapter.BrandAdapter;
import com.lx.jdshop.Adapter.ProductListAdapter;
import com.lx.jdshop.Bean.RBrand;
import com.lx.jdshop.Bean.RProductList;
import com.lx.jdshop.Bean.SProductList;
import com.lx.jdshop.Listenter.IModeChaneListener;
import com.lx.jdshop.Listenter.IProductSortChanegListener;
import com.lx.jdshop.R;
import com.lx.jdshop.UI.FlexiListView;
import com.lx.jdshop.UI.ProductSortPopWindow;
import com.lx.jdshop.UI.SubCategoryView;
import com.lx.jdshop.Util.FixedViewUtil;
import com.lx.jdshop.cons.IdiyMessage;
import com.lx.jdshop.controller.CategoryController;

import java.util.List;


/**
 * Created by Xia_焱 on 2017/7/24.
 */

public class ProductListActivity extends BaseActivity implements IModeChaneListener, IProductSortChanegListener, View.OnClickListener, AdapterView.OnItemClickListener {
    private CategoryController mController;
    private long categoryID;
    private long mTopCategoryID;
    private GridView mBrandGv;
    private BrandAdapter brandAdapter;
    private EditText mMinPriceTv;
    private EditText mMaxPriceTv;
    private View mSliderView;
    private TextView mAllIndicatorTv;
    private SProductList mSendArgs;
    private TextView mJDTakeTv;
    private TextView mPayWhenReceiveTv;
    private TextView mJustHashStockTv;
    private TextView mPriceIndicatoTv;
    private TextView mChooseIndicatorTv;
    private TextView mSaleIndicatorTv;
    private DrawerLayout mDrawerLayout;
    private FlexiListView mProductLv;
    private ProductListAdapter productListAdapter;
    private ProductSortPopWindow mProductSorPop;
    public static final String TODETAILSKEY="TODETAILSKEY";
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case IdiyMessage.BRAND_ACTION_RESULT:
                    handBrand((List<RBrand>) msg.obj);
                    break;
                case IdiyMessage.PRODUCT_LIST_ACTION_RESULT:
                    handleProductList((List<RProductList>) msg.obj);
                    break;
            }
        }
    };


    private void handleProductList(List<RProductList> datas) {
        productListAdapter.setDatas(datas);
        productListAdapter.notifyDataSetChanged();
    }


    private void handBrand(List<RBrand> data) {
        brandAdapter.setDatas(data);
        brandAdapter.notifyDataSetChanged();
        //重置品牌列表的高度
        FixedViewUtil.setGridViewHeightBasedOnChildren(mBrandGv, 3);
    }


    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_product_list);
        Intent intent = getIntent();
        categoryID = intent.getLongExtra(SubCategoryView.TOPRODUCTLISTKEY, 0);
        mTopCategoryID = intent.getLongExtra(SubCategoryView.TOPCATEGORY_ID, 0);
        if (categoryID == 0 || mTopCategoryID == 0) {
            showToast("数据异常");
            finish();
        }
        mSendArgs = new SProductList();
        mSendArgs.setCategoryId(categoryID);
    }

    @Override
    protected void initViews() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        initMainUI();
        initSlideUI();
    }

    private void initSlideUI() {
        mSliderView = findViewById(R.id.slide_view);
        mJDTakeTv = (TextView) findViewById(R.id.jd_take_tv);
        mPayWhenReceiveTv = (TextView) findViewById(R.id.paywhenreceive_tv);
        mJustHashStockTv = (TextView) findViewById(R.id.justhasstock_tv);

        mMinPriceTv = (EditText) findViewById(R.id.minPrice_et);
        mMaxPriceTv = (EditText) findViewById(R.id.maxPrice_et);

        mBrandGv = (GridView) findViewById(R.id.brand_gv);
        brandAdapter = new BrandAdapter(this);
        mBrandGv.setAdapter(brandAdapter);
        mBrandGv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                brandAdapter.mCurrentTabPosition = position;
                brandAdapter.notifyDataSetChanged();
                //记录品牌ID
            }
        });
    }

    private void initMainUI() {
        mAllIndicatorTv = (TextView) findViewById(R.id.all_indicator);
        mSaleIndicatorTv = (TextView) findViewById(R.id.sale_indicator);
        mPriceIndicatoTv = (TextView) findViewById(R.id.price_indicator);
        mChooseIndicatorTv = (TextView) findViewById(R.id.choose_indicator);
        mProductLv = (FlexiListView) findViewById(R.id.product_lv);
        productListAdapter = new ProductListAdapter(this);
        mProductLv.setAdapter(productListAdapter);

    }

    @Override
    protected void initData() {

        mController = new CategoryController(this);
        mController.setIModeChangeListener(this);
        mController.sendAsyncMessage(IdiyMessage.BRAND_ACTION, mTopCategoryID);
        mController.sendAsyncMessage(IdiyMessage.PRODUCT_LIST_ACTION, mSendArgs);

    }

    @Override
    protected void initEvents() {

        mPayWhenReceiveTv.setOnClickListener(this);
        mJustHashStockTv.setOnClickListener(this);
        mJDTakeTv.setOnClickListener(this);
        mSaleIndicatorTv.setOnClickListener(this);
        mAllIndicatorTv.setOnClickListener(this);
        mPriceIndicatoTv.setOnClickListener(this);
        mChooseIndicatorTv.setOnClickListener(this);
        mProductLv.setOnItemClickListener(this);
    }

    @Override
    public void onModeChaned(int action, Object... values) {
        mHandler.obtainMessage(action, values[0]).sendToTarget();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.all_indicator:
                if (mProductSorPop == null) {
                    mProductSorPop = new ProductSortPopWindow(this);
                    mProductSorPop.setListener(this);
                }
                mProductSorPop.onShow(v);
                break;
            case R.id.sale_indicator:
                mSendArgs.setSortType(1);
                mController.sendAsyncMessage(IdiyMessage.PRODUCT_LIST_ACTION, mSendArgs);
                break;
            case R.id.price_indicator:
                int sortType = mSendArgs.getSortType();
                if (sortType == 0 || sortType == 1 || sortType == 3) {
                    mSendArgs.setSortType(2);
                }
                if (sortType == 0 || sortType == 1 || sortType == 2) {
                    mSendArgs.setSortType(3);
                }
                mController.sendAsyncMessage(IdiyMessage.PRODUCT_LIST_ACTION, mSendArgs);
                break;
            case R.id.choose_indicator:
                mDrawerLayout.openDrawer(mSliderView);
                break;
            case R.id.jd_take_tv:
                v.setSelected(!v.isSelected());
                break;
            case R.id.paywhenreceive_tv:
                v.setSelected(!v.isSelected());
                break;
            case R.id.justhasstock_tv:
                v.setSelected(!v.isSelected());
                break;
        }
    }

    //确定
    public void chooseSearchClick(View v) {
        //1.确定选择服务
        int deliverChoose = 0;
        if (mJDTakeTv.isSelected()) {
            deliverChoose += 1;
        }
        if (mPayWhenReceiveTv.isSelected()) {
            deliverChoose += 2;
        }
        if (mJustHashStockTv.isSelected()) {
            deliverChoose += 4;
        }
        mSendArgs.setDeliverChoose(deliverChoose);
        //2.价格区间
        String minPriceStr = mMinPriceTv.getText().toString();
        String maxPriceStr = mMaxPriceTv.getText().toString();
        if (!TextUtils.isEmpty(minPriceStr) && !TextUtils.isEmpty(maxPriceStr)) {
            double minPrice = Double.parseDouble(minPriceStr);
            double maxPrice = Double.parseDouble(maxPriceStr);
            mSendArgs.setMinPrice((int) minPrice);
            mSendArgs.setMaxPrice((int) maxPrice);
        }
        if (brandAdapter.mCurrentTabPosition != -1) {
            //获取品牌ID
            long brandId = brandAdapter.getItemId(brandAdapter.mCurrentTabPosition);
            mSendArgs.setBrandId(brandId);
        }
        //发送请求
        mController.sendAsyncMessage(IdiyMessage.PRODUCT_LIST_ACTION, mSendArgs);
        mDrawerLayout.closeDrawer(mSliderView);
    }

    //重置
    public void resetClick(View v) {
        mSendArgs = new SProductList();
        mSendArgs.setCategoryId(categoryID);
        mController.sendAsyncMessage(IdiyMessage.PRODUCT_LIST_ACTION, mSendArgs);
        mDrawerLayout.closeDrawer(mSliderView);
    }

    @Override
    public void onSortChanged(int action) {
        switch (action) {
            case IProductSortChanegListener.ALLSORT:
                mAllIndicatorTv.setText("综合");
                mSendArgs.setFilterType(1);
                mController.sendAsyncMessage(IdiyMessage.PRODUCT_LIST_ACTION, mSendArgs);
                break;
            case IProductSortChanegListener.NEWSSORT:
                mAllIndicatorTv.setText("新品");
                mSendArgs.setFilterType(2);
                mController.sendAsyncMessage(IdiyMessage.PRODUCT_LIST_ACTION, mSendArgs);
                break;
            case IProductSortChanegListener.COMMENTSORT:
                mAllIndicatorTv.setText("评价");
                mSendArgs.setFilterType(3);
                mController.sendAsyncMessage(IdiyMessage.PRODUCT_LIST_ACTION, mSendArgs);
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        long pId = brandAdapter.getItemId(position);
        Intent intent=new Intent(this, ProductDetailsActivity.class);
        intent.putExtra(TODETAILSKEY, pId);
        startActivity(intent);
    }
}
