package com.proj.padoapp;

import android.content.Context;
import android.content.SharedPreferences;

public class AppConstants {
    private static final String PREF_NAME = "PREF_NAME";
    private static final String KEY_FIRST_LOGIN = "KEY_FIRST_LOGIN";

    private static AppConstants instance;
    private final SharedPreferences sharedPreferences;

    private AppConstants(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public static synchronized AppConstants getInstance(Context context) {
        if (instance == null) {
            instance = new AppConstants(context.getApplicationContext());
        }
        return instance;
    }

    public boolean isFirstLogin() {
        return sharedPreferences.getBoolean(KEY_FIRST_LOGIN, true);
    }

    public void setFirstLogin(boolean isFirstLogin) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(KEY_FIRST_LOGIN, isFirstLogin);
        editor.apply();
    }
}
