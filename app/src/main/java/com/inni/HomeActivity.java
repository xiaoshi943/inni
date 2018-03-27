package com.inni;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.inni.framework.connection.IMServer;
import com.inni.framework.connection.bean.MsgEntry;
import com.inni.im.aidl.IIMServer;
import com.inni.im.aidl.IMEntry;
import com.inni.im.service.IMService;

public class HomeActivity extends AppCompatActivity {
    private EditText editText;
    private TextView sendBtn;

    private IMServer mIMServer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();

        mIMServer = new IMServer(this);
    }

    private void initView(){
        editText = (EditText) findViewById(R.id.edit_tv);
        sendBtn = (TextView) findViewById(R.id.send_btn);
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = editText.getText().toString();
                MsgEntry msgE = new MsgEntry();
                msgE.setInniID("1000");
                msgE.setMsg(msg);
                mIMServer.send(msgE);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mIMServer.unBindSerivce();
    }
}
