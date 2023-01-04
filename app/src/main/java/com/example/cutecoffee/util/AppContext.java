package com.example.cutecoffee.util;

import android.app.Application;

public class AppContext extends Application {

    private static AppContext instance;

    public static AppContext getInstance(){
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        this.instance = AppContext.this;
    }

}
