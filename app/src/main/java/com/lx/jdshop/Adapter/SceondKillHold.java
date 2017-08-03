package com.lx.jdshop.Adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.lx.jdshop.Bean.RSecondKill;
import com.lx.jdshop.R;
import com.lx.jdshop.UI.RoundImageView;
import com.lx.jdshop.cons.NetworkConst;

/**
 * Created by Xia_焱 on 2017/7/21.
 */

public class SceondKillHold extends RvHolder<RSecondKill> {

    private RoundImageView image_iv;
    private TextView nowprice_tv;
    private TextView normalprice_tv;

    public SceondKillHold(View itemView, int type, RvListener listener) {
        super(itemView, type, listener);
        image_iv = (RoundImageView) itemView.findViewById(R.id.image_iv);
        nowprice_tv = (TextView) itemView.findViewById(R.id.nowprice_tv);
        normalprice_tv = (TextView) itemView.findViewById(R.id.normalprice_tv);
    }

    @Override
    public void bindHolder(RSecondKill rSecondKill, int position, Context ctx) {
        // image_iv.setImageUrl(NetworkConst.BASE_URL+rSecondKill.getIconUrl());
        Glide.with(ctx).load(NetworkConst.BASE_URL+rSecondKill.getIconUrl()).placeholder(R.mipmap.ic_loading_22_s).into(image_iv);
        nowprice_tv.setText("¥ "+rSecondKill.getPointPrice());
        normalprice_tv.setText("¥ "+rSecondKill.getAllPrice());
    }


}
