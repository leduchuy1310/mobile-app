package com.ntvi.bkshop.activity;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.ClientCertRequest;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.ntvi.bkshop.R;

public class WebViewActivity extends Activity{
    WebView webView;
    android.support.v7.widget.Toolbar  toolbar;
    String url,title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview);
        init();
        setWebView();
        ToolBarAction();
    }
    private void init(){
        webView = (WebView)findViewById(R.id.webView);
        toolbar = (Toolbar)findViewById(R.id.toolbar_webview);
    }
    private void setWebView(){
        url = getIntent().getStringExtra("URL_WEBVIEW");
        title = getIntent().getStringExtra("TITLE_WEBVIEW");
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorWhite,null));
        toolbar.setTitle(title);
        WebSettings webSettings = webView.getSettings();
        webSettings.setDomStorageEnabled(true);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        webSettings.setAppCacheEnabled(true);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onReceivedClientCertRequest(WebView view, ClientCertRequest request) {
                super.onReceivedClientCertRequest(view, request);
            }
            @Override
            public boolean shouldOverrideKeyEvent(WebView view, KeyEvent event) {
                return true;
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return true;
            }
        });
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);
    }
    private void ToolBarAction(){
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}