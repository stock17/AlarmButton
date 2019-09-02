package com.yurima.conductor;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SenderThread extends HandlerThread {

    ServerSocket serverSocket;
    Socket socket;
    PrintWriter writer;
    private Handler mSenderHandler;

    private static final int STRING_MESSAGE = 0;

    public SenderThread(String name) {
        super(name);
    }

    @Override
    protected void onLooperPrepared() {
        super.onLooperPrepared();
        try {
            serverSocket = new ServerSocket (9000);
            socket = serverSocket.accept();
            Log.i("SenderThread", "Socket is connected!!!");
            writer = new PrintWriter(socket.getOutputStream(), true);
            writer.println("TEST");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void prepareHandler(){
        mSenderHandler = new Handler(this.getLooper()) {
            @Override
            public void handleMessage(Message msg) {
                Log.i("AAA", "Into the SEND method!!!");
                try {
                    if (socket != null && socket.isConnected()) {
                        if (msg.what == STRING_MESSAGE){
                            String str = (String) msg.obj;
                            writer.println(str);
                        }
                    }

                } catch (Exception ex) { }
            }
        };
    }

    public void sendMessage(String text) {
//        Message message = mSenderHandler.obtainMessage(STRING_MESSAGE, text);
        Message message = new Message();
        message.what = STRING_MESSAGE;
        message.obj = text;
        mSenderHandler.sendMessage(message);
    }
}
