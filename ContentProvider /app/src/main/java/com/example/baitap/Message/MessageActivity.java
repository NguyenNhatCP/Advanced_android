package com.example.baitap.Message;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.baitap.Adapter.AdapterSMS;
import com.example.baitap.R;

import java.util.ArrayList;
import java.util.List;

public class MessageActivity extends AppCompatActivity {
    ListView lstSMS;
    ArrayAdapter<SMS> adapterSMS;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        lstSMS = (ListView)findViewById(R.id.lstMessage);
        List<SMS> smsList = new ArrayList<SMS>();
        adapterSMS = new AdapterSMS(this,R.layout.item_sms,smsList);
        Uri uri = Uri.parse("content://sms/inbox");
        Cursor cursor = getContentResolver().query(uri,null,null,null,null);
        if (cursor.moveToFirst())
        {
            for (int i = 0;i <cursor.getCount();i++)
            {
                SMS sms = new SMS();
                sms.setBody(cursor.getString(cursor.getColumnIndexOrThrow("body")).toString());
                sms.setNumber(cursor.getString(cursor.getColumnIndexOrThrow("address")).toString());
                smsList.add(sms);
                cursor.moveToNext();
            }
        }
        cursor.close();
        lstSMS.setAdapter(adapterSMS);
        adapterSMS.notifyDataSetChanged();

    }
}
