package com.proj.padoapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RelativeLayout;
import androidx.appcompat.app.AppCompatActivity;
public class settings_activity extends AppCompatActivity {
	public RelativeLayout back_btn, logout_btn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settings);

		back_btn = findViewById(R.id.back_btn);
		back_btn.setOnClickListener(v -> {
			Intent intent = new Intent(settings_activity.this, unlock_activity.class);
			startActivity(intent);
		});

		logout_btn = findViewById(R.id.logoff_button);
		logout_btn.setOnClickListener(v -> {
			Intent intent = new Intent(settings_activity.this, MainActivity.class);
			startActivity(intent);
		});
	}
}
