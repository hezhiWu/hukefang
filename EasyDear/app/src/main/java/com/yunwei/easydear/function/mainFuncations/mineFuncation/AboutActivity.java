package com.yunwei.easydear.function.mainFuncations.mineFuncation;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.yunwei.easydear.R;
import com.yunwei.easydear.base.BaseActivity;

/**
 * Describe:
 * Author: hezhiWu
 * Date: 2017-02-26
 * Time: 15:52
 * Version:1.0
 */

public class AboutActivity extends BaseActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        setToolbarTitle("关于易兑");
    }
}
