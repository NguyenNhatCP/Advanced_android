package com.example.musicservice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnStart,btnStop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnStart = (Button)findViewById(R.id.btnStartMusic);
        btnStop = (Button)findViewById(R.id.btnStopMusic);

        btnStart.setOnClickListener(this);
        btnStop.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(MainActivity.this, MusicService.class);
        switch (v.getId())
        {
            case R.id.btnStartMusic: {
                startService(intent);
                break;
            }
            case R.id.btnStopMusic: {
                stopService(intent);
                break;
            }
        }
    }
}
