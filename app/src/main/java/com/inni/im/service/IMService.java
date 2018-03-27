package com.inni.im.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.inni.im.aidl.IIMServer;
import com.inni.im.aidl.IMEntry;
import com.inni.im.protocol.IDispatcher;
import com.inni.im.protocol.impl.ConnectorImpl;
import com.inni.im.protocol.impl.Dispatcher;

/**
 * Created by shi on 2018/3/26.
 */

public class IMService extends Service{
    private Binder binder;
    private ConnectorImpl mConnector;
    @Override
    public void onCreate() {
        super.onCreate();
        binder = new IMServerImpl();
        mConnector = new ConnectorImpl(new Dispatcher(getApplication()));
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    private class IMServerImpl extends IIMServer.Stub{
        @Override
        public void send(IMEntry entry) throws RemoteException {

            String json = JSONObject.toJSONString(entry);
           //mConnector.send(json);

            //=====测试=============================
            Log.e("pppp",json);
            Intent intent = new Intent("com.inni.IM_MESSAGE");
            intent.putExtra("imMsg",json);
            getApplication().sendBroadcast(intent);
        }
    }
}
