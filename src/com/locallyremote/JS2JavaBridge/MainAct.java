package com.locallyremote.JS2JavaBridge;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainAct extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        //Default
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        //Setting the stage
        WebView myWebView = (WebView) findViewById(R.id.webview);
        WebSettings webSettings = myWebView.getSettings();
        //Enable JS in WV.  This opens some real security risks(Cross-Origin Resource Sharing/XSS)
        webSettings.setJavaScriptEnabled(true);
        myWebView.setWebViewClient(new WebViewClient());
        //Inject Java class into myWebView's JS
        myWebView.addJavascriptInterface(new JSInterface(this), "Android");
        //Load the UI
        myWebView.loadUrl("file:///android_asset/www/index.html");
    }
}
