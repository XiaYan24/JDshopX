package com.lx.jdshop.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.lx.jdshop.Activity.ProductDetailsActivity;
import com.lx.jdshop.R;
import com.lx.jdshop.cons.NetworkConst;

/**
 * Created by Xia_焱 on 2017/7/25.
 */

public class ProductDetailsFragment extends BaseFragment {

    private View view;

    @Override
    public View initView(LayoutInflater inflater) {
        view = inflater.inflate(R.layout.fragment_product_details, null);
        initUI(view);
        return view;
    }

    private void initUI(View view) {
        WebView mWebView = (WebView) view.findViewById(R.id.web_view);
        ProductDetailsActivity activity = (ProductDetailsActivity) getActivity();
        mWebView.loadUrl(NetworkConst.PRODUCTDETAIL_URL+"?productId="+activity.mProductID);
        mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        mWebView.getSettings().setJavaScriptEnabled(true);
    }
}
