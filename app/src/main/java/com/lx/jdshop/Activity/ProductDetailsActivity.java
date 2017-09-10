package com.lx.jdshop.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.lx.jdshop.Bean.RResult;
import com.lx.jdshop.Fragment.ProductCommentFragment;
import com.lx.jdshop.Fragment.ProductDetailsFragment;
import com.lx.jdshop.Fragment.ProductIntroduceFragment;
import com.lx.jdshop.Listenter.IModeChaneListener;
import com.lx.jdshop.R;
import com.lx.jdshop.cons.IdiyMessage;
import com.lx.jdshop.controller.ProductDetailsController;

import java.util.ArrayList;
import java.util.logging.Handler;

/**
 * Created by Xia_焱 on 2017/7/25.
 */

public class ProductDetailsActivity extends BaseActivity implements View.OnClickListener, ViewPager.OnPageChangeListener, IModeChaneListener {

    public long mProductID;
    public int mBuyCount = 1;
    public String mProductVersion = "";
    private View mDetailsIndicator;
    private View mIntroduceIndicator;
    private View mCommentIndicator;
    private LinearLayout introduce_ll;
    private LinearLayout details_ll;
    private LinearLayout comment_ll;
    private ViewPager mContainerVp;
    private ContainerAdapter mContainerAdapter;
    private TextView custom_service_tv;
    private ProductDetailsController mController;

    @SuppressLint("HandlerLeak")
    private android.os.Handler mHandler = new android.os.Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case IdiyMessage.ADD2SHOPCAR_ACTION_RESULT:
                    RResult bean = (RResult) msg.obj;
                    if (bean.isSuccess()) {
                        showToast("添加成功");
                        finish();
                    } else {
                        showToast("添加失败");
                    }
                    break;

            }
        }
    };

    @Override
    protected void setContentView() {
        mProductID = getIntent().getLongExtra(ProductListActivity.TODETAILSKEY, 0);
        if (mProductID == 0) {
            showToast("数据异常");
            finish();
        }
        setContentView(R.layout.activity_product_details);

    }

    @Override
    protected void initViews() {
        introduce_ll = (LinearLayout) findViewById(R.id.introduce_ll);
        details_ll = (LinearLayout) findViewById(R.id.details_ll);
        comment_ll = (LinearLayout) findViewById(R.id.comment_ll);

        mDetailsIndicator = findViewById(R.id.details_view);
        mIntroduceIndicator = findViewById(R.id.introduce_view);
        mCommentIndicator = findViewById(R.id.comment_view);

        mContainerVp = (ViewPager) findViewById(R.id.container_vp);
        mContainerAdapter = new ContainerAdapter(getSupportFragmentManager());
        mContainerVp.setAdapter(mContainerAdapter);
        mContainerVp.setOnPageChangeListener(this);

        custom_service_tv = (TextView) findViewById(R.id.custom_service_tv);
    }


    public class ContainerAdapter extends FragmentPagerAdapter {
        private ArrayList<Fragment> mFragments = new ArrayList<Fragment>();

        public ContainerAdapter(FragmentManager fm) {
            super(fm);
            mFragments.add(new ProductIntroduceFragment());
            mFragments.add(new ProductDetailsFragment());
            mFragments.add(new ProductCommentFragment());
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }
    }


    @Override
    protected void initData() {
        mController = new ProductDetailsController(this);
        mController.setIModeChangeListener(this);
    }

    @Override
    protected void initEvents() {
        introduce_ll.setOnClickListener(this);
        details_ll.setOnClickListener(this);
        comment_ll.setOnClickListener(this);
        custom_service_tv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        defaultIndicator();
        switch (v.getId()) {
            case R.id.introduce_ll:
                mIntroduceIndicator.setVisibility(View.VISIBLE);
                mContainerVp.setCurrentItem(0);
                break;
            case R.id.details_ll:
                mDetailsIndicator.setVisibility(View.VISIBLE);
                mContainerVp.setCurrentItem(1);
                break;
            case R.id.comment_ll:
                mCommentIndicator.setVisibility(View.VISIBLE);
                mContainerVp.setCurrentItem(2);
                break;
            case R.id.custom_service_tv:
                //调起QQ
                String url = "mqqwpa://im/chat?chat_type=wpa&uin=2853700237";
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
                break;
        }
    }

    public void add2ShopCar(View v) {
        //商品的ID 购买的数量 以及型号 用户ID
        if (mBuyCount == 0) {
            showToast("请设置购买的数量");
            return;
        }
        if (mProductVersion.equals("")) {
            showToast("请选择购买的型号");
            return;
        }
        // 发送请求需要mController三个参数
        mController.sendAsyncMessage(IdiyMessage.ADD2SHOPCAR_ACTION,
                mProductID, mBuyCount, mProductVersion);


    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        defaultIndicator();
        switch (position) {
            case 0:
                mIntroduceIndicator.setVisibility(View.VISIBLE);
                break;
            case 1:
                mDetailsIndicator.setVisibility(View.VISIBLE);
                break;
            case 2:
                mCommentIndicator.setVisibility(View.VISIBLE);
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private void defaultIndicator() {
        mDetailsIndicator.setVisibility(View.INVISIBLE);
        mIntroduceIndicator.setVisibility(View.INVISIBLE);
        mCommentIndicator.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onModeChaned(int action, Object... values) {
        mHandler.obtainMessage(action, values[0]).sendToTarget();
    }
}
