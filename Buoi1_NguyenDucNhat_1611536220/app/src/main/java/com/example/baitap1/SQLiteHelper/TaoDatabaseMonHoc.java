package com.example.baitap1.SQLiteHelper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TaoDatabaseMonHoc extends SQLiteOpenHelper {
    public static final String DatabaseName = "QLMH";
    public static final int version = 1;

    public TaoDatabaseMonHoc(Context context)
    {
        super(context,DatabaseName,null,version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "Create table MONHOC(maMH Integer Primary key,tenMH Text);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop table if exists MONHOC ;");
        onCreate(db);
    }

}