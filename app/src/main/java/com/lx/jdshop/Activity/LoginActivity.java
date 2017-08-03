package com.lx.jdshop.Activity;

import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.alibaba.fastjson.JSON;
import com.lx.jdshop.Bean.RLoginresult;
import com.lx.jdshop.Bean.RResult;
import com.lx.jdshop.DB.UserDao;
import com.lx.jdshop.Listenter.IModeChaneListener;
import com.lx.jdshop.MyApplication;
import com.lx.jdshop.R;
import com.lx.jdshop.Util.ActivityUtil;
import com.lx.jdshop.cons.IdiyMessage;
import com.lx.jdshop.controller.UserController;

/**
 * Created by Xia_焱 on 2017/7/18.
 */

public class LoginActivity extends BaseActivity implements View.OnClickListener, IModeChaneListener {
    private TextInputLayout passwordWrapper;
    private TextInputLayout usernameWrapper;
    private Button btnLogin;
    private TextView tvRegister;
    private TextView tvForget;
    private UserController loginController;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case IdiyMessage.LOGIN_ACTION_RESULT:
                    handLoginResult(msg);
                    break;
                case IdiyMessage.SAVE_USERTODB_RESULT:
                    handSaveUser((Boolean) msg.obj);
                    break;
                case IdiyMessage.GET_USER_ACTION_RESULT:
                    handGetUser(msg.obj);
                    break;
            }
        }
    };

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_login);
    }

    @Override
    protected void initViews() {
        usernameWrapper = (TextInputLayout) findViewById(R.id.usernameWrapper);
        passwordWrapper = (TextInputLayout) findViewById(R.id.passwordWrapper);
        btnLogin = (Button) findViewById(R.id.btn_login);
        tvRegister = (TextView) findViewById(R.id.tv_register);
        tvForget = (TextView) findViewById(R.id.tv_forget);
        loginController = new UserController(this);
        loginController.setIModeChangeListener(this);

    }

    @Override
    protected void initData() {
        //回显数据
        loginController.sendAsyncMessage(IdiyMessage.GET_USER_ACTION, 0);
    }

    @Override
    protected void initEvents() {
        btnLogin.setOnClickListener(this);
        tvRegister.setOnClickListener(this);
        tvForget.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                StartLogin();
                break;
            case R.id.tv_register:
                //注册
                ActivityUtil.start(LoginActivity.this, RegisterActivity.class, false);
                break;
            case R.id.tv_forget:
                showToast("请联系管理员");
                break;

        }
    }

    private void StartLogin() {
        String username = usernameWrapper.getEditText().getText().toString();
        String password = passwordWrapper.getEditText().getText().toString();
        if (TextUtils.isEmpty(username)) {
            Toast.makeText(this, R.string.user_name, Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, R.string.user_pass, Toast.LENGTH_SHORT).show();
            return;
        }
        //发送网络请求
        loginController.sendAsyncMessage(IdiyMessage.LOGIN_ACTION, username, password);
    }
    //登录
    private void handLoginResult(Message msg) {
        RResult obj = (RResult) msg.obj;
        if (obj.isSuccess()) {
            //将用户的信息保存到Application中
            RLoginresult bean = JSON.parseObject(obj.getResult(),RLoginresult.class);
            ((MyApplication)getApplication()).setRLoginResult(bean);

            String username = usernameWrapper.getEditText().getText().toString();
            String password = passwordWrapper.getEditText().getText().toString();
            //账号密码保存
            loginController.sendAsyncMessage(IdiyMessage.SAVE_USERTODB, username, password);


        } else {
            showToast(obj.getErrorMsg());
        }
    }
    //获取账号
    private void handGetUser(Object obj) {
        if (obj != null) {
            UserDao.UserInfo user = (UserDao.UserInfo) obj;
            usernameWrapper.getEditText().setText(user.getName());
            passwordWrapper.getEditText().setText(user.getPwd());

        }
    }
    //保存账号
    private void handSaveUser(Boolean ifSucces) {
        if (ifSucces) {
            //进入主页
            ActivityUtil.start(LoginActivity.this, HomeActivity.class, true);
        } else {
          showToast("请检查网络");
        }
    }
    @Override
    public void onModeChaned(int action, Object... values) {
        mHandler.obtainMessage(action, values[0]).sendToTarget();
    }
}
