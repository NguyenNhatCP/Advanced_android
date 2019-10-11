package com.example.bai11;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class NotificationView extends Activity {
    TextView tvNotification;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification);
        tvNotification = (TextView)findViewById(R.id.tvNotification);
        tvNotification.setText(getIntent().getStringExtra("Wifi"));
    }
}
