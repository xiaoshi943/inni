package com.inni.im.protocol.impl;

import com.inni.im.protocol.IDispatcher;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by shi on 2018/3/26.
 */

public class Reader implements Runnable {
    private Socket socket = null;
    private IDispatcher dispatcher = null;
    private BufferedReader bufReader = null;

    public Reader(Socket socket , IDispatcher dispatcher){
        this.socket = socket;
        this.dispatcher = dispatcher;
        try {
            bufReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void run() {
        String msg = "";
        try {
            while ((msg = bufReader.readLine()) != null){
                dispatcher.dispatch(msg);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
