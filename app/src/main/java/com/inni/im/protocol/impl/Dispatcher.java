package com.inni.im.protocol.impl;

import android.content.Context;
import android.content.Intent;

import com.inni.im.protocol.IDispatcher;

/**
 * Created by shi on 2018/3/26.
 */

public class Dispatcher implements IDispatcher {
    private Context mContext;

    public Dispatcher(Context context){
        mContext = context;
    }
    @Override
    public void dispatch(String msg) {
        Intent intent = new Intent("com.inni.IM_MESSAGE");
        intent.putExtra("imMsg",msg);
        mContext.sendBroadcast(intent);
    }
}
