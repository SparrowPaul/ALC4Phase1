package com.sparrowpaul.alc4phase1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.MenuItem;
import android.webkit.DownloadListener;
import android.webkit.SslErrorHandler;
import android.webkit.URLUtil;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class AboutAlcActivity extends AppCompatActivity {

    private WebView webView;
    private String url = "http://andela.com/alc";
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_alc);

        if (getSupportActionBar() != null){ //handles elements on the action bar
            getSupportActionBar().setTitle("About ALC");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) { //changing the color of the nav keys
            getWindow().setNavigationBarColor(getResources().getColor(R.color.colorPrimary));
        }

        progressDialog = new ProgressDialog(this); // initializing the progress dialog
        loadWebView(); // function to load the web view
    }


    // handles the top back button
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public void loadWebView(){

        // for webView
        webView = findViewById(R.id.webViewID); // finding web view id
        webView.loadUrl(url); // loading the url

        webView.setWebViewClient(new HelloWeb());

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setAppCacheEnabled(true);
    }

    private class HelloWeb extends WebViewClient {  // web view client class

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {

            progressDialog.setMessage("loading content ...");
            progressDialog.show();

            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            handler.proceed();
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {

            progressDialog.dismiss();

            super.onPageFinished(view, url);

        }
    }
}
