package com.example.baitap1;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.baitap1.Adapter.monhocAdapter;
import com.example.baitap1.DAO.monhocDAO;
import com.example.baitap1.DTO.monhocDTO;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ListView lstMH;
    EditText editMaMH,editTenMH;
    Button btnThem;
    monhocDAO mhDAO;
    monhocDTO mh;
    monhocAdapter monhocAdapter;
    List<monhocDTO> listMH;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AddEvent();
        mhDAO = new monhocDAO(this);
        mhDAO.open();
        loadlist();
        btnThem.setOnClickListener(this);

    }
    private void AddEvent()
    {
        editMaMH = (EditText)findViewById(R.id.editMaMH);
        editTenMH = (EditText)findViewById(R.id.editTenMH);
        lstMH = (ListView)findViewById(R.id.lstMoHoc);
        btnThem = (Button)findViewById(R.id.btnThem);

    }
    private void loadlist()
    {
        listMH = new ArrayList<monhocDTO>();
        //create adapter object
        listMH = mhDAO.getAllNhanvien();
        monhocAdapter = new monhocAdapter(this,R.layout.item_monhoc,listMH);
        lstMH.setAdapter(monhocAdapter);
    }
    private void AlertCheckData()
    {
        AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);
        dlgAlert.setMessage("Vui lòng điền đầy đủ thông tin");
        dlgAlert.setTitle("Thông báo");
        dlgAlert.setPositiveButton("OK", null);
        dlgAlert.setCancelable(true);
        dlgAlert.create().show();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btnThem: {
                String maMH = "", tenMH = "";
                maMH = editMaMH.getText().toString().trim();
                tenMH = editTenMH.getText().toString().trim();
                if (maMH.equals("") | tenMH.equals("")) {
                    AlertCheckData();
                    editMaMH.requestFocus();
                } else {
                    mh = new monhocDTO();
                    mh.setMaMH(Integer.parseInt(maMH));
                    mh.setTenMH(tenMH);
                    listMH.add(mh);
                    boolean kt = mhDAO.ThemMonHoc(mh);
                    if (kt) {
                        Toast.makeText(getApplicationContext(), "Thêm môn học thành công", Toast.LENGTH_SHORT).show();
                        monhocAdapter.notifyDataSetChanged();
                    } else
                        Toast.makeText(getApplicationContext(), "Thêm môn học thất bại", Toast.LENGTH_SHORT).show();
                    break;
                }
            }
        }
    }
}
