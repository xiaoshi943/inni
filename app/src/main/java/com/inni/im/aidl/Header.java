package com.inni.im.aidl;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by qiang on 2018/3/26.
 */

public class Header implements Parcelable{
    private String clientIP;
    private String inniID;

    public Header(String clientIP,String inniID){
        this.clientIP = clientIP;
        this.inniID = inniID;
    }

    public static final Parcelable.Creator<Header> CREATOR=new Parcelable.Creator<Header>(){
        public Header createFromParcel(Parcel in){
            return new Header(in);
        }
        public Header[] newArray(int size){
            return new Header[size];
        }
    };

    private Header(Parcel in){
        clientIP = in.readString();
        inniID = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(clientIP);
        dest.writeString(inniID);
    }

    public String getClientIP() {
        return clientIP;
    }

    public void setClientIP(String clientIP) {
        this.clientIP = clientIP;
    }

    public String getInniID() {
        return inniID;
    }

    public void setInniID(String inniID) {
        this.inniID = inniID;
    }
}
