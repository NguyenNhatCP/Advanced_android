package com.example.baitap.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.baitap.Message.SMS;
import com.example.baitap.R;

import java.util.List;

public class AdapterSMS extends ArrayAdapter<SMS> {
    private Context mCtx;
    int res;
    List<SMS> smsList;

    public AdapterSMS(Context mCtx, int res, List<SMS> smsList) {
        super(mCtx, res, smsList);
        this.mCtx = mCtx;
        this.res = res;
        this.smsList = smsList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)mCtx.getSystemService(mCtx.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(res,parent,false);
        TextView tvName = (TextView)v.findViewById(R.id.tv_Number);
        TextView tvPhone = (TextView)v.findViewById(R.id.tv_Body);

        //setText()
        tvName.setText("SDT: "+(smsList.get(position).getNumber()).toString());
        tvPhone.setText("Text: "+smsList.get(position).getBody().toString());
        return v;
    }
}
