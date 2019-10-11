package com.example.baitap;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.baitap.Danhba.DanhbaActivity;
import com.example.baitap.Message.MessageActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public  void XulymanhinhDanhBa(View view)
    {
        Intent intent = new Intent(this, DanhbaActivity.class);
        startActivity(intent);
    }
    public  void XulymanhinhTinNhan(View view)
    {
        Intent intent = new Intent(this, MessageActivity.class);
        startActivity(intent);
    }

}
