package com.example.de5cau2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.adapter.HelperAdapter;
import com.example.de5cau2.databinding.ActivitySavedBinding;
import com.example.models.Book;

public class SavedActivity extends AppCompatActivity {

    ActivitySavedBinding binding;
    Book book;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  setContentView(R.layout.activity_saved);
        binding = ActivitySavedBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getDataBook();
    }

    private void getDataBook() {
        Intent intent = getIntent();
        book = (Book) intent.getSerializableExtra("BookInfo");
        binding.edtBookId.setText(book.getBookId());
        binding.edtBookName.setText(book.getBookName());
        binding.edtBookPrice.setText(String.valueOf(book.getBookPrice()));
        AddEvents();
    }

    private void AddEvents() {
        binding.btnSaveBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view) {
               HelperAdapter db = new HelperAdapter(SavedActivity.this);
               long numbOfRows = db.InsertBook(binding.edtBookId.getText().toString(),binding.edtBookName.getText().toString(),Double.valueOf(binding.edtBookPrice.getText().toString()));
                if(numbOfRows > 0){
                    Toast.makeText(SavedActivity.this,"Success!!",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(SavedActivity.this,"Error!!",Toast.LENGTH_SHORT).show();

                }finish();
            }
        });
        binding.btnBackBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}