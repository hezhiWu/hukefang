package com.yunwei.easydear.function.account;

import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;

import com.yunwei.easydear.R;
import com.yunwei.easydear.common.Constant;
import com.yunwei.easydear.common.handler.HandlerValue;
import com.yunwei.easydear.function.account.data.UserInfoEntity;
import com.yunwei.easydear.base.BaseActivity;
import com.yunwei.easydear.function.mainFuncations.MainActivity;
import com.yunwei.easydear.utils.ISkipActivityUtil;
import com.yunwei.easydear.utils.ISpfUtil;

import butterknife.ButterKnife;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.frame.function.account
 * @Description:启动界面
 * @date 2016/11/22 16:46
 */

public class SplashActivity extends BaseActivity implements AccountContract.LoginView {

//    @BindView(R.id.splash_iv)
//    ImageView splashIv;

    private LoginPresenter loginPresenter;

    @Override
    protected void dispatchMessage(Message msg) {
        super.dispatchMessage(msg);
        switch (msg.what) {
            case HandlerValue.START_PAGE_DELAYED:
                if (TextUtils.isEmpty(ISpfUtil.getValue(Constant.ACCESS_TOKEN_KEY, "").toString())) {
                    ISkipActivityUtil.startIntent(this, LoginActivity.class);
                } else {
                    ISkipActivityUtil.startIntent(this, MainActivity.class);
                }
                break;
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_splash);
        setToolbarVisibility(View.GONE);
        setSwipeEnabled(false);
        ButterKnife.bind(this);

        mHandler.sendEmptyMessageDelayed(HandlerValue.START_PAGE_DELAYED, Constant.THREE_SECONDES);

    }

    @Override
    protected void onPause() {
        super.onPause();
        super.finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (loginPresenter != null) {
            loginPresenter.cancelRequest();
        }
    }

    @Override
    public void showDialog() {

    }

    @Override
    public String getPassword() {
        return ISpfUtil.getValue(Constant.PSSWORD_KEY, "").toString();
    }

    @Override
    public String getAccount() {
        return ISpfUtil.getValue(Constant.ACCOUNT_KEY, "").toString();
    }

    @Override
    public void loginFailure(String error) {
        ISkipActivityUtil.startIntent(this, LoginActivity.class);
        finish();
    }

    @Override
    public void loginSuccess(UserInfoEntity entity) {
        ISkipActivityUtil.startIntent(this, MainActivity.class);
        finish();
    }

    @Override
    public void dismissDialog() {

    }
}
