package com.paradigm.sdk.demo;

import android.app.Activity;
import android.os.Bundle;

import com.umeng.analytics.MobclickAgent;

public class DemoBaseActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
}
