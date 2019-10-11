package com.example.baitap1.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.example.baitap1.DTO.monhocDTO;
import com.example.baitap1.R;


import java.util.List;

public class monhocAdapter extends ArrayAdapter<monhocDTO> {
    Context mCtx;
    int rs;
    List<monhocDTO> listMH;

    public monhocAdapter(Context context, int resource, List<monhocDTO> listMH) {
        super(context, resource, listMH);
        this.mCtx = context;
        this.rs = resource;
        this.listMH = listMH;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)mCtx.getSystemService(mCtx.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(rs,parent,false);
        TextView tvMaMH = (TextView)v.findViewById(R.id.tvMaMH);
        TextView tvTenMH = (TextView)v.findViewById(R.id.tvTenMH);

        //setText()
        tvMaMH.setText((String.valueOf(listMH.get(position).getMaMH())));
        tvTenMH.setText(listMH.get(position).getTenMH().toString());
        return v;
    }
}
