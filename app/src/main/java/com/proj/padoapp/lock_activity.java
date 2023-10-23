package com.proj.padoapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.airbnb.lottie.LottieAnimationView;


public class lock_activity extends Activity {

    LottieAnimationView btn_loking;

    /*ImageView btn_lock;

    @SuppressLint("MissingInflatedId")
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.unlock);
        btn_lock = findViewById(R.id.btn_lock);

        btn_lock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Wifi_post postTestTask = new Wifi_post();
                postTestTask.execute("off");
            }
        });

    }*/

    @SuppressLint("MissingInflatedId")
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.lock);

        btn_loking = findViewById(R.id.btn_loking);

        btn_loking.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("Range")
            @Override
            public void onClick(View v) {
                Wifi_post postTestTask = new Wifi_post();
                postTestTask.execute("off");
                btn_loking.setMinAndMaxProgress(0f,60f);
                btn_loking.playAnimation();
            }
        });
    }

}
	
	