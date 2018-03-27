package com.inni.framework.connection;

import android.util.Log;

/**
 * IM消息接收帮助类
 * Created by qiang on 2018/3/27.
 */

public class BaseReceiverHelper implements IReceiverHelper {
    @Override
    public void receiver(String msg) {
        Log.e("msg",msg);
    }
}
