package com.inni.framework.connection;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;

import com.inni.framework.connection.bean.MsgEntry;
import com.inni.im.aidl.Header;
import com.inni.im.aidl.IIMServer;
import com.inni.im.aidl.IMEntry;
import com.inni.im.service.IMService;

/**
 * Created by qiang on 2018/3/26.
 */

public class IMServer {
    private Activity mContext;
    private IIMServer mIMServer;
    private ServiceConnection connection;

    public IMServer(Activity context){
        mContext = context;
        init();
    }

    //获取远程服务代理对象
    private void init() {
        connection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                mIMServer = IIMServer.Stub.asInterface(service);
            }
            @Override
            public void onServiceDisconnected(ComponentName name) {
            }
        };

        mContext.bindService(new Intent(mContext, IMService.class), connection, Context.BIND_AUTO_CREATE);
    }

    public void send(MsgEntry msgEntry){
        if(msgEntry != null){
            Header header = new Header("",msgEntry.getInniID());
            IMEntry entry = new IMEntry(header,msgEntry.getMsg());
            try {
                mIMServer.send(entry);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void unBindSerivce(){
        mContext.unbindService(connection);
    }
}
