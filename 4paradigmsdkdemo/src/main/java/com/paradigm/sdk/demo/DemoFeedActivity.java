package com.paradigm.sdk.demo;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.sdk.a4paradigm.AdTagManager;
import com.sdk.a4paradigm.callBack.FeedCallBack;
import com.sdk.a4paradigm.bean.Data;
import com.sdk.a4paradigm.bean.ErroInfo;
import com.sdk.a4paradigm.bean.FeedCallBackBean;
import com.sdk.a4paradigm.bean.Image;
import com.sdk.a4paradigm.util.LogUtil;
import com.sdk.a4paradigm.util.ToastUtil;

import java.util.ArrayList;

public class DemoFeedActivity extends DemoBaseActivity {
    AdTagManager adManager;


    private LinearLayout nativeLayout;


    private ImageView imageView;

    private TextView tilteTv;

    private TextView contentTv;


    private EditText editText;

    private String tid = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.native_demo);
        nativeLayout = findViewById(R.id.native_demo_layout);
        imageView = findViewById(R.id.img_src);
        tilteTv = findViewById(R.id.title);
        editText = findViewById(R.id.edit);
        editText.setText("455455");
        contentTv = findViewById(R.id.content);
        findViewById(R.id.banner_rquest).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (adManager != null) {
                    adManager.destory();
                }
                tid = editText.getText().toString();
                if (!TextUtils.isEmpty(tid)) {
                    adManager = new AdTagManager();
                    adManager.setNativeAdCallBack(new FeedCallBack() {

                        @Override
                        public void onMaterialsReady(final FeedCallBackBean feedCallBackBean) {
                            {
                                final ArrayList<Image> imgList = feedCallBackBean.getImglist();

                                LogUtil.e(DemoFeedActivity.class, "image.getUrl()---->" + imgList.get(0).getUrl());
                                ImageLoader.getInstance().displayImage(imgList.get(0).getUrl(), imageView, new ImageLoadingListener() {
                                    @Override
                                    public void onLoadingStarted(String imageUri, View view) {

                                    }

                                    @Override
                                    public void onLoadingFailed(String imageUri, View view, FailReason failReason) {

                                    }

                                    @Override
                                    public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                                        LogUtil.e(DemoFeedActivity.class, "111111111111111111111111");
                                        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                                        imageView.setImageBitmap(loadedImage);
                                        adManager.sendNativeDisplayMsg();
                                        String title;
                                        ArrayList<String> titleList = feedCallBackBean.getTitleList();
                                        if (titleList != null && titleList.size() > 0)
                                            title = titleList.get(0);
                                        else title = "";
                                        if (tilteTv != null) {
                                            tilteTv.setText(title);
                                        }
                                        ArrayList<Data> dataList = feedCallBackBean.getDataList();
                                        if (dataList.size() > 0) {
                                            if (contentTv != null && !TextUtils.isEmpty(dataList.get(0).getValue())) {
                                                contentTv.setText(dataList.get(0).getValue());
                                            }
                                        }


                                    }

                                    @Override
                                    public void onLoadingCancelled(String imageUri, View view) {

                                    }
                                });


                            }
                        }

                        @Override
                        public void OnErro(ErroInfo erroInfo) {

                        }


                    });

                    adManager.setOnTouchListenerForNativeAd(imageView);
                    adManager.setOnTouchListenerForNativeAd(nativeLayout);
                    adManager.init(DemoFeedActivity.this, tid);
                } else {
                    ToastUtil.showToast(DemoFeedActivity.this, "tid 不能为空!");
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
