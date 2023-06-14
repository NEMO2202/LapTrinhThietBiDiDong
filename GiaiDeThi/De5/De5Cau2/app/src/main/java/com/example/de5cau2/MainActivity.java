package com.example.de5cau2;

import static com.example.adapter.HelperAdapter.DB_NAME;
import static com.example.adapter.HelperAdapter.TBL_NAME;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.adapter.HelperAdapter;
import com.example.de5cau2.databinding.ActivityMainBinding;
import com.example.models.Book;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    ArrayList<Book> books;
    Book selectedBook = null;
    HelperAdapter dbhelper = new HelperAdapter(this);
    ArrayAdapter<Book> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
       binding = ActivityMainBinding.inflate(getLayoutInflater());
       setContentView(binding.getRoot());
       initAdapter();
       addEvents();
    }

    private void addEvents() {
        binding.lvBook.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                selectedBook = books.get(i);
                Intent intent = new Intent(getApplicationContext(),SavedActivity.class);
                intent.putExtra("BookInfo",selectedBook);
                startActivity(intent);
                return false;
            }
        });
    }


    private void initAdapter() {
        dbhelper = new HelperAdapter(MainActivity.this);
        dbhelper.CreateSampleData();
    }
    @Override
    protected void onResume() {
        loadDataFromDB();
        adapter.notifyDataSetChanged();
        super.onResume();
    }

    private void loadDataFromDB() {
       books = new ArrayList<>();
       books.addAll(dbhelper.getAllBook());
        adapter = new ArrayAdapter<Book>(MainActivity.this, android.R.layout.simple_list_item_1,books);
        binding.lvBook.setAdapter(adapter);
    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.d("===Activity Lifecycle===", "onStart");
        Toast.makeText(this, "Activity Lifecycle: onStart", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("===Activity Lifecycle===", "onStop");
        Toast.makeText(this, "Activity Lifecycle: onStop", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("===Activity Lifecycle===", "onDestroy");
        Toast.makeText(this, "Activity Lifecycle: onDestroy", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("===Activity Lifecycle===", "onPause");
        Toast.makeText(this, "Activity Lifecycle: onPause", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("===Activity Lifecycle===", "onRestart");
        Toast.makeText(this, "Activity Lifecycle: onRestart", Toast.LENGTH_SHORT).show();
    }
}