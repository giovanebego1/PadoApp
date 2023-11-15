package com.proj.padoapp;

import android.animation.Animator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.airbnb.lottie.LottieAnimationView;


public class lock_activity extends Activity {

    LottieAnimationView btn_loking;
    ImageView btn_settings;

    @SuppressLint("MissingInflatedId")
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.lock);

        btn_loking = findViewById(R.id.btn_loking);
        btn_settings = findViewById(R.id.setings_btn);

        btn_loking.setOnClickListener(new View.OnClickListener() {

            @SuppressLint("Range")
            @Override
            public void onClick(View v) {
                Wifi_post postTestTask = new Wifi_post();
                btn_loking.playAnimation();

                btn_loking.addAnimatorListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(@NonNull Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(@NonNull Animator animation) {
                        Intent intent = new Intent(lock_activity.this, unlock_activity.class);
                        startActivity(intent);
                        postTestTask.execute("off");
                    }

                    @Override
                    public void onAnimationCancel(@NonNull Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(@NonNull Animator animation) {

                    }
                });
            }
        });

        btn_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(lock_activity.this, settings_activity.class);
                startActivity(intent);
            }
        });
    }

}
	
	