package com.inni.im.aidl;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by qiang on 2018/3/26.
 */

public class IMEntry implements Parcelable {
    private Header header;
    private String data;

    public IMEntry(){

    }

    public IMEntry(Header header,String data){
        this.header = header;
        this.data = data;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(header,0);
        dest.writeString(data);
    }

    public static final Parcelable.Creator<IMEntry> CREATOR=new Parcelable.Creator<IMEntry>(){
        public IMEntry createFromParcel(Parcel in){
            return new IMEntry(in);
        }
        public IMEntry[] newArray(int size){
            return new IMEntry[size];
        }
    };

    private IMEntry(Parcel in){
        header = in.readParcelable(Header.class.getClassLoader());
        data = in.readString();
    }

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
