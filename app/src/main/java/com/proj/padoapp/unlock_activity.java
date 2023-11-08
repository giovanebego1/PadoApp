package com.proj.padoapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.airbnb.lottie.LottieAnimationView;
import android.app.Activity;
import androidx.annotation.Nullable;

public class unlock_activity extends Activity {
	LottieAnimationView btn_unlock;
	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.unlock);

		btn_unlock = findViewById(R.id.btn_unlock);

		btn_unlock.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Wifi_post postTestTask = new Wifi_post();
				postTestTask.execute("on");
				btn_unlock.playAnimation();
				Intent intent = new Intent(unlock_activity.this, lock_activity.class);
				startActivity(intent);
			}
		});
	}
}

