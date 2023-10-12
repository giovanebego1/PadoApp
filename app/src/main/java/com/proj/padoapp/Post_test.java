package com.proj.padoapp;

import android.os.AsyncTask;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class Post_test extends AsyncTask<Void, Void, String> { ;

    @Override
    protected String doInBackground(Void... params) {
        try {
            // URL do servidor do ESP32
            URL url = new URL("http://192.168.4.1/"); // Substitua pelo endereço correto

            // Abra uma conexão HTTP
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");

            // Parâmetros POST
            String token = "ACB5"; // Substitua com seu token
            String command = "led=on"; // Comando LED
            String postData = "token=" + URLEncoder.encode(token, "UTF-8") + "&" +
                    "led=" + URLEncoder.encode(command, "UTF-8");


                    conn.setDoOutput(true);
            conn.setFixedLengthStreamingMode(postData.getBytes().length);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            // Envie os dados POST
            OutputStream os = conn.getOutputStream();
            os.write(postData.getBytes());
            os.flush();
            os.close();

            // Obtenha a resposta do servidor
            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Leitura da resposta (opcional)
                // InputStream is = conn.getInputStream();
                // ...

                return "Comando enviado com sucesso!";
            } else {
                return "Erro ao enviar comando.";
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "Erro de conexão.";
        }
    }

    @Override
    protected void onPostExecute(String result) {
        // Processar o resultado, se necessário
    }
}
