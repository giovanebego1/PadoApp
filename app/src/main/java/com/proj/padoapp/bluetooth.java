package com.proj.padoapp;

/*

import javax.bluetooth.*;
import javax.microedition.io.Connector;
import javax.microedition.io.StreamConnection;
import javax.microedition.io.StreamConnectionNotifier;

public class bluetooth {

    public static void main(String[] args) throws IOException, InterruptedException {

        UUID uuid = new UUID("0000110100001000800000805F9B34FB", false);
        String serverURL = "btspp://localhost:" + uuid + ";name=SampleServer";

        StreamConnectionNotifier streamConnNotifier = (StreamConnectionNotifier) Connector.open(serverURL);

        System.out.println("Esperando conexão bluetooth...");
        StreamConnection connection = streamConnNotifier.acceptAndOpen();

        System.out.println("Conexão estabelecida. Aguardando dados...");

        InputStream inStream = connection.openInputStream();
        BufferedReader bReader = new BufferedReader(new InputStreamReader(inStream));

        String lineRead = bReader.readLine();
        System.out.println("Mensagem recebida do ESP32: " + lineRead);

        // Código para enviar um POST para o ESP32
        String message = "on"; // ou "off" dependendo do comando que você quer enviar
        OutputStream outStream = connection.openOutputStream();
        PrintWriter pWriter = new PrintWriter(new OutputStreamWriter(outStream));
        pWriter.write("POST / HTTP/1.0\r\n");
        pWriter.write("Content-Length: " + message.length() + "\r\n");
        pWriter.write("\r\n");
        pWriter.write(message);
        pWriter.flush();
        pWriter.close();

        inStream.close();
        connection.close();
    }
}
 */