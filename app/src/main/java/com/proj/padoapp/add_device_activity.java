package com.proj.padoapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class add_device_activity extends Activity {

    ImageView settings_btn;
    RelativeLayout wifi_btn, bluetooth_btn, qr_code_btn;
    BlueHelper blueHelper;
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_device);
        blueHelper = new BlueHelper();
        settings_btn = findViewById(R.id.settings);
        wifi_btn = findViewById(R.id.wifi);
        bluetooth_btn = findViewById(R.id.bluetooth);
        qr_code_btn = findViewById(R.id.qr_code);


    }
}
	
	