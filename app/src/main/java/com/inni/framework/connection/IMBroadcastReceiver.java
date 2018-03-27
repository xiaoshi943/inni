package com.inni.framework.connection;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * IM广播接收
 * Created by shi on 2018/3/27.
 */

public class IMBroadcastReceiver extends BroadcastReceiver {
    private IReceiverHelper receiverHelper;

    public IMBroadcastReceiver() {
        super();
        receiverHelper = new BaseReceiverHelper();
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent != null){
        receiverHelper.receiver(intent.getStringExtra("imMsg"));
        }
    }
}
