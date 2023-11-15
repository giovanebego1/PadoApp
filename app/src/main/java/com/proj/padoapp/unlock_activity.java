package com.proj.padoapp;

import android.animation.Animator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.airbnb.lottie.LottieAnimationView;
import android.app.Activity;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class unlock_activity extends Activity {
	LottieAnimationView btn_unlock;

	ImageView btn_settings;
	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.unlock);

		btn_unlock = findViewById(R.id.btn_unlock);
		btn_settings = findViewById(R.id.unlock_settings);
		btn_unlock.setOnClickListener(new View.OnClickListener() {
			@SuppressLint("Range")
			@Override
			public void onClick(View v) {
				Wifi_post postTestTask = new Wifi_post();
				btn_unlock.playAnimation();

				btn_unlock.addAnimatorListener(new Animator.AnimatorListener() {
					@Override
					public void onAnimationStart(@NonNull Animator animation) {

					}

					@Override
					public void onAnimationEnd(@NonNull Animator animation) {
						Intent intent = new Intent(unlock_activity.this, lock_activity.class);
						startActivity(intent);
						postTestTask.execute("on");
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
				Intent intent = new Intent(unlock_activity.this, settings_activity.class);
				startActivity(intent);
			}
		});
    }
}

