package com.lx.jdshop.UI;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.alibaba.fastjson.JSON;
import com.lx.jdshop.Activity.ProductListActivity;
import com.lx.jdshop.Bean.RSubCategory;
import com.lx.jdshop.Bean.RTopCategory;
import com.lx.jdshop.Listenter.IModeChaneListener;
import com.lx.jdshop.Listenter.IViewContainer;
import com.lx.jdshop.R;
import com.lx.jdshop.cons.IdiyMessage;
import com.lx.jdshop.cons.NetworkConst;
import com.lx.jdshop.controller.CategoryController;

import java.util.List;

import image.SmartImageView;


public class SubCategoryView extends FlexiScrollView implements IViewContainer, IModeChaneListener, View.OnClickListener {

    private RTopCategory topCategoryBean;
    private LinearLayout mContainerLl;
    private CategoryController controller;
    private static final int sLinePerSize = 3;
    public static String TOPRODUCTLISTKEY = "TOPRODUCTLISTKEY";
    public static String TOPCATEGORY_ID = "TOPCATEGORY_ID";

    private Handler mHandle = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case IdiyMessage.SUBCATEGORY_ACTION_RESULT:
                    handleSubCategory((List<RSubCategory>) msg.obj);
                    break;
            }
        }
    };

    private void handleSubCategory(List<RSubCategory> datas) {
        // List<RSubCategory> datas里面有多少条数据 就说明有多少个2级分类
        for (int i = 0; i < datas.size(); i++) {
            intSecondCategoryNameTv(datas, i);
            // 添加3级分类
            // 1.取出某个2级分类
            RSubCategory secondCategory = datas.get(i);
            // 2.获取所有的3级分类
            List<RTopCategory> thridCategorys = JSON.parseArray(
                    secondCategory.getThirdCategory(), RTopCategory.class);
            // 3.计算行数
            int totalSize = thridCategorys.size();
            int lines = totalSize / sLinePerSize;
            // 计算有没余数
            int remainder = totalSize % sLinePerSize;
            lines += (remainder == 0 ? 0 : 1);
            for (int j = 0; j < lines; j++) {
                // 行的容器
                LinearLayout lineLl = new LinearLayout(getContext());
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
                params.setMargins(0, 10, 0, 0);
                lineLl.setLayoutParams(params);
                mContainerLl.addView(lineLl);
                // 4.计算每一行应该展示几列数据
                // 4.1 sLinePerSize每一行最大的数*行的索引=整个3级分类数组的索引
                // 计算是否需要添加第一列数据
                if (sLinePerSize * j <= totalSize - 1) {
                    initThirdCategoryItem(thridCategorys, sLinePerSize * j,
                            lineLl);
                }
                // 计算是否需要添加第2列数据
                if (sLinePerSize * j + 1 <= totalSize - 1) {
                    initThirdCategoryItem(thridCategorys, sLinePerSize * j + 1,
                            lineLl);
                }
                // 计算是否需要添加第2列数据
                if (sLinePerSize * j + 2 <= totalSize - 1) {
                    initThirdCategoryItem(thridCategorys, sLinePerSize * j + 2,
                            lineLl);
                }

            }

        }

    }

    private void initThirdCategoryItem(List<RTopCategory> thridCategorys, int index, LinearLayout lineLl) {
        RTopCategory thirdCategory = thridCategorys.get(index);
        // 5. 创建一列mColumnLl 添加到lineLl
        LinearLayout mColumnLl = new LinearLayout(getContext());
        LinearLayout.LayoutParams mColumnParams = new LinearLayout.LayoutParams(
                (getWidth() - 16) / 3, LinearLayout.LayoutParams.WRAP_CONTENT);
        mColumnLl.setLayoutParams(mColumnParams);
        mColumnLl.setOrientation(LinearLayout.VERTICAL);
        mColumnLl.setGravity(Gravity.CENTER_HORIZONTAL);
        lineLl.addView(mColumnLl);
        mColumnLl.setOnClickListener(this);
        mColumnLl.setTag(thirdCategory);
        // 6.往列的容器里面添加图片
        String imageUrlPath = NetworkConst.BASE_URL
                + thirdCategory.getBannerUrl();
        SmartImageView bannerIv = new SmartImageView(getContext());
        LinearLayout.LayoutParams bannerIvParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                (getWidth() - 16) / 3);
        bannerIv.setLayoutParams(bannerIvParams);
        bannerIv.setImageUrl(imageUrlPath);
        mColumnLl.addView(bannerIv);
        // 7.往列的容器里面添加文本
        TextView thridCategoryNameTv = new TextView(getContext());
        LinearLayout.LayoutParams thridCategoryNameParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        thridCategoryNameTv.setLayoutParams(thridCategoryNameParams);
        thridCategoryNameTv.setText(thirdCategory.getName());
        thridCategoryNameTv.setTextSize(15);
        mColumnLl.addView(thridCategoryNameTv);
    }

    private void intSecondCategoryNameTv(List<RSubCategory> data, int i) {
        TextView secondCategoryNameTv = new TextView(getContext());
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(7, 16, 0, 5);
        secondCategoryNameTv.setLayoutParams(params);
        RSubCategory subCategoryBean = data.get(i);
        secondCategoryNameTv.setText(subCategoryBean.getName());
        mContainerLl.addView(secondCategoryNameTv);
    }

    public SubCategoryView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        initController();
        initUI();

    }

    private void initController() {
        controller = new CategoryController(getContext());
        controller.setIModeChangeListener(this);

    }

    private void initUI() {
        mContainerLl = (LinearLayout) findViewById(R.id.child_container_ll);
    }

    @Override
    public void onShow(Object... values) {
        topCategoryBean = (RTopCategory) values[0];
        //清空容器
        mContainerLl.removeAllViews();
        //往这个容器中添加图片
        initBannerIv();
        //请求2级3级分类的数据源
        controller.sendAsyncMessage(IdiyMessage.SUBCATEGORY_ACTION, topCategoryBean.getId());

    }

    private void initBannerIv() {
        String imgUrl = NetworkConst.BASE_URL + topCategoryBean.getBannerUrl();
        SmartImageView smartImageView = new SmartImageView(getContext());
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(9, 7, 9, 7);
        smartImageView.setLayoutParams(params);
        smartImageView.setScaleType(ImageView.ScaleType.FIT_XY);
        smartImageView.setImageUrl(imgUrl);
        mContainerLl.addView(smartImageView);
    }


    @Override
    public void onModeChaned(int action, Object... values) {
        mHandle.obtainMessage(action, values[0]).sendToTarget();
    }

    @Override
    public void onClick(View v) {
        //点击3级分类
        RTopCategory thirdCategory = (RTopCategory) v.getTag();
        Intent intent = new Intent(getContext(), ProductListActivity.class);
        intent.putExtra(TOPRODUCTLISTKEY,thirdCategory.getId());
        intent.putExtra(TOPCATEGORY_ID,topCategoryBean.getId());
        getContext().startActivity(intent);
    }
}
