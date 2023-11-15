package com.proj.padoapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

public class add_device_activity extends AppCompatActivity {
    ImageView config_btn;
    RelativeLayout bluetooth_btn, wifi_btn, qr_code_btn;
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_device);

        wifi_btn = findViewById(R.id.wifi);
        bluetooth_btn = findViewById(R.id.bluetooth);
        qr_code_btn = findViewById(R.id.qr_code);
        config_btn = findViewById(R.id.config);

        config_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(add_device_activity.this, settings_activity.class);
                startActivity(intent);
            }
        });

        wifi_btn.setOnClickListener(v ->{
            Intent intent = new Intent(add_device_activity.this, searching_wifi.class);
            startActivity(intent);
            // use that part to save the option off user chose to search the devices for wifi or bluetooth.
        });

        bluetooth_btn.setOnClickListener(v ->{
            Intent intent = new Intent(add_device_activity.this, searching_bluetooth.class);
            startActivity(intent);
            // use that part to save the option off user chose to search the devices for wifi or bluetooth.
        });

        qr_code_btn.setOnClickListener(v -> {
            scanCode();
        });
    }

    public void scanCode(){
        ScanOptions options = new ScanOptions();
        options.setPrompt("Volume up to flash on");
        options.setBeepEnabled(true);
        options.setOrientationLocked(true);
        barLauncher.launch(options);
    }

    ActivityResultLauncher<ScanOptions> barLauncher = registerForActivityResult(new ScanContract(), result -> {
        if (result.getContents() !=null){
            AlertDialog.Builder builder = new AlertDialog.Builder(add_device_activity.this);
            builder.setTitle("Result");
            builder.setMessage(result.getContents());
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            }).show();
        }
    });
}
	
	