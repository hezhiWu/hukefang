package com.yunwei.easydear.function.mainFuncations.qrcode;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.DecodeHintType;
import com.google.zxing.Result;
import com.google.zxing.client.android.AmbientLightManager;
import com.google.zxing.client.android.BeepManager;
import com.google.zxing.client.android.CaptureActivity;
import com.google.zxing.client.android.CaptureActivityHandler;
import com.google.zxing.client.android.InactivityTimer;
import com.google.zxing.client.android.IntentSource;
import com.google.zxing.client.android.ViewfinderView;
import com.google.zxing.client.android.ZxingConfig;
import com.google.zxing.client.android.camera.CameraManager;
import com.yunwei.easydear.base.BaseFragment;

import java.util.Collection;
import java.util.Map;

/**
 * Describe:
 * Author: hezhiWu
 * Date: 2017-01-08
 * Time: 13:25
 * Version:1.0
 */

public class ScanQrFragment extends BaseFragment {

    private static final String TAG = CaptureActivity.class.getSimpleName();

    private static final long DEFAULT_INTENT_RESULT_DURATION_MS = 1500L;
    private static final long BULK_MODE_SCAN_DELAY_MS = 1000L;

    private CameraManager cameraManager;
    private CaptureActivityHandler handler;
    private Result savedResultToShow;
    private ViewfinderView viewfinderView;
    private Result lastResult;
    private boolean hasSurface;// 用来控制重绘界面
    private boolean copyToClipboard = true;// 是否复制到剪切板
    private IntentSource source;
    private ZxingConfig config;// 配置信息类
    private Collection<BarcodeFormat> decodeFormats;
    private Map<DecodeHintType, ?> decodeHints;
    //长时间无活动自动销毁机制
    private InactivityTimer inactivityTimer;
    private BeepManager beepManager;//蜂鸣器
    private AmbientLightManager ambientLightManager;

    ViewfinderView getViewfinderView() {
        return viewfinderView;
    }

    public Handler getHandler() {
        return handler;
    }

    CameraManager getCameraManager() {
        return cameraManager;
    }

    private static ScanQrFragment fragment;

    public static ScanQrFragment newInstance() {
        if (fragment == null) {
            fragment = new ScanQrFragment();
        }
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        TextView textView = new TextView(getActivity());
//        textView.setText("扫一扫添加好友");
        return textView;
    }
}
