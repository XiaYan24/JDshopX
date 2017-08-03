package com.lx.jdshop.Activity;

import android.os.Handler;
import android.os.Message;

import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.lx.jdshop.Bean.RResult;
import com.lx.jdshop.Listenter.IModeChaneListener;
import com.lx.jdshop.R;
import com.lx.jdshop.Util.ActivityUtil;
import com.lx.jdshop.cons.IdiyMessage;
import com.lx.jdshop.controller.UserController;


/**
 * Created by Xia_焱 on 2017/7/19.
 */

public class RegisterActivity extends BaseActivity implements View.OnClickListener, IModeChaneListener {
    private TextInputLayout userPhoneWrapper;
    private TextInputLayout passwordWrapper;
    private EditText password;
    private ImageView img_back;
    private LinearLayout llr_click;
    private EditText userPhone;
    private UserController userController;
    private Handler mhandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case IdiyMessage.REGISTER_ACTION_RESULT:
                    handRegister((RResult) msg.obj);
                    break;
            }
        }
    };


    private void handRegister(RResult obj) {
        if (obj.isSuccess()) {
            ActivityUtil.start(RegisterActivity.this, LoginActivity.class, false);
            showToast("注册成功");
        } else {
            showToast(obj.getErrorMsg());
        }
    }


    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_register);

    }

    @Override
    protected void initViews() {
        userPhoneWrapper = (TextInputLayout) findViewById(R.id.userPhoneWrapper);
        passwordWrapper = (TextInputLayout) findViewById(R.id.passwordWrapper);
        password = (EditText) findViewById(R.id.password);
        userPhone = (EditText) findViewById(R.id.userPhone);
        llr_click = (LinearLayout) findViewById(R.id.llr_click);
        userController = new UserController(this);
        userController.setIModeChangeListener(this);
        img_back = (ImageView) findViewById(R.id.img_back);
    }


    @Override
    protected void initData() {
        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String str = password.getText().toString();
                if (str.length() == 0) {
                    llr_click.setBackgroundColor(getResources().getColor(R.color.color_gray));
                    llr_click.setEnabled(false);
                } else {
                    llr_click.setBackgroundColor(getResources().getColor(R.color.color_red));
                }
            }
        });
    }

    @Override
    protected void initEvents() {
        llr_click.setOnClickListener(this);
        img_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.llr_click:
                RegisterUser();
                break;
            case R.id.img_back:
                finish();
                break;
        }
    }

    private void RegisterUser() {
        String userName = userPhoneWrapper.getEditText().getText().toString();
        String passWord = passwordWrapper.getEditText().getText().toString();
        if (TextUtils.isEmpty(userName)) {
            showToast("账号不能为空");
            return;
        }
        if (TextUtils.isEmpty(passWord)) {
            showToast("请设置密码");
            return;
        }
        //注册用户
        userController.sendAsyncMessage(IdiyMessage.REGISTER_ACTION, userName, passWord);
    }

    @Override
    public void onModeChaned(int action, Object... values) {
        mhandler.obtainMessage(action, values[0]).sendToTarget();
    }
}
