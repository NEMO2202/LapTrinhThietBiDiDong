package com.example.adapter;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class HelperAdapter extends SQLiteOpenHelper {
    public static final String DB_NAME = "employee_db.sqlite";
    public static final int DB_VERSION = 1;
    public static final String TBL_NAME = "Employee";
    public static final String COL_ID = "EmployeeId";
    public static final String COL_NAME = "EmployeeName";
    public static final String COL_AGE = "EmployeeAge";

    public HelperAdapter(@Nullable Context context) {
        super(context,DB_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = " CREATE TABLE IF NOT EXISTS " + TBL_NAME + "(" + COL_ID + " VARCHAR(20) PRIMARY KEY, " + COL_NAME + " VARCHAR(50)," + COL_AGE + "REAL)";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ TBL_NAME);
    }
    public void execSql(String sql){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.execSQL(sql);
    }
    public Cursor queryData(String sql){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery(sql,null);
    }
    public int getNumberOfRows(String sql){
        Cursor cursor = queryData(sql);
        int numbOfRows = cursor.getCount();
        cursor.close();
        return numbOfRows;
    }
    public void CreateSampleData(){
        int Number = getNumberOfRows("SELECT * FROM "+ TBL_NAME);
        if (Number == 0){
            execSql("INSERT INTO " + TBL_NAME + " VALUES('NV-111','Nguyễn Đại Nhân', 23) ");
            execSql("INSERT INTO " + TBL_NAME + " VALUES('NV-112','Trần Đại Nghĩa', 45) ");
            execSql("INSERT INTO " + TBL_NAME + " VALUES('NV-113','Hoàng Đại Lễ', 20) ");
            execSql("INSERT INTO " + TBL_NAME + " VALUES('NV-114','Phạm Đại Trí', 23) ");
            execSql("INSERT INTO " + TBL_NAME + " VALUES('NV-115','Trương Đại Tín', 12) ");
            execSql("INSERT INTO " + TBL_NAME + " VALUES('NV-116','Hồ Đại Đức', 19) ");
        }
    }
}
