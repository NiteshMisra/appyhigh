package com.news.activity;

import android.os.Bundle;
import android.view.MenuItem;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.google.gson.Gson;
import com.news.R;
import com.news.response.NotificationResponse;
import com.news.viewmodel.MyViewModel;
import java.util.Objects;

@SuppressWarnings("deprecation")
public class NewsDetail extends AppCompatActivity {

    private WebView webView;
    private ProgressBar progressBar;
    private com.newsapi.model.NewsList news;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_detail);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        webView = findViewById(R.id.webView);
        MyViewModel myViewModel = ViewModelProviders.of(NewsDetail.this).get(MyViewModel.class);
        String value = getIntent().getStringExtra("GSON");

        news = new Gson().fromJson(value, com.newsapi.model.NewsList.class);
        progressBar = findViewById(R.id.progressBar);

        if (news.getUrlToImage() != null && !news.getUrlToImage().isEmpty()){
            myViewModel.push(news.getTitle(),"News",news.getUrl(),news.getUrlToImage()).observe(this, new Observer<NotificationResponse>() {
                @Override
                public void onChanged(NotificationResponse notificationResponse) {
                    if (notificationResponse != null){
                        if (notificationResponse.getRecipients() > 0){
                            Toast.makeText(NewsDetail.this,"Notification Sent Successfully",Toast.LENGTH_LONG).show();
                        }
                    }
                }
            });
        }else{
            myViewModel.pushWithoutImage(news.getTitle(),"News",news.getUrl()).observe(this, new Observer<NotificationResponse>() {
                @Override
                public void onChanged(NotificationResponse notificationResponse) {
                    if (notificationResponse != null){
                        if (notificationResponse.getRecipients() > 0){
                            Toast.makeText(NewsDetail.this,"Notification Sent Successfully",Toast.LENGTH_LONG).show();
                        }
                    }
                }
            });
        }

        loadWebView();
    }

    private void loadWebView(){

        WebSettings webSettings = webView.getSettings();
        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                progressBar.setProgress(newProgress);
            }
        });
        webView.setWebViewClient(new WebViewClient());
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setPluginState(WebSettings.PluginState.ON);
        webSettings.setAllowFileAccess(true);
        webView.loadUrl(news.getUrl());
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (webView != null){
            webView.destroy();
        }
    }
}