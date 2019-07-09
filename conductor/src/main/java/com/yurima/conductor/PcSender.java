package com.yurima.conductor;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import java.io.PrintWriter;
import java.net.Socket;


public class PcSender implements Runnable {

    Socket out;

    @Override
    public void run() {
        try {
            out = new Socket ("127.0.0.1", 9000);
        } catch (Exception e){}
    }

    public void sendMessage(String msg) {
        try {
            Log.i("AAA", "Into the SEND method!!!");
            if (out == null)
                out = new Socket("127.0.0.1", 9000);
            PrintWriter writer = new PrintWriter(out.getOutputStream());
            writer.write(msg);
            writer.flush();

        } catch (Exception ex) { }
    }
}
