package com.example.adapter;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class HelperAdapter extends SQLiteOpenHelper {
    public static final String DB_NAME = "book_db.sqlite";
    public static final int DB_VERSION = 1;
    public static final String TBL_NAME = "Book";
    public static final String COL_ID = "BookId";
    public static final String COL_NAME = "BookName";
    public static final String COL_PRICE = "BookPrice";
    public HelperAdapter(@Nullable Context context) {
        super(context,DB_NAME,null,DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = " CREATE TABLE IF NOT EXISTS " + TBL_NAME + "(" + COL_ID + " VARCHAR(20) PRIMARY KEY, " + COL_NAME + " VARCHAR(50)," + COL_PRICE + " REAL)";
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
            execSql("INSERT INTO " + TBL_NAME + " VALUES('SH001','Khoa Học Tự Nhiên', 23000) ");
            execSql("INSERT INTO " + TBL_NAME + " VALUES('SH002','Toán Nâng Cao', 18000) ");
            execSql("INSERT INTO " + TBL_NAME + " VALUES('SH003','Ngữ Văn', 49000) ");
            execSql("INSERT INTO " + TBL_NAME + " VALUES('SH004','Địa lý',23000) ");
            execSql("INSERT INTO " + TBL_NAME + " VALUES('SH005','Nguyễn Văn A', 17000) ");
        }
    }

}
