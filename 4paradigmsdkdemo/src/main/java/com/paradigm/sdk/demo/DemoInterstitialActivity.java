package com.paradigm.sdk.demo;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;

import com.sdk.a4paradigm.AdTagManager;
import com.sdk.a4paradigm.callBack.InterstitialCallBack;
import com.sdk.a4paradigm.bean.ErroInfo;
import com.sdk.a4paradigm.util.ToastUtil;

public class DemoInterstitialActivity extends DemoBaseActivity {
    private AdTagManager adManager;

    private Spinner spinner;

    private int style;

    private String tid;

    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo_interstitial_layout);
        spinner = findViewById(R.id.spinner);
        spinner.setVisibility(View.VISIBLE);
        editText = findViewById(R.id.edit);
        editText.setText("455453");
        tid = editText.getText().toString();
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                style = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        findViewById(R.id.banner_rquest).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adManager = new AdTagManager();
                tid = editText.getText().toString();
                if (!TextUtils.isEmpty(tid)) {
                    if (style == 0) {
                        adManager.init(DemoInterstitialActivity.this, tid);

                    } else {
                        adManager.init(DemoInterstitialActivity.this, "455453", false);

                    }
                } else {
                    ToastUtil.showToast(DemoInterstitialActivity.this, "tid 不能为空");
                }

                adManager.setOnInterstitialViewCallBack(new InterstitialCallBack() {
                    @Override
                    public void onReady() {
                        adManager.interstitialViewShow();
                    }

                    @Override
                    public void OnErro(ErroInfo erroInfo) {

                    }


                });
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
