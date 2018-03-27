package com.inni.base;

import android.app.Application;

import com.inni.framework.connection.IMServiceHelper;

/**
 * Created by shi on 2018/3/27.
 */

public class InniApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        IMServiceHelper.getInstance().init(this);
    }

}
