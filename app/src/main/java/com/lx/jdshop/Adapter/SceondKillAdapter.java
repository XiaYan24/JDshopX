package com.lx.jdshop.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.lx.jdshop.Bean.RSecondKill;
import com.lx.jdshop.R;

import java.util.List;

/**
 * Created by Xia_焱 on 2017/7/21.
 */

public class SceondKillAdapter extends RvAdapter<RSecondKill> {

    public SceondKillAdapter(Context context, List<RSecondKill> list, RvListener listener) {
        super(context, list, listener);

    }

    @Override
    protected RvHolder getHolder(ViewGroup parent, int viewType) {
        View inflate = mInflater.inflate(R.layout.home_seckill_item, parent, false);
        return new SceondKillHold(inflate, viewType, listener);
    }
}
