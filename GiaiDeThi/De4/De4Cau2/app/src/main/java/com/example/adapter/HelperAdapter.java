package com.example.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.models.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.PrimitiveIterator;

import kotlin.collections.DoubleIterator;

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
            execSql("INSERT INTO " + TBL_NAME + " VALUES('SH001','Thuốc trừ sâu', 23000) ");
            execSql("INSERT INTO " + TBL_NAME + " VALUES('SH002','Thuộc diệt Bug', 18000) ");
            execSql("INSERT INTO " + TBL_NAME + " VALUES('SH003','Thuốc trầm cẩm', 49000) ");
            execSql("INSERT INTO " + TBL_NAME + " VALUES('SH004','Thuốc ngủ ',23000) ");
            execSql("INSERT INTO " + TBL_NAME + " VALUES('SH005','Thuốc bệnh', 17000) ");
        }
    }
    public List<Product> getAllProduct(){
        List<Product> products = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TBL_NAME,null,null,null,null,null,null);
        while(cursor.moveToNext()){
            String pId = cursor.getString(0);
            String pName = cursor.getString(1);
            Double pPrice = cursor.getDouble(2);
            Product p = new Product(pId,pName, pPrice);
            products.add(p);
        }
        cursor.close();
        return products;
    }
    public long InsertProduct(String ProductId, String ProductName, Double ProductPrice){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("ProductId", ProductId);
        values.put("ProductName", ProductName);
        values.put("ProductPrice",ProductPrice);
        long Numb = db.insert(HelperAdapter.TBL_NAME,null,values);
        db.close();
        return Numb;
    }

}
