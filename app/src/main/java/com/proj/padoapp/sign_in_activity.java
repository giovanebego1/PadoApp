package com.proj.padoapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.Toast;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;

import androidx.annotation.Nullable;

public class sign_in_activity extends Activity {

	DBHelper dbHelper;
	EditText user_login, pwd_login;
	TextView esqueceu_senha, nao_tem_conta;
	ImageView done_button, btn_face, btn_apple, btn_google, btn_show_pwd, btn_s_h_pwd;

	@SuppressLint({"MissingInflatedId", "WrongViewCast"})
	@Override

	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sign_in);
		dbHelper = new DBHelper(this);
		user_login = findViewById(R.id.user_login);
		pwd_login = findViewById(R.id.pwd_login);
		esqueceu_senha = findViewById(R.id.esqueceu_senha);
		nao_tem_conta = findViewById(R.id.nao_tem_conta);
		done_button = findViewById(R.id.done_button);
		btn_show_pwd = findViewById(R.id.bnt_show_pwd);
		btn_face = findViewById(R.id.btn_face);
		btn_apple = findViewById(R.id.btn_apple);
		btn_google = findViewById(R.id.btn_google);
		//btn_s_h_pwd = findViewById(R.id.btn_show_hide_pwd);

		done_button.setOnClickListener(v -> {
			boolean isLoggedId = dbHelper.checkUser(user_login.getText().toString(), pwd_login.getText().toString());
			if (isLoggedId){
				Intent intent = new Intent(sign_in_activity.this, unlock_activity.class);
				startActivity(intent);
			}else
				Toast.makeText(sign_in_activity.this, "Erro ao fazer login", Toast.LENGTH_LONG).show();
		});

		nao_tem_conta.setOnClickListener(v -> {
			Intent intent = new Intent(sign_in_activity.this, create_new_account_activity.class);
			startActivity(intent);
		});
        /*
		btn_s_h_pwd.setOnClickListener(v -> {
			if (pwd_login.getTransformationMethod() == PasswordTransformationMethod.getInstance()){
				pwd_login.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
				btn_s_h_pwd.setImageResource(R.drawable.shape );
			}
			else {
				pwd_login.setTransformationMethod(PasswordTransformationMethod.getInstance());
				btn_s_h_pwd.setImageResource(R.drawable.shape);
			}
		});
         */
	}
}
	
	