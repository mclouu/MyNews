/*
 * Created by Romain Mathieu => https://github.com/mclouu
 */

package com.romain.mathieu.mynews.controller.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import com.romain.mathieu.mynews.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class WebViewActivity extends AppCompatActivity {

    @BindView(R.id.webView)
    WebView webView;

    private String url;


    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        ButterKnife.bind(this);

        Intent intent = getIntent();
        if (intent != null) {


            if (intent.hasExtra("urlArticle")) {
                url = intent.getStringExtra("urlArticle");
            }


            webView.getSettings().setJavaScriptEnabled(true);
            webView.loadUrl(url);
        }

    }
}