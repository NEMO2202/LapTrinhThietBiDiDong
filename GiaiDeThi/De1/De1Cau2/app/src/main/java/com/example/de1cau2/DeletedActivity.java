package com.example.de1cau2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.adapter.HelperAdapter;
import com.example.de1cau2.databinding.ActivityDeletedBinding;
import com.example.model.Employee;

public class DeletedActivity extends AppCompatActivity {
    ActivityDeletedBinding binding;
    Employee employee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  setContentView(R.layout.activity_deleted);
        binding = ActivityDeletedBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getDataEmployee();
    }

    private void getDataEmployee() {
        Intent intent = getIntent();
        employee= (Employee) intent.getSerializableExtra("EmployeeInfo");
        binding.edtEmployeeId.setText(employee.getEmployeeId());
        binding.edtEmployeeName.setText(employee.getEmployeeName());
        binding.edtEmployeeAge.setText(String.valueOf(employee.getEmployeeAge()));
        AddEvents();
    }

    private void AddEvents() { binding.btnDeleteEmployee.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick (View view) {
            AlertDialog.Builder builder = new AlertDialog.Builder(DeletedActivity.this);
            builder.setTitle("Xác nhận xóa");
            builder.setMessage("Bạn có chắc muốn xóa nhân viên '" + employee.getEmployeeName()+ "' không ? ");
            builder.setIcon(android.R.drawable.ic_delete);
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    int numbOfRow = MainActivity.db.delete(HelperAdapter.TBL_NAME, "EmployeeId=?", new String[]{employee.getEmployeeId()});
                    if (numbOfRow > 0) {
                        Toast.makeText(DeletedActivity.this, "Success!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(DeletedActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                    }
                    finish();
                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
            builder.create().show();
        }
    });
        binding.btnBackEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}