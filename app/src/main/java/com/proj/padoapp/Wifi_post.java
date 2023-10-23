package com.proj.padoapp;

import android.os.AsyncTask;
import android.util.Log;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class Wifi_post extends AsyncTask<String, Void, String> {

    private static final String SERVER_URL = "http://192.168.4.1/";
    private static final String TOKEN = "ACB5";

    @Override
    protected String doInBackground(String... params) {
        String command = params[0];
        try {
            URL url = new URL(SERVER_URL);
            Log.d("Post_test", "URL connection opened to: " + url.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");

            String token = TOKEN; // Substitua com seu token
            String postData = "token=" + URLEncoder.encode(token, "UTF-8") + "&" +
                    "led=" + URLEncoder.encode(command, "UTF-8");
            conn.setDoOutput(true);
            conn.setFixedLengthStreamingMode(postData.getBytes().length);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            OutputStream os = conn.getOutputStream();
            Log.d("Post_test", "POST data sent: " + postData);
            os.write(postData.getBytes());
            os.flush();
            os.close();

            int responseCode = conn.getResponseCode();
            Log.d("Post_test", "Response code: " + responseCode);
            if (responseCode == HttpURLConnection.HTTP_OK) {
                Log.d("Post_test", "Comando enviado com sucesso!");
                return "Comando enviado com sucesso!";
            } else {
                Log.d("Post_test", "Erro ao enviar comando.");
                return "Erro ao enviar comando.";
            }
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("Post_test", "Erro de conexão: " + e.getMessage());
            return "Erro de conexão.";
        }
    }

    @Override
    protected void onPostExecute(String result) {
        Log.d("Post_test", "onPostExecute result: " + result);
    }
}
