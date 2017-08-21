package com.lx.jdshop.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lx.jdshop.Activity.SettingActivity;
import com.lx.jdshop.Bean.RLoginResult;
import com.lx.jdshop.MyApplication;
import com.lx.jdshop.R;
import com.lx.jdshop.Util.ActivityUtil;

/**
 * Created by Xia_焱 on 2017/8/20.
 */

public class MinFragment extends BaseFragment implements View.OnClickListener {

    private ImageView img_setting;
    private TextView tv_userName;
    private MyApplication application;

    @Override
    public void onActivityCreated( Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View initView(LayoutInflater inflater) {
        view = inflater.inflate(R.layout.fragment_min, null);
        initUI(view);
        initData();
        return  view;
    }

    private void initUI(View view) {
        img_setting = (ImageView) view.findViewById(R.id.img_setting);
        img_setting.setOnClickListener(this);
        tv_userName = (TextView) view.findViewById(R.id.tv_userName);
        application = (MyApplication) getActivity().getApplication();

    }

    @Override
    protected void initData() {
        RLoginResult mLoginResult =  application.mRLoginResult;
        tv_userName.setText(mLoginResult.getUserName());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_setting:
                ActivityUtil.start(getActivity(),SettingActivity.class,false);
                break;
        }
    }
}

