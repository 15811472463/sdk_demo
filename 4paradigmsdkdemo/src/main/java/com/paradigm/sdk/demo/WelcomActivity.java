package com.paradigm.sdk.demo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.sdk.a4paradigm.AdTagManager;

public class WelcomActivity extends DemoBaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcom_layout);
        TextView versionTv = findViewById(R.id.version);
        versionTv.setText(AdTagManager.getVersion());
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(WelcomActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 2000);
    }
}
