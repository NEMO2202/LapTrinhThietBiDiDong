package com.example.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.models.Phone;

import java.util.ArrayList;
import java.util.List;

public class HelperAdapter extends SQLiteOpenHelper {
    public static final String DB_NAME = "phone_db.sqlite";
    public static final int DB_VERSION = 1;
    public static final String TBL_NAME = "Phone";
    public static final String COL_ID = "PhoneId";
    public static final String COL_NAME = "PhoneName";
    public static final String COL_PRICE = "PhonePrice";

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
            execSql("INSERT INTO " + TBL_NAME + " VALUES('SP-111','Vertu Constellation', 19000) ");
            execSql("INSERT INTO " + TBL_NAME + " VALUES('SP-112','Iphone 5S', 45000) ");
            execSql("INSERT INTO " + TBL_NAME + " VALUES('SP-113','Nokia Lumia 925', 20000) ");
            execSql("INSERT INTO " + TBL_NAME + " VALUES('SP-114','SamSung Galaxy S4', 23000) ");
            execSql("INSERT INTO " + TBL_NAME + " VALUES('SP-115','HTC Desire', 124000) ");
            execSql("INSERT INTO " + TBL_NAME + " VALUES('SP-116','HKPhone Revo LEAD', 19000) ");
        }
    }
public List<Phone> getAllPhone(){
        List<Phone> phones = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TBL_NAME,null,null,null,null, null,null);
        while(cursor.moveToNext()){
            String pId = cursor.getString(0);
            String pName = cursor.getString(1);
            Double pPrice = cursor.getDouble(2);
            Phone p = new Phone(pId,pName,pPrice);
            phones.add(p);
        }
        cursor.close();
        return phones;
}
public long DeletedPhone(String PhoneId, String PhoneName, Double PhonePrice){
        SQLiteDatabase db = getWritableDatabase();
    ContentValues values = new ContentValues();
    values.put("PhoneName",PhoneName);
    values.put("PhonePrice",PhonePrice);
    long numOfRows = db.delete(HelperAdapter.TBL_NAME,"PhoneId=?", new String[]{String.valueOf(PhoneId)});
    db.close();
    return numOfRows;
}

}
