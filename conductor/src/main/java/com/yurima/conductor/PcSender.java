package com.yurima.conductor;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class PcSender implements Runnable {

    ServerSocket serverSocket;
    Socket socket;
    PrintWriter writer;

    @Override
    public void run() {
        try {
            serverSocket = new ServerSocket (9000);
            socket = serverSocket.accept();
            Log.i("PcSender", "Socket is connected!!!");
            writer = new PrintWriter(socket.getOutputStream());
            writer.write("TEST\n");
            writer.flush();

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void sendMessage(String msg) {
        try {

            Log.i("AAA", "Into the SEND method!!!");
            if (socket != null && socket.isConnected()) {
                writer.write(msg);
                writer.flush();
            }

        } catch (Exception ex) { }
    }
}
