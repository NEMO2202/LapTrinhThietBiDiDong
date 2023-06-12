package com.example.de3cau1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.de3cau1.databinding.ActivityMainBinding;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    Random random = new Random();

    int randomNumb;

    Handler handler = new Handler();

    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
    LinearLayout.LayoutParams btnParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
    LinearLayout.LayoutParams txtParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

    LinearLayout linearLayout;

    ListView lv;
    int count = 1;

    Button[] btn = new Button[12];

    int countClick;

    String c = "";
    char[] letters = {'1', '2', '3', '4', '5', '6', '7', '8', '9', '*', '0', '#'};
    Runnable foregroundThread = new Runnable() {
        @Override
        public void run() {

            if(count == 1 || count == 2){
                TextView txt = new TextView(MainActivity.this);
                txt.setLayoutParams(txtParams);
                linearLayout.addView(txt);
            }

            if(count == 3){
                binding.layoutContainer.addView(linearLayout);
                SetUpLinearLayout();
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
        btnParams.weight = 6;
        txtParams.weight = 1;
        params.topMargin = 30;
        SetUpLinearLayout();
        lv = new ListView(MainActivity.this);
        lv.setLayoutParams(params);
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
        binding.layoutContainer.removeAllViews();
        binding.edtNumbOfView.setText("");
        c = "";
        randomNumb = 0;
        Thread backgroundThread = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i < 12; i++){
                    //randomNumb = random.nextInt(letters.length);
                    btn[i] = new Button(MainActivity.this);
                    btn[i].setText(Character.toString((char) letters[randomNumb]));
                    btn[i].setLayoutParams(btnParams);
                    linearLayout.addView(btn[i]);
                    randomNumb++;
                    int a = i;
                    btn[a].setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            c += btn[a].getText().toString();
                            binding.edtNumbOfView.setText(c);
                        }
                    });
                    handler.post(foregroundThread);
                    SystemClock.sleep(100);
                }
            }
        });
        backgroundThread.start();
    }


    private void SetUpLinearLayout(){
        linearLayout = new LinearLayout(MainActivity.this);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.setWeightSum(20);
        linearLayout.setLayoutParams(params);
    }
}