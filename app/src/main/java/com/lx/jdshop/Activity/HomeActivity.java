package com.lx.jdshop.Activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.lx.jdshop.Fragment.CategoryFragment;
import com.lx.jdshop.Fragment.HomeFragment;
import com.lx.jdshop.Fragment.MinFragment;
import com.lx.jdshop.Fragment.ShopCarFragment;
import com.lx.jdshop.R;


/**
 * Created by Xia_焱 on 2017/7/19.
 */

public class HomeActivity extends BaseActivity implements BottomNavigationBar.OnTabSelectedListener {
    private BottomNavigationBar bottomNavigationBar;
    private HomeFragment homeFragment;
    private ShopCarFragment shopCarFragment;
    private MinFragment minFragment;
    private CategoryFragment categoryFragment;
    @Override
    protected void setContentView() {
    setContentView(R.layout.activity_home);
    }

    @Override
    protected void initViews() {
        bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);
    }

    @Override
    protected void initData() {
  /*设置底部导航栏*/
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        bottomNavigationBar
                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC
                );
        bottomNavigationBar.addItem(new BottomNavigationItem(R.mipmap.icon_home, "首页").setActiveColorResource(R.color.orange))
                .addItem(new BottomNavigationItem(R.mipmap.iocn_classify, "分类").setActiveColorResource(R.color.un_press_color))
                .addItem(new BottomNavigationItem(R.mipmap.icon_shopcar, "购物车").setActiveColorResource(R.color.teal))
                .addItem(new BottomNavigationItem(R.mipmap.icon_min, "我的").setActiveColorResource(R.color.blue))
                .setFirstSelectedPosition(0)
                .initialise();
        setDefaultFragment();
        bottomNavigationBar.setTabSelectedListener(this);
    }

    private void setDefaultFragment() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.layFrame, new HomeFragment());
        transaction.commit();
    }

    @Override
    protected void initEvents() {

    }

    @Override
    public void onTabSelected(int position) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        switch (position) {
            case 0:
                if (homeFragment == null) {
                    homeFragment = new HomeFragment();
                }
                transaction.replace(R.id.layFrame, homeFragment);
                break;
            case 1:
                if (categoryFragment == null) {
                    categoryFragment = new CategoryFragment();
                }
                transaction.replace(R.id.layFrame, categoryFragment);
                break;
            case 2:
                if (shopCarFragment == null) {
                    shopCarFragment = new ShopCarFragment();
                }
                transaction.replace(R.id.layFrame, shopCarFragment);
                break;
            case 3:
                if (minFragment == null) {
                    minFragment = new MinFragment();
                }
                transaction.replace(R.id.layFrame, minFragment);
                break;
            default:
                break;
        }
        transaction.commit();
    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }
}
