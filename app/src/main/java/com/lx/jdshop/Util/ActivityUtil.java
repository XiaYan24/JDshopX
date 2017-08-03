package com.lx.jdshop.Util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.lx.jdshop.Activity.BaseActivity;


/**
 * Created by Xia_焱 on 2017/7/18.
 */

public class ActivityUtil {
    public static void start(Context c, Class<? extends BaseActivity> clazz, boolean ifFinishSelf) {
        Intent intent = new Intent(c, clazz);
        c.startActivity(intent);

        if (ifFinishSelf) {
            ((Activity) c).finish();
        }
    }
}
