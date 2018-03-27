package com.inni.im.protocol.impl;

import android.util.Log;

import com.inni.im.aidl.IMEntry;
import com.inni.im.protocol.IConnector;
import com.inni.im.protocol.IDispatcher;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Created by shi on 2018/3/25.
 */

public class ConnectorImpl implements IConnector {
    private Socket mSockect = null;
    private IDispatcher mDispatcher = null;

    public ConnectorImpl(IDispatcher dispatcher){
        mDispatcher = dispatcher;
        try {
            mSockect = new Socket(ConnectionConfiger.HOST, ConnectionConfiger.PORT);
        }catch (IOException e){
            e.printStackTrace();
        }
       // receive();
    }

    @Override
    public void connect() {
        try {
            mSockect.connect(new InetSocketAddress(ConnectionConfiger.HOST, ConnectionConfiger.PORT) ,10*1000);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void receive() {
        new Thread(new Reader(mSockect,mDispatcher));
    }

    @Override
    public void send(IMEntry entry) {
        if(entry == null){
            return;
        }

        String msg = entry.toString();
        try {
            PrintStream printStream = new PrintStream(mSockect.getOutputStream());
            printStream.print(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
}
