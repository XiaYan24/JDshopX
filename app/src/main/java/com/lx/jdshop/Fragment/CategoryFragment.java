package com.lx.jdshop.Fragment;

import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


import com.lx.jdshop.Adapter.TopCategoryAdapter;
import com.lx.jdshop.Bean.RTopCategory;
import com.lx.jdshop.R;
import com.lx.jdshop.UI.SubCategoryView;
import com.lx.jdshop.cons.IdiyMessage;
import com.lx.jdshop.controller.CategoryController;

import java.util.List;

/**
 * Created by Xia_焱 on 2017/7/23.
 */

public class CategoryFragment extends BaseFragment implements AdapterView.OnItemClickListener {

    private ListView mTopCategoryLv;
    private TopCategoryAdapter mTopCategoryAdapter;
    private SubCategoryView mSubCategoryView;


    @Override
    public View initView(LayoutInflater inflater) {
        view = inflater.inflate(R.layout.fragment_category, null);
        initUI(view);
        initData();
        return view;
    }

    private void initUI(View view) {
        mTopCategoryLv = (ListView) view.findViewById(R.id.top_lv);
        mTopCategoryAdapter = new TopCategoryAdapter(getActivity());
        mTopCategoryLv.setAdapter(mTopCategoryAdapter);
        mTopCategoryLv.setOnItemClickListener(this);
        mSubCategoryView = (SubCategoryView) view.findViewById(R.id.subcategory);
    }

    @Override
    protected void initData() {
        CategoryController categoryController = new CategoryController(getActivity());
        categoryController.setIModeChangeListener(this);
        categoryController.sendAsyncMessage(IdiyMessage.TOPCATEGORY_ACTION, 0);
    }

    @Override
    protected void handlerMessage(Message msg) {
        switch (msg.what) {
            case IdiyMessage.TOPCATEGORY_ACTION_RESULT:
                handleTopCategory((List<RTopCategory>) msg.obj);
                break;
        }

    }

    private void handleTopCategory(List<RTopCategory> data) {
        mTopCategoryAdapter.setDatas(data);
        mTopCategoryAdapter.notifyDataSetChanged();
        mTopCategoryLv.performItemClick(null,0,0);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //-1.记录当前哪个位置被点击了
        mTopCategoryAdapter.mCurrentTabPosition = position;
//		2.刷新列表
        mTopCategoryAdapter.notifyDataSetChanged();
//		将数据添加到SubCategory
        RTopCategory topCategoryBean = (RTopCategory) mTopCategoryAdapter.getItem(position);
//		点击左边的列表 告诉右边的View说 你可以刷新界面
        mSubCategoryView.onShow(topCategoryBean);
    }
}
