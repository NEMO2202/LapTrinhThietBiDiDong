package com.example.adapter;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class HelperAdapter extends SQLiteOpenHelper {
    public static final String DB_NAME = "product_db.sqlite";
    public static final int DB_VERSION = 1;
    public static final String TBL_NAME = "Product";
    public static final String COL_ID = "ProductId";
    public static final String COL_NAME = "ProductName";
    public static final String COL_PRICE = "ProductPrice";

    public HelperAdapter(@Nullable Context context) {
        super(context,DB_NAME,null,DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = " CREATE TABLE IF NOT EXISTS " + TBL_NAME + "(" + COL_ID + " VARCHAR(20) PRIMARY KEY, " + COL_NAME + " VARCHAR(50)," + COL_PRICE + "REAL)";
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
            execSql("INSERT INTO " + TBL_NAME + " VALUES('MH36321','Thuốc trừ sâu', 19000) ");
            execSql("INSERT INTO " + TBL_NAME + " VALUES('MH36322','Thuốc diệt Bug', 45000) ");
            execSql("INSERT INTO " + TBL_NAME + " VALUES('MH36323','Thuốc diệt cỏ', 20000) ");
            execSql("INSERT INTO " + TBL_NAME + " VALUES('MH36324','Máy phun thuốc', 23000) ");
            execSql("INSERT INTO " + TBL_NAME + " VALUES('MH36325','Máy bơm', 124000) ");
            execSql("INSERT INTO " + TBL_NAME + " VALUES('MH36326','Bộ dụng cụ', 19000) ");
        }
    }
}
