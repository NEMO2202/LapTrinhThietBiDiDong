package com.example.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.de3cau2.MainActivity;
import com.example.de3cau2.SavedActivity;
import com.example.models.Student;
import com.google.android.gms.analytics.ecommerce.Product;

import java.util.ArrayList;
import java.util.List;

public class HelperAdapter extends SQLiteOpenHelper {
    public static final String DB_NAME = "student_db.sqlite";
    public static final int DB_VERSION = 1;
    public static final String TBL_NAME = "Student";
    public static final String COL_ID = "StudentId";
    public static final String COL_NAME = "StudentName";
    public static final String COL_CLASS = "StudentClass";
    public HelperAdapter(@Nullable Context context) {
        super(context,DB_NAME,null,DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = " CREATE TABLE IF NOT EXISTS " + TBL_NAME + "(" + COL_ID + " VARCHAR(20) PRIMARY KEY, " + COL_NAME + " VARCHAR(50)," + COL_CLASS + " VARCHAR(50))";
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
            execSql("INSERT INTO " + TBL_NAME + " VALUES('6151071069','Phạm Thị Ly', 'Công nghệ thông tin K61') ");
            execSql("INSERT INTO " + TBL_NAME + " VALUES('6151071066','Hồ Thị Kiều Linh', 'Công nghệ thông tin K61') ");
            execSql("INSERT INTO " + TBL_NAME + " VALUES('6151071085','Nguyễn Thị Nhật Phương', 'Công nghệ thông tin K61') ");
            execSql("INSERT INTO " + TBL_NAME + " VALUES('6151071002','Nguyễn Quốc Bảo','Cơ điện tử') ");
            execSql("INSERT INTO " + TBL_NAME + " VALUES('6151071045','Nguyễn Văn A', 'Kế toán') ");
        }
    }
    public List<Student> getAllStudent(){
        List<Student> students=new ArrayList<>();
        SQLiteDatabase db=getReadableDatabase();
        Cursor cursor=db.query(TBL_NAME, null, null, null, null, null,null);
        while(cursor.moveToNext()){
            String pId=cursor.getString(0);
            String pName=cursor.getString(1);
            String pClass=cursor.getString(2);

            Student p=new Student(pId, pName, pClass);
            students.add(p);
        }
        cursor.close();
        return students;
    }
    public long InsertStudent(String StudentId, String StudentName, String StudentClass){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("StudentId",StudentId );
        values.put("StudentName", StudentName);
        values.put("StudentClass",StudentClass);
        long numbOfRows =db.insert(HelperAdapter.TBL_NAME,null,values);
        db.close();
        return numbOfRows;
    }
}
