package com.proj.padoapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.Nullable;

public class create_new_account_activity extends Activity {
	EditText full_name, us_email, us_phone, us_pass;
	ImageView btn_con_regis, btn_face, btn_google, btn_apple;
	DBHelper dbHelper;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.create_new_account);
		full_name = findViewById(R.id.full_name);
		us_email = findViewById(R.id.user_email);
		us_phone = findViewById(R.id.phone);
		us_pass = findViewById(R.id.pwd_login);
		btn_con_regis = findViewById(R.id.conclued_bt);
		dbHelper = new DBHelper(this);

		btn_con_regis.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String user, pwd, us_name, phone;
				user = us_email.getText().toString();
				pwd = us_pass.getText().toString();
				us_name = full_name.getText().toString();
				phone = us_phone.getText().toString();

				if (user.equals("") || pwd.equals("") || us_name.equals("") || phone.equals("")){
					Toast.makeText(create_new_account_activity.this,"Por favor, preencha todos os campos", Toast.LENGTH_LONG).show();
				}else {
					if(Pass_val.isValid(pwd)) {
						if (Email_val.isValid(user)) {
							if (dbHelper.checkUsername(user)) {
								Toast.makeText(create_new_account_activity.this, "Usuário já existe", Toast.LENGTH_LONG).show();
							}
							boolean registerStatus = dbHelper.insertData(user, pwd, us_name, phone);

							if (registerStatus) {
								Toast.makeText(create_new_account_activity.this, "Cadastro Concluído", Toast.LENGTH_LONG).show();
								Intent intent = new Intent(create_new_account_activity.this, MainActivity.class);
								startActivity(intent);
							} else {
								Toast.makeText(create_new_account_activity.this, "Não foi possível realizar o cadastro", Toast.LENGTH_LONG).show();
							}
						}else {
							Toast.makeText(create_new_account_activity.this, "E-mail inserido é inválido, por favor inserir um e-mail valido.", Toast.LENGTH_LONG).show();
						}
					}else {
						Toast.makeText(create_new_account_activity.this, "Senha inválida. A senha deve atender aos requisitos especificados.", Toast.LENGTH_LONG).show();
					}
				}
            }
		});
		btn_face = findViewById(R.id.facebook_button);
		btn_face.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

            }
		});
		btn_apple = findViewById(R.id.apple_button);
		btn_apple.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

            }
		});
		btn_google = findViewById(R.id.google_button);
		btn_google.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

            }
		});
    }


}
	
	