package com.example.de2cau1;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.de2cau1.databinding.ActivityMainBinding;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    Random random = new Random();

    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

    int randomNumb;
    int position;

    Handler handler = new Handler();

    Runnable foregroundThread = new Runnable() {
        @Override
        public void run() {
                if (position % 2 == 0) {
                    Button btn = new Button(MainActivity.this);
                    btn.setText(Integer.toString(randomNumb));
                    btn.setBackgroundColor(Color.GRAY);
                    btn.setLayoutParams(params);
                    binding.layoutContainer.addView(btn);
                }else{
                    EditText edt = new EditText(MainActivity.this);
                    edt.setText(Integer.toString(randomNumb));
                    edt.setLayoutParams(params);
                    binding.layoutContainer.addView(edt);

            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        addEvents();
    }

    private void addEvents() {
        binding.btnDraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawUI();
            }
        });
    }

    private void drawUI() {
        String check = binding.edtNumOfViews.getText().toString();
        if (!Character.isDigit(check.charAt(0))) {
            Toast toast = Toast.makeText(MainActivity.this, "Vui lòng nhập số!", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        } else {
            int numb = Integer.parseInt(binding.edtNumOfViews.getText().toString());
            binding.layoutContainer.removeAllViews();
            Thread backgroundThreat = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < numb; i++) {
                        position = i;
                        randomNumb = random.nextInt(100);
                        handler.post(foregroundThread);
                        SystemClock.sleep(100);
                    }
                }
            });
            backgroundThreat.start();
        }
    }
}