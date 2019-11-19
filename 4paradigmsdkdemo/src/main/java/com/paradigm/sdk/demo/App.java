package com.paradigm.sdk.demo;

import android.app.Application;

import com.umeng.analytics.MobclickAgent;
import com.umeng.commonsdk.UMConfigure;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // 5dce06d5570df338ee00025c
        UMConfigure.init(this, UMConfigure.DEVICE_TYPE_PHONE, "");
        //  MobclickAgent.setCatchUncaughtExceptions(true);
        //MobclickAgent.openActivityDurationTrack(false);
        MobclickAgent.setPageCollectionMode(MobclickAgent.PageMode.LEGACY_AUTO);
        if (BuildConfig.DEBUG) {
            MobclickAgent.setDebugMode(true);
        } else {
            MobclickAgent.setDebugMode(false);
        }
    }
}
