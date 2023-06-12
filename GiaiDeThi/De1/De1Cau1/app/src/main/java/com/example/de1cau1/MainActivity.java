package com.example.de1cau1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.de1cau1.databinding.ActivityMainBinding;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    Random random = new Random();
    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(500, LinearLayout.LayoutParams.WRAP_CONTENT);
    int randomNumb;
    Handler handler = new Handler();

    int position;

    Runnable foregroundThread = new Runnable() {
        @Override
        public void run() {
            Button btn = new Button(MainActivity.this);
            params = new LinearLayout.LayoutParams(500, LinearLayout.LayoutParams.WRAP_CONTENT);
            btn.setText(Integer.toString(randomNumb));
            //btn.setWidth(70);
            if (position % 2 == 0) {
                params.gravity = Gravity.LEFT;
            } else {
                params.gravity = Gravity.RIGHT;
            }
            btn.setLayoutParams(params);
            binding.layoutContainer.addView(btn);
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
        String check = binding.edtNumbOfViews.getText().toString();
        if (!Character.isDigit(check.charAt(0))) {
            Toast toast = Toast.makeText(MainActivity.this, "Vui lòng nhập số!", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        } else {
            int numb = Integer.parseInt(binding.edtNumbOfViews.getText().toString());
            binding.layoutContainer.removeAllViews();
            Thread backgroundThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < numb; i++) {
                        position = i;
                        randomNumb = random.nextInt(100);
                        handler.post(foregroundThread);
                        SystemClock.sleep(200);
                    }
                }
            });
            backgroundThread.start();
        }
    }
}