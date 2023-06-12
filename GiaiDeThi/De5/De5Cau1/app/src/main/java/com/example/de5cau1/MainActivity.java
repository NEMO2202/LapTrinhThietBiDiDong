package com.example.de5cau1;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.de5cau1.databinding.ActivityMainBinding;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    Random random = new Random();

    int randomNumb;

    Handler handler = new Handler();

    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
    LinearLayout.LayoutParams longParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
    LinearLayout.LayoutParams shortParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
    LinearLayout.LayoutParams txtParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

    LinearLayout linearLayout;

    int count = 1;

    int checkNumb;
    Runnable foregroundThread = new Runnable() {
        @Override
        public void run() {
            Button btn = new Button(MainActivity.this);
            btn.setText(Integer.toString(randomNumb));
            if(checkNumb % 2 != 0){
                if(count == 1){
                    btn.setLayoutParams(longParams);
                }
                else{
                    btn.setLayoutParams(shortParams);
                }
            }
            else{
                if(count == 1){
                    btn.setLayoutParams(shortParams);
                }
                else{
                    btn.setLayoutParams(longParams);
                }
            }

            if(randomNumb % 2 == 0){
                btn.setBackgroundColor(Color.GREEN);
            }
            else{
                btn.setBackgroundColor(Color.GRAY);
            }
            linearLayout.addView(btn);

            if(count == 1){
                TextView txt = new TextView(MainActivity.this);
                txt.setLayoutParams(txtParams);
                linearLayout.addView(txt);
            }

            if(count == 2){
                binding.layoutContainer.addView(linearLayout);
                SetUpLinearLayout();
                checkNumb++;
                count = 0;
            }

            count++;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        txtParams.weight = 0.5f;
        longParams.weight = 7.5f;
        shortParams.weight = 2;
        params.topMargin = 25;
        SetUpLinearLayout();
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

    private void drawUI(){
        checkNumb = 1;
        String check = binding.edtNumbOfView.getText().toString();
        if(!Character.isDigit(check.charAt(0))){
            Toast toast = Toast.makeText(MainActivity.this, "Vui lòng nhập số!", Toast.LENGTH_LONG );
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }
        else{
            int numb = Integer.parseInt(binding.edtNumbOfView.getText().toString());
            if(numb % 2 == 0){
                binding.layoutContainer.removeAllViews();
                Thread backgroundThread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for(int i = 1; i <= numb; i++){
                            randomNumb = random.nextInt(10);
                            handler.post(foregroundThread);
                            SystemClock.sleep(100);
                        }
                    }
                });
                backgroundThread.start();
            }
            else{
                Toast toast = Toast.makeText(MainActivity.this, "Vui lòng nhập số chẵn!", Toast.LENGTH_LONG );
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            }
        }
    }

    private void SetUpLinearLayout(){
        linearLayout = new LinearLayout(MainActivity.this);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.setWeightSum(10);
        linearLayout.setLayoutParams(params);
    }

}