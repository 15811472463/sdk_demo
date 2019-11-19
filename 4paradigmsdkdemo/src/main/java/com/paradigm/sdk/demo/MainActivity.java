package com.paradigm.sdk.demo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.hjq.permissions.OnPermission;
import com.hjq.permissions.Permission;
import com.hjq.permissions.XXPermissions;
import com.sdk.a4paradigm.AdTagManager;
import com.sdk.a4paradigm.util.ToastUtil;
import com.umeng.analytics.MobclickAgent;

import java.util.List;

public class MainActivity extends DemoBaseActivity{

    private boolean isGrantAllPermission;

    private AdTagManager adTagManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.permission_bt).setOnClickListener(onClickListener);
        findViewById(R.id.banner_btn).setOnClickListener(onClickListener);
        findViewById(R.id.table_btn).setOnClickListener(onClickListener);
        findViewById(R.id.splash_btn).setOnClickListener(onClickListener);
        findViewById(R.id.native_btn).setOnClickListener(onClickListener);
        findViewById(R.id.erro_btn).setOnClickListener(onClickListener);


        XXPermissions.with(this).constantRequest().permission(Permission.Group.LOCATION, Permission.Group.STORAGE, new String[]{Permission.REQUEST_INSTALL_PACKAGES, Permission.READ_PHONE_STATE}).request(new OnPermission() {
            @Override
            public void hasPermission(List<String> granted, boolean isAll) {
                isGrantAllPermission = isAll;
            }

            @Override
            public void noPermission(List<String> denied, boolean quick) {
                isGrantAllPermission=false;

            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(MainActivity.this);
    }


    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(MainActivity.this);
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.permission_bt:
                    requestPersmission(MainActivity.this);
                    break;
                case R.id.banner_btn:
                    toStartActivity(MainActivity.this, DemoBannerActivity.class);
                    break;
                case R.id.splash_btn:
                    toStartActivity(MainActivity.this, DemoSplashActivity.class);
                    break;
                case R.id.native_btn:
                    toStartActivity(MainActivity.this, DemoFeedActivity.class);
                    break;

                case R.id.table_btn:
                    toStartActivity(MainActivity.this, DemoInterstitialActivity.class);
                    break;

                case R.id.erro_btn:
                    adTagManager.outPutErrro(1, "wqeq");

                    break;
            }

        }
    };


    public void toStartActivity(Context context, Class<?> t) {
        if (isGrantAllPermission) {
            Intent intent = new Intent(context, t);
            context.startActivity(intent);
        } else {
            ToastUtil.showToast(context, "为了保证你的收益，先允许广告所需的必须权限否则不能使用广告sdk");
        }

    }


    public void requestPersmission(Context context) {
        XXPermissions.with(this).constantRequest().permission(Permission.Group.LOCATION, Permission.Group.STORAGE, new String[]{Permission.REQUEST_INSTALL_PACKAGES, Permission.READ_PHONE_STATE}).request(new OnPermission() {
            @Override
            public void hasPermission(List<String> granted, boolean isAll) {
                isGrantAllPermission = isAll;
            }

            @Override
            public void noPermission(List<String> denied, boolean quick) {

            }
        });
    }


}