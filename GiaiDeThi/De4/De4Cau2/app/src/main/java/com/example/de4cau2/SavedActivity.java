package com.example.de4cau2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.adapter.HelperAdapter;
import com.example.de4cau2.databinding.ActivitySavedBinding;
import com.example.models.Product;

public class SavedActivity extends AppCompatActivity {

    ActivitySavedBinding binding;
    Product product;
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
        product = (Product) intent.getSerializableExtra("ProductInfo");
        binding.edtProductId.setText(product.getProductId());
        binding.edtProductName.setText(product.getProductName());
        binding.edtProductPrice.setText(String.valueOf(product.getProductPrice()));
        AddEvents();
    }

    private void AddEvents() {
        binding.btnSaveProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view) {
               HelperAdapter db = new HelperAdapter(SavedActivity.this);
               long numbOfRows = db.InsertProduct(binding.edtProductId.getText().toString(),binding.edtProductName.getText().toString(),Double.valueOf(binding.edtProductPrice.getText().toString()));
                if(numbOfRows > 0){
                    Toast.makeText(SavedActivity.this,"Success!!",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(SavedActivity.this,"Error!!",Toast.LENGTH_SHORT).show();

                }finish();
            }
        });
        binding.btnBackProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}