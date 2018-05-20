package com.yunwei.easydear.function.mainFuncations.membercenterFunction;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.yunwei.easydear.R;
import com.yunwei.easydear.base.BaseActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by LJH on 2017/1/16.
 */

public class MemberCenterActivity extends BaseActivity{

    private final String TAG = this.getClass().getSimpleName();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_membercenter);
        setToolbarVisibility(View.GONE);
//        setSwipeEnabled(false);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.member_center_back, R.id.member_center_back_to_business})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.member_center_back:
                onBackPressed();
                break;
            case R.id.member_center_back_to_business:
//                ISkipActivityUtil.startIntent(this, BusinessActivity.class);
                finish();
                break;
        }
    }
}
