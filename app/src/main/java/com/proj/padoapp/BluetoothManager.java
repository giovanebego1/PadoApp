package com.proj.padoapp;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.util.Log;

import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

public class BluetoothManager {

    private static final String TAG = "BluetoothManager";
    private static final UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB"); // UUID do serviço Bluetooth SPP

    private BluetoothAdapter bluetoothAdapter;
    private BluetoothSocket socket;
    private OutputStream outputStream;

    public BluetoothManager(BluetoothAdapter bluetoothAdapter) {
        this.bluetoothAdapter = bluetoothAdapter;
    }

    public boolean connectToESP32(BluetoothDevice device) {
        try {
            socket = device.createRfcommSocketToServiceRecord(MY_UUID);
            socket.connect();
            outputStream = socket.getOutputStream();
            return true;
        } catch (IOException e) {
            Log.e(TAG, "Error occurred while connecting: " + e.getMessage());
            return false;
        }
    }

    public void sendCommand(String command) {
        if (outputStream != null) {
            try {
                outputStream.write(command.getBytes());
            } catch (IOException e) {
                Log.e(TAG, "Error occurred while sending data: " + e.getMessage());
            }
        }
    }

    public void disconnect() {
        if (socket != null) {
            try {
                socket.close();
            } catch (IOException e) {
                Log.e(TAG, "Error occurred while closing the socket: " + e.getMessage());
            }
        }
    }
}
