package com.example.baitap.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.baitap.Danhba.Contact;
import com.example.baitap.R;

import java.util.ArrayList;
import java.util.List;

public class AdapterDanhBa extends ArrayAdapter<Contact> {
    Context mCtx;
    int layouts;
    ArrayList<Contact> listDanhBa;

    public AdapterDanhBa(Context mCtx, int layouts, ArrayList<Contact> listDanhBa) {
        super(mCtx, layouts, listDanhBa);
        this.mCtx = mCtx;
        this.layouts = layouts;
        this.listDanhBa = listDanhBa;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)mCtx.getSystemService(mCtx.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(layouts,parent,false);
        TextView tvName = (TextView)v.findViewById(R.id.tv_Name);
        TextView tvPhone = (TextView)v.findViewById(R.id.tv_Phone);

        //setText()
        tvName.setText("Tên: "+(listDanhBa.get(position).getName()).toString());
        tvPhone.setText("Số điện thoại: "+listDanhBa.get(position).getPhone().toString());
        return v;
    }
}
