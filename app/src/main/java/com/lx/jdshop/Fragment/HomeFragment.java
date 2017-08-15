package com.lx.jdshop.Fragment;
import android.content.Intent;
import android.os.Message;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;


import com.lx.jdshop.Activity.ProductDetailsActivity;
import com.lx.jdshop.Adapter.RecommendAdapter;

import com.lx.jdshop.Adapter.SecondKillAdapter;
import com.lx.jdshop.Bean.Banner;
import com.lx.jdshop.Bean.RRecommndProduct;
import com.lx.jdshop.Bean.RSecondKill;
import com.lx.jdshop.R;
import com.lx.jdshop.UI.FlashView;
import com.lx.jdshop.Util.FixedViewUtil;
import com.lx.jdshop.cons.IdiyMessage;
import com.lx.jdshop.cons.NetworkConst;
import com.lx.jdshop.controller.HomeController;

import java.util.LinkedList;
import java.util.List;

import image.SmartImageView;

import static com.lx.jdshop.Activity.ProductListActivity.TODETAILSKEY;

/**
 * Created by Xia_焱 on 2017/7/19.
 */

public class HomeFragment extends BaseFragment implements AdapterView.OnItemClickListener {
    private LinkedList<String> bannerImages;
    private HomeController homeController;
    private FlashView mFv_banner;
    private SmartImageView mAd2Iv;
    private RecyclerView rlView;
    private GridView mRecommend;
    private RecommendAdapter recommendAdapter;
    private SecondKillAdapter secondKillAdapter;

    @Override
    protected void handlerMessage(Message msg) {
        switch (msg.what){
            case IdiyMessage.ACTION_LOAD_AD1_RESULT:
                handleLoadAd1Result((List<Banner>) msg.obj);
                break;
            case IdiyMessage.ACTION_LOAD_AD2_RESULT:
                handleLoadAd2Result((List<Banner>) msg.obj);
                break;
            case IdiyMessage.SECOND_KILL_ACTION_RESULT:
                handleSecondKill((List<RSecondKill>)msg.obj);
                break;
            case IdiyMessage.RECOMMEND_ACTION_RESULT:
                handleRecommend((List<RRecommndProduct>)msg.obj);
                break;
        }
    }

    private void handleRecommend(List<RRecommndProduct> data) {
        recommendAdapter.setDatas(data);
        recommendAdapter.notifyDataSetChanged();
        FixedViewUtil.setGridViewHeightBasedOnChildren(mRecommend,2);
    }

    private void handleSecondKill(List<RSecondKill> list) {
        secondKillAdapter = new SecondKillAdapter(getActivity(),list);
        rlView.setAdapter(secondKillAdapter);
        secondKillAdapter.setOnItemClickListener(new SecondKillAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                long pId = secondKillAdapter.getItemId(position);
                Intent intent = new Intent(getActivity(), ProductDetailsActivity.class);
                intent.putExtra(TODETAILSKEY, pId);
                startActivity(intent);
            }
        });
    }

    private void handleLoadAd2Result(List<Banner> banners) {
        if (banners.size() > 0) {
            mAd2Iv.setImageUrl(NetworkConst.BASE_URL + banners.get(0).getAdUrl());
            mAd2Iv.setVisibility(View.VISIBLE);
        }
    }

    private void handleLoadAd1Result(final List<Banner> datas){
        if (datas.size()!=0) {
            bannerImages = new LinkedList<>();

            for (int i= 0;i<datas.size();i++){
                Banner banner = datas.get(i);
                bannerImages.add(i,NetworkConst.BASE_URL+banner.getAdUrl());
            }
            mFv_banner.setImagesUrl(bannerImages);
        }
    }

    @Override
    public View initView(LayoutInflater inflater) {
        view = inflater.inflate(R.layout.fragment_home, null);
        initUI(view);
        initData();
        return view;
    }

    private void initUI(View view) {
        mFv_banner = (FlashView) view.findViewById(R.id.fv_header);
        mFv_banner.setPlaceholder(R.mipmap.ic_loading_36);
        mAd2Iv = (SmartImageView) view.findViewById(R.id.ad2_iv);
        rlView = (RecyclerView) view.findViewById(R.id.rl_view);
        rlView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        //猜你喜欢
        mRecommend = (GridView) view.findViewById(R.id.recommend_gv);
        recommendAdapter = new RecommendAdapter(getActivity());
        mRecommend.setAdapter(recommendAdapter);
        mRecommend.setOnItemClickListener(this);
    }

    @Override
    protected void initData() {
        homeController = new HomeController(getActivity());
        homeController.setIModeChangeListener(this);
        homeController.sendAsyncMessage(IdiyMessage.ACTION_LOAD_AD1,1);
        homeController.sendAsyncMessage(IdiyMessage.ACTION_LOAD_AD2,2);
        homeController.sendAsyncMessage(IdiyMessage.SECOND_KILL_ACTION,0);
        homeController.sendAsyncMessage(IdiyMessage.RECOMMEND_ACTION,0);

    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        long pId = recommendAdapter.getItemId(position);
        Intent intent = new Intent(getActivity(), ProductDetailsActivity.class);
        intent.putExtra(TODETAILSKEY, pId);
        startActivity(intent);
    }
}
