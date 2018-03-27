package com.inni.iminni;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.inni.R;
import com.inni.framework.connection.IMServiceHelper;
import com.inni.bean.MsgEntry;

public class HomeActivity extends AppCompatActivity {
    private EditText editText;
    private TextView sendBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
        IMServiceHelper.getInstance().init(this);
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
                IMServiceHelper.getInstance().send(msgE);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
