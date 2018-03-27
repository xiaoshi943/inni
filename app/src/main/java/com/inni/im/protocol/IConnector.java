package com.inni.im.protocol;

import com.inni.im.aidl.IMEntry;

/**
 * Created by shi on 2018/3/25.
 */

public interface IConnector {
    void connect();
    void receive();
    void send(IMEntry entry);
}
