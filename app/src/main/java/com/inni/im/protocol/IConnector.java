package com.inni.im.protocol;

/**
 * Created by shi on 2018/3/25.
 */

public interface IConnector {
    void connect();
    void accept();
    void receive();
    void send();
}
