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
            writer = new PrintWriter(socket.getOutputStream());
            writer.write("TEST\n");
            writer.flush();

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void prepareHandler(){
        mSenderHandler = new Handler(getLooper(), new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                try {
                    Log.i("AAA", "Into the SEND method!!!");
                    if (socket != null && socket.isConnected()) {
                        if (msg.what == STRING_MESSAGE){
                            String str = (String) msg.obj;
                            writer.write(str);
                            writer.flush();
                        }
                    }

                } catch (Exception ex) { }

                return true;
            }
        });
    }

    public void sendMessage(String text) {
        Message message = mSenderHandler.obtainMessage(STRING_MESSAGE, text);
        mSenderHandler.sendMessage(message);
    }
}
