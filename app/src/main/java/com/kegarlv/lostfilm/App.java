package com.kegarlv.lostfilm;

import android.app.Application;
import android.content.Context;

import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by ivan on 04.02.17.
 */

public class App extends Application {
    private static App instance;

    public static String TAG = "myTag";
    public static App getInstance(){
        return instance;
    }

    public static Context getContext() {
        return instance.getApplicationContext();
    }
    @Override
    public void onCreate() {
        instance = this;
        super.onCreate();
    }
}
