package com.lx.jdshop.Activity;

import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.lx.jdshop.R;
import com.lx.jdshop.Util.ActivityUtil;


/**
 * Created by Xia_焱 on 2017/7/18.
 */

public class SplashActivity extends BaseActivity {

    private ImageView logo_iv;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_splash);
    }

    @Override
    protected void initViews() {
        logo_iv = (ImageView) findViewById(R.id.logo_iv);
        alphaAnim();
    }

    private void alphaAnim() {
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.2f,1.0f);
        alphaAnimation.setDuration(2000);
        alphaAnimation.setFillAfter(true);
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //启动Activity
                ActivityUtil.start(SplashActivity.this,LoginActivity.class,true);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        logo_iv.startAnimation(alphaAnimation);

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initEvents() {

    }
}
