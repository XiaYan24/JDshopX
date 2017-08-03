package com.lx.jdshop.Activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.lx.jdshop.Fragment.ProductCommentFragment;
import com.lx.jdshop.Fragment.ProductDetailsFragment;
import com.lx.jdshop.Fragment.ProductIntroduceFragment;
import com.lx.jdshop.R;

import java.util.ArrayList;

/**
 * Created by Xia_焱 on 2017/7/25.
 */

public class ProductDetailsActivity extends BaseActivity implements View.OnClickListener, ViewPager.OnPageChangeListener {

    public long mProductID;
    private View mDetailsIndicator;
    private View mIntroduceIndicator;
    private View mCommentIndicator;
    private LinearLayout introduce_ll;
    private LinearLayout details_ll;
    private LinearLayout comment_ll;
    private ViewPager mContainerVp;
    private ContainerAdapter mContainerAdapter;
    private TextView custom_service_tv;

    @Override
    protected void setContentView() {
        mProductID = getIntent().getLongExtra(ProductListActivity.TODETAILSKEY, 0);
        if (mProductID == 0){
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

        mContainerVp =(ViewPager) findViewById(R.id.container_vp);
        mContainerAdapter = new ContainerAdapter(getSupportFragmentManager());
        mContainerVp.setAdapter(mContainerAdapter);
        mContainerVp.setOnPageChangeListener(this);

        custom_service_tv = (TextView) findViewById(R.id.custom_service_tv);
    }



    public class ContainerAdapter extends FragmentPagerAdapter {
        private ArrayList<Fragment> mFragments=new ArrayList<Fragment>();
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
        switch (v.getId()){
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

                break;
        }
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
}
