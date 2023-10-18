package com.proj.padoapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class unlock_activity extends Activity {
	ImageView btn_unlock;

	@SuppressLint("MissingInflatedId")
	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.unlock);
		btn_unlock = findViewById(R.id.btn_unlock);

		btn_unlock.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Post_test postTestTask = new Post_test();
				postTestTask.execute();
			}
		});

	}
}

