package com.example.baitap.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

import com.example.baitap.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class WebViewActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_layout_rss);
        Intent intent = getIntent();
        String link = intent.getStringExtra("link");
        WebView webView = (WebView) findViewById(R.id.webview);
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        new GetHTML(webView).execute(link);
    }

    private class GetHTML extends AsyncTask<String, Void, String> {
        WebView webview;
        public GetHTML(WebView webView)
        {
            this.webview = webView;
        }
        @Override
        protected String doInBackground(String... params) {
            try {
                URL url = new URL(params[0]);
                URLConnection urlconnect = url.openConnection();
                HttpURLConnection httpURLConnect = (HttpURLConnection)urlconnect;
                int responseCode = httpURLConnect.getResponseCode();
                if(responseCode == HttpURLConnection.HTTP_OK) {
                    InputStream input = httpURLConnect.getInputStream();
                    BufferedReader myreader = new BufferedReader(new InputStreamReader(input));
                    String aDatarow = "";
                    String aBuffer = "";
                    while ((aDatarow = myreader.readLine()) != null) {
                        aBuffer += aDatarow + "\n";
                    }
                    myreader.close();
                    input.close();
                    return aBuffer;
                }
            }catch (IOException e){e.printStackTrace();}
            return  null;
        }

        @Override
        protected void onPostExecute(String result) {
            if(result != null)
            {
                webview.loadDataWithBaseURL("x-data://base",result,"text/html","UTF-8",null);
            }
            super.onPostExecute(result);
        }
    }
}
