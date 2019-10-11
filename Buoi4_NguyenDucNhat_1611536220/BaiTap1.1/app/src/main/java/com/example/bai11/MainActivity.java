package com.example.bai11;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webView = ((WebView) findViewById(R.id.webView));
        new Thread(){
            public  void run()
            {
                http();
            }
        }.start();
    }
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            webView.loadDataWithBaseURL("x-data://base",msg.obj.toString(),"text/html","UTF-8",null);
        }
    };
    public  void  http()
    {
        try {
            URL url = new URL("https://vietnamnet.vn/vn/the-gioi/");
            URLConnection urlConnection = url.openConnection();
            HttpURLConnection httpURLConnection = (HttpURLConnection)urlConnection;
            int resCode = httpURLConnection.getResponseCode();
            if(resCode == HttpURLConnection.HTTP_OK)
            {
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader myReader = new BufferedReader(new InputStreamReader(inputStream));
                String DataRow = "";
                String Buffer = "";
                while ((DataRow = myReader.readLine())!= null){
                    Buffer += DataRow +"\n";
                }
                Log.e("TQKy",Buffer);
                myReader.close();
                inputStream.close();
                handler.obtainMessage(1,Buffer).sendToTarget();

            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
