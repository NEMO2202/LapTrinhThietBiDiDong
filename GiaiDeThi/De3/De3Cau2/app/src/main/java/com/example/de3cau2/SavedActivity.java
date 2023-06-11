package com.example.de3cau2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.adapter.HelperAdapter;
import com.example.de3cau2.databinding.ActivitySavedBinding;
import com.example.models.Student;

public class SavedActivity extends AppCompatActivity {

    ActivitySavedBinding binding;
    Student student;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_saved);
        binding = ActivitySavedBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getDataStudent();
    }

    private void getDataStudent() {
        Intent intent = getIntent();
        student = (Student) intent.getSerializableExtra("StudentInfo");
        binding.edtStudentName.setText(student.getStudentName());
        binding.edtStudentClass.setText(student.getStudentClass());
        binding.edtStudentId.setText(student.getStudentId());
        AddEvents();
    }

    private void AddEvents() {
        binding.btnSaveStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view) {
                ContentValues values = new ContentValues();
                values.put("StudentName",binding.edtStudentName.getText().toString());
                values.put("StudentClass",binding.edtStudentClass.getText().toString());
                values.put("StudentId",binding.edtStudentId.getText().toString());
                long numbOfRows = MainActivity.db.insert(HelperAdapter.TBL_NAME,null,values);
                if(numbOfRows > 0){
                    Toast.makeText(SavedActivity.this,"Success!!",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(SavedActivity.this,"Error!!",Toast.LENGTH_SHORT).show();

                }finish();
            }
        });
        binding.btnBackStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}