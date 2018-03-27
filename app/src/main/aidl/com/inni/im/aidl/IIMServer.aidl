// IIMServer.aidl
package com.inni.im.aidl;

import com.inni.im.aidl.IMEntry;

// Declare any non-default types here with import statements

interface IIMServer {
    void send(in IMEntry entry);
}
