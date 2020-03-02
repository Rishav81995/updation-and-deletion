package com.example.pc26.myproject;

import android.app.Application;
import android.content.Context;

/**
 * Created by pc26 on 2/17/2020.
 */

public class MyApplication extends Application {
    public static MyApplication instance;
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
    @Override
    public Context getApplicationContext() {
        return super.getApplicationContext();
    }
    public static MyApplication getInstance() {
        return instance;
    }
}
