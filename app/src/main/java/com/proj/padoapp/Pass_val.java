package com.proj.padoapp;
import android.util.Log;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Pass_val {
    public static void valid (String[] args){

    }
    public static boolean isValid(String password){
        Pattern p = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&+=()<>~`])(?=\\S+$).{8,20}$");
        Matcher m = p.matcher(password);
        Log.d("Debug", "Validating password: " + password);
        if(m.find()) return true;
        return false;
    }
}
