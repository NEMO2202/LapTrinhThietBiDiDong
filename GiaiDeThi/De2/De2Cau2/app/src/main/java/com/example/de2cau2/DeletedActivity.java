package com.example.de2cau2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.adapter.HelperAdapter;
import com.example.de2cau2.databinding.ActivityDeletedBinding;
import com.example.models.Phone;

public class DeletedActivity extends AppCompatActivity {
    ActivityDeletedBinding binding;
    Phone phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_deleted);
        binding = ActivityDeletedBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getDataPhone();
    }

    private void getDataPhone() {
        Intent intent = getIntent();
        phone = (Phone) intent.getSerializableExtra("SmartPhoneInfo");
        binding.edtPhoneId.setText(phone.getPhoneId());
        binding.edtPhoneName.setText(phone.getPhoneName());
        binding.edtPhonePrice.setText(String.valueOf(phone.getPhonePrice()));
        AddEvents();
    }

    private void AddEvents() {
        binding.btnDeletePhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(DeletedActivity.this);
                builder.setTitle("Xác nhận xóa");
                builder.setMessage("Bạn có chắc muốn xóa sản phẩm '" + phone.getPhoneName()+ "' không ? ");
                builder.setIcon(android.R.drawable.ic_delete);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        int numbOfRow = MainActivity.db.delete(HelperAdapter.TBL_NAME, "PhoneId=?", new String[]{String.valueOf(phone.getPhoneId())});
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
        binding.btnBackPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}
