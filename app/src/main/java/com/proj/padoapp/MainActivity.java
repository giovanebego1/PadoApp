package com.proj.padoapp;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;;
import com.airbnb.lottie.LottieAnimationView;

import java.util.concurrent.Executor;

public class MainActivity extends AppCompatActivity {
	DBHelper dbHelper;
	LottieAnimationView btn_switch;
	EditText user_login, pwd_login;
	TextView esqueceu_senha, nao_tem_conta;
	ImageView done_button, btn_face, btn_apple, btn_google;
	RelativeLayout frame_biometric;
	boolean isSwitchOn;
	int checkCredentials;
	AppConstants appConstants;
	private Executor executor;
	private BiometricPrompt biometricPrompt;
	private BiometricPrompt.PromptInfo promptInfo;
	@SuppressLint({"MissingInflatedId", "WrongViewCast"})
	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.sign_in);

		dbHelper = new DBHelper(this);

		appConstants = AppConstants.getInstance(this);

		user_login = findViewById(R.id.user_login);
		pwd_login = findViewById(R.id.pwd_login);
		esqueceu_senha = findViewById(R.id.esqueceu_senha);
		nao_tem_conta = findViewById(R.id.nao_tem_conta);
		done_button = findViewById(R.id.done_button);
		btn_face = findViewById(R.id.btn_face);
		btn_apple = findViewById(R.id.btn_apple);
		btn_google = findViewById(R.id.btn_google);
		btn_switch = findViewById(R.id.switch_btn);
		frame_biometric = findViewById(R.id.frame_biometric);

		Switchcase();

		executor = ContextCompat.getMainExecutor(this);
		biometricPrompt = new BiometricPrompt(MainActivity.this, executor, new BiometricPrompt.AuthenticationCallback() {
			@Override
			public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
				super.onAuthenticationError(errorCode, errString);
				Toast.makeText(MainActivity.this, "Erro " + errString, Toast.LENGTH_LONG).show();
			}

			@Override
			public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
				super.onAuthenticationSucceeded(result);
				Toast.makeText(MainActivity.this, "Sucessfull", Toast.LENGTH_LONG).show();
				Intent intent = new Intent(MainActivity.this, add_device_activity.class);
				startActivity(intent);
			}

			@Override
			public void onAuthenticationFailed() {
				super.onAuthenticationFailed();
				Toast.makeText(MainActivity.this, "AuthenticationFailed", Toast.LENGTH_LONG).show();
			}
		});

		promptInfo = new BiometricPrompt.PromptInfo.Builder()
				.setTitle("Autentificação Biometrica")
				.setSubtitle("Entre no App com sua digital ou facial")
				.setNegativeButtonText("Cancele")
				.build();

		nao_tem_conta.setOnClickListener(v -> {
			Intent intent = new Intent(MainActivity.this, create_new_account_activity.class);
			startActivity(intent);
		});

		btn_switch.setOnClickListener(new View.OnClickListener() {
			boolean prevState = isSwitchOn; // Salvar estado anterior para comparar posteriormente
			@Override
			public void onClick(View v) {
				if (isSwitchOn) {
					// switch is on, turning it off
					btn_switch.setMinAndMaxProgress(0.5f, 1.0f);
					isSwitchOn = true;

				} else {
					// switch is off, turning it on
					btn_switch.setMinAndMaxProgress(0.0f, 0.5f);
					isSwitchOn = false;
				}
				btn_switch.playAnimation();

				// Update isSwitchOn state immediately upon button click

				btn_switch.addAnimatorListener(new AnimatorListenerAdapter() {
					@Override
					public void onAnimationStart(Animator animation) {
						super.onAnimationStart(animation);
						Log.d("Switch", "Animation START: " + isSwitchOn);
					}

					@Override
					public void onAnimationEnd(Animator animation) {
						super.onAnimationEnd(animation);
						Log.d("Switch", "Animation END: " + isSwitchOn);
						isSwitchOn = !prevState;
						authenticate();
					}
				});
			}
		});
	}

	private void Switchcase(){
		switch (checkCredentials){
			case 1:
				Toast.makeText(MainActivity.this, "Usuário e senha estão corretos, mas o sistema apresentou falha durante o login", Toast.LENGTH_LONG).show();
				break;
			case 2:
				Toast.makeText(MainActivity.this, "Usuário não existe no sistema ou senha está incorreta", Toast.LENGTH_LONG).show();
				break;
			default:
				Toast.makeText(MainActivity.this, "Bem vindo ao Pado SmartLock, para acessar entre com usuário e senha ou cadastrese caso não tenha acesso.", Toast.LENGTH_LONG).show();
		}
	}
	private  void authenticate(){
		biometricPrompt.authenticate(promptInfo);
	}

	protected void onResume() {
		super.onResume();
		if (isSwitchOn == true){
			authenticate();
		} else {
			done_button.setOnClickListener(v -> {
				boolean isLoggedId = dbHelper.checkUser(user_login.getText().toString(), pwd_login.getText().toString());
				boolean checkUsername = dbHelper.checkUsername(user_login.getText().toString());
				boolean checkPwd = dbHelper.checkPassword(pwd_login.getText().toString());

				if (checkUsername && checkPwd == true) {
					if (isLoggedId) {
						if (appConstants.isFirstLogin()) {
							frame_biometric.setVisibility(View.INVISIBLE);
							appConstants.setFirstLogin(false);
						}else {
							frame_biometric.setVisibility(View.VISIBLE);
							NotificationHelper.createNotificationChannel(this);
							NotificationHelper.showNotification(this,
									"Habilite Biometria", "Lembrete para habilitar a opção de Biometria para acessar o App",
									"Habilite a autentificação de Biometria",
									"Clique nesta notificação, para habilitar a autentificação Biometrica de reconhecimento facil e digital");
							newIntent();
						}
					}else {
						checkCredentials = 1; // usuário e senha corretos, mas acesso falhou
					}
				} else {
					checkCredentials = 2; // usuário não existe ou senha incorreta
				}
				Switchcase();
			});
		}
	}

	private void newIntent(){
		Intent intent = new Intent(MainActivity.this, add_device_activity.class);
		startActivity(intent);
	}
}
