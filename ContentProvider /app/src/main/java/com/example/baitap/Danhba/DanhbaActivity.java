package com.example.baitap.Danhba;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Selection;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.baitap.Adapter.AdapterDanhBa;
import com.example.baitap.R;

import java.util.ArrayList;

public class DanhbaActivity extends AppCompatActivity {
    ListView lstDanhBa;
    ArrayList<Contact> listdanhba;
    ArrayAdapter<Contact> adapterDanhBa;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhba);
        addControls();
        showAllContactFromDevice();
    }

    private void addControls() {
        lstDanhBa = (ListView)findViewById(R.id.lstDanhBa);
        listdanhba = new ArrayList<>();
        adapterDanhBa = new AdapterDanhBa(DanhbaActivity.this,R.layout.item_danhba,listdanhba);
        lstDanhBa.setAdapter(adapterDanhBa);
    }


    private void showAllContactFromDevice() {
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        Cursor cursor = getContentResolver().query(uri,null,null,null,null);
        listdanhba.clear();
        while (cursor.moveToNext())
        {
            String tenCotName = ContactsContract.Contacts.DISPLAY_NAME;
            String tenCotPhone = ContactsContract.CommonDataKinds.Phone.NUMBER;
            int vtTenCotName = cursor.getColumnIndex(tenCotName);
            int vtTenCotPhone = cursor.getColumnIndex(tenCotPhone);
            String name = cursor.getString(vtTenCotName);
            String phone = cursor.getString(vtTenCotPhone);
            Contact contact = new Contact(name,phone);
           listdanhba.add(contact);
        }
        adapterDanhBa.notifyDataSetChanged();
    }

}
