package com.inni.im.protocol.impl;

import com.inni.im.aidl.IMEntry;
import com.inni.im.protocol.IConnector;
import com.inni.im.protocol.IDispatcher;

import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Created by shi on 2018/3/25.
 */

public class ConnectorImpl implements IConnector {
    private static ConnectorImpl connector;
    private Socket mSockect = null;
    private IDispatcher dispatcher = null;

    private ConnectorImpl(){
        try {
            mSockect = new Socket(ConnectionConfiger.HOST, ConnectionConfiger.PORT);
        }catch (IOException e){
        }
        dispatcher = new Dispatcher();

        receive();
    }

    public static ConnectorImpl getIntance(){
        if(connector == null){
            connector = new ConnectorImpl();
        }

        return connector;
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
        new Thread(new Reader(mSockect,dispatcher));
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
}
