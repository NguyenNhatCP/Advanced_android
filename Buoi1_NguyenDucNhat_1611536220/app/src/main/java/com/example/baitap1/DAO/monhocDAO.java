package com.example.baitap1.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.baitap1.DTO.monhocDTO;
import com.example.baitap1.SQLiteHelper.TaoDatabaseMonHoc;

import java.util.ArrayList;

import static android.os.Build.ID;

public class monhocDAO {
    SQLiteDatabase db;
    TaoDatabaseMonHoc dbmh;
    public monhocDAO(Context context)
    {
        dbmh = new TaoDatabaseMonHoc(context);
    }
    public void open()
    {
     db = dbmh.getWritableDatabase();
    }
    public void close()
    {
     dbmh.close();
    }
    //Truy vấn môn học
    public ArrayList<monhocDTO> getAllNhanvien()
    {
        ArrayList<monhocDTO> listMH = new ArrayList<>();
        String sql = "Select * from monhoc";
        Cursor cursor = db.rawQuery(sql,null);
        listMH.clear();
        while (cursor.moveToNext())
        {
            int ma = cursor.getInt(0);
            String ten = cursor.getString(1);
            listMH.add(new monhocDTO(ma,ten));
        }
        close();
        return  listMH;
    }
    public boolean ThemMonHoc(monhocDTO mh)
    {
        ContentValues ctmh = new ContentValues();
        ctmh.put("maMH",mh.getMaMH());
        ctmh.put("tenMH",mh.getTenMH().toString());
        long inv = db.insert("monhoc",null,ctmh);
        if(inv != 0)
            return true;
        else
            return false;

    }
}
