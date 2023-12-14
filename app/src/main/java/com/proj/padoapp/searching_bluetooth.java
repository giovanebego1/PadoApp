package com.proj.padoapp;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;

public class searching_bluetooth extends AppCompatActivity {

    LottieAnimationView loading_bluetooth;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searching_bluetooth);

        LottieAnimationView loading = findViewById(R.id.loading_bloutooth);
        loading.setAnimation(R.raw.loading_red);
        loading.setRepeatCount(1);
        loading.playAnimation();
        loading.addAnimatorListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                Intent intent = new Intent(searching_bluetooth.this, unlock_activity.class);
                startActivity(intent);
            }
        });
    }
}
