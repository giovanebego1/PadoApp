package com.proj.padoapp;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class Unlock_post extends AsyncTask<Void, Void, String> {

    @Override
    protected String doInBackground(Void... params) {
        try {
            // URL do servidor do ESP32
            URL url = new URL("http://192.168.4.1/"); // Substitua pelo endereço correto
            Log.d("Post_test", "URL connection opened to: " + url.toString());
            // Abra uma conexão HTTP
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");

            // Parâmetros POST
            String token = "ACB5"; // Substitua com seu token
            String command = "on"; // Comando LED
            String postData = "token=" + URLEncoder.encode(token, "UTF-8") + "&" +
                    "led=" + URLEncoder.encode(command, "UTF-8");


            conn.setDoOutput(true);
            conn.setFixedLengthStreamingMode(postData.getBytes().length);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            // Envie os dados POST
            OutputStream os = conn.getOutputStream();
            Log.d("Post_test", "POST data sent: " + postData);
            os.write(postData.getBytes());
            os.flush();
            os.close();

            // Obtenha a resposta do servidor
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
    protected void onPostExecute(String result) {
        Log.d("Post_test", "onPostExecute result: " + result);
    }
}
