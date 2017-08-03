package com.lx.jdshop.Activity;


import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lx.jdshop.Listenter.IModeChaneListener;
import com.lx.jdshop.R;
import com.lx.jdshop.Util.ActivityUtil;
import com.lx.jdshop.cons.IdiyMessage;
import com.lx.jdshop.controller.UserController;


/**
 * Created by Xia_焱 on 2017/7/20.
 */

public class SettingActivity extends BaseActivity implements View.OnClickListener, IModeChaneListener {

    private ImageView id_back;
    private TextView tv_title;
    private LinearLayout llr_click;
    private UserController userController;
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case IdiyMessage.CLEAR_USER_ACTION_RESULT:
                    ActivityUtil.start(SettingActivity.this,LoginActivity.class,true);
                    break;
            }
        }
    };

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_setting);
    }

    @Override
    protected void initViews() {
        id_back = (ImageView) findViewById(R.id.id_back);
        tv_title = (TextView) findViewById(R.id.tv_title);
        llr_click = (LinearLayout) findViewById(R.id.llr_click);
        userController = new UserController(this);
        userController.setIModeChangeListener(this);
    }

    @Override
    protected void initData() {
        tv_title.setText("设置");
    }

    @Override
    protected void initEvents() {
        id_back.setOnClickListener(this);
        llr_click.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.id_back:
                finish();
                break;
            //退出登录
            case R.id.llr_click:
                userController.sendAsyncMessage(IdiyMessage.CLEAR_USER_ACTION,0);
                break;
        }
    }

    @Override
    public void onModeChaned(int action, Object... values) {
        mHandler.obtainMessage(action, values[0]).sendToTarget();
    }
}
