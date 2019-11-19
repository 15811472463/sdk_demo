package com.paradigm.sdk.demo;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.sdk.a4paradigm.AdTagManager;
import com.sdk.a4paradigm.callBack.BannerCallBack;
import com.sdk.a4paradigm.bean.ErroInfo;
import com.sdk.a4paradigm.util.LogUtil;
import com.sdk.a4paradigm.util.ToastUtil;
import com.sdk.a4paradigm.view.BannerView;

public class DemoBannerActivity extends DemoBaseActivity {
    private BannerView bannerView;
    private AdTagManager adManager;
    private String tid;

    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo_banner_activity);
        bannerView = findViewById(R.id.banner_view);
        editText = findViewById(R.id.edit);
        // 每个tid 对应一个adTagManager 对象
        adManager = new AdTagManager();
        findViewById(R.id.banner_rquest).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 设置回调
                adManager.binderBannerView(bannerView, new BannerCallBack() {
                    @Override
                    public void onReady() {
                        bannerView.show();

                    }

                    @Override
                    public void OnErro(ErroInfo erroInfo) {
                        LogUtil.e(DemoBannerActivity.class, "code--->" + erroInfo.erroCode + ";info---->" + erroInfo.msg);
                        ToastUtil.showToast(DemoBannerActivity.this, "code->" + erroInfo.erroCode + ";info--->" + erroInfo.msg);

                    }
                });
                if (!TextUtils.isEmpty(editText.getText().toString())) {
                    tid = editText.getText().toString();
                    adManager.init(DemoBannerActivity.this, tid);
                }else{
                    ToastUtil.showToast(DemoBannerActivity.this,"tid 不能为空！");
                }

            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (adManager != null) {
            adManager.destory();
        }
    }
}
