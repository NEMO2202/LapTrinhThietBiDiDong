package com.example.de4cau1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.de4cau1.databinding.ActivityMainBinding;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    Random random = new Random();

    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);

    int randomNumb;

    Handler handler = new Handler();

    int count = 1;

    LinearLayout linearLayout;

    LayoutParams btnParams, txtParams;


    Runnable foregroundThread = new Runnable() {
        @Override
        public void run() {
            if(count <= 3){
                Button btn = new Button(MainActivity.this);
                btn.setText(Integer.toString(randomNumb));
                btn.setLayoutParams(params);
                //setUpButtonParam(btn);

                if(randomNumb % 2 == 0){
                    btn.setBackgroundColor(Color.GREEN);
                }
                else{
                    btn.setBackgroundColor(Color.GRAY);
                }
                linearLayout.addView(btn);

                if(count == 1 || count == 2){
                    TextView txt = new TextView(MainActivity.this);
                    txt.setLayoutParams(params);
                    //setUpTextViewParam(txt);
                    linearLayout.addView(txt);
                }
            }

            if(count == 3){
                binding.layoutContainer.addView(linearLayout);
                setUpLinearLayout();
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
        params.weight = 1;
        params.topMargin = 15;
        setUpLinearLayout();
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
        String check = binding.edtNumbOfView.getText().toString();
        if(!Character.isDigit(check.charAt(0))){
            Toast toast = Toast.makeText(MainActivity.this, "Vui lòng nhập số!", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }
        else{
            int numb = Integer.parseInt(binding.edtNumbOfView.getText().toString());
            if(numb % 3 == 0){
                binding.layoutContainer.removeAllViews();
                Thread backgroundThreat = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for(int i = 1; i <= numb; i++){
                            randomNumb = random.nextInt(10);
                            handler.post(foregroundThread);
                            SystemClock.sleep(100);
                        }
                    }
                });
                backgroundThreat.start();
            }
            else{
                Toast toast = Toast.makeText(MainActivity.this, "Vui lòng nhập số chia hết cho 3!", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            }
        }
    }
    private void setUpTextViewParam(TextView txt){
        txtParams.weight = 1.0f;
        txt.setLayoutParams(txtParams);
    }

    private void setUpButtonParam(Button btn){
        btnParams.weight = 1.0f;
        btn.setLayoutParams(btnParams);
    }
    private void setUpLinearLayout() {
        linearLayout = new LinearLayout(MainActivity.this);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.setWeightSum(5);
        linearLayout.setLayoutParams(params);
    }
}