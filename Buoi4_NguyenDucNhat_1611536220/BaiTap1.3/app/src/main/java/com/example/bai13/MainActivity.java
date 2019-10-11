package com.example.bai13;

import android.app.DownloadManager;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String url = "http://speedtest.ftp.otenet.gr/files/test10Mb.db";
        download(url);
    }

    private void download(String url) {
        DownloadManager downloadManager = (DownloadManager)getSystemService(DOWNLOAD_SERVICE);
        Uri dowloadUri = Uri.parse(url);
        DownloadManager.Request request = new DownloadManager.Request(dowloadUri
        ).setTitle("Dummy File")
                .setDescription("Downloading")
                .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE)
                .setAllowedOverMetered(true)
                .setAllowedOverRoaming(true);
        request.setDestinationInExternalPublicDir(
                Environment.DIRECTORY_DOWNLOADS,dowloadUri.getLastPathSegment()
        );
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setTitle("Downloading a file");
        request.setVisibleInDownloadsUi(true);;
        downloadManager.enqueue(request);
    }

}
