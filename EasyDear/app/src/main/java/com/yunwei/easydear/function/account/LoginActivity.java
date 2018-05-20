package com.yunwei.easydear.function.account;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.yunwei.easydear.R;
import com.yunwei.easydear.common.Constant;
import com.yunwei.easydear.common.dialog.DialogFactory;
import com.yunwei.easydear.function.account.data.UserInfoEntity;
import com.yunwei.easydear.function.account.data.soure.LoginRemoteRepo;
import com.yunwei.easydear.base.BaseActivity;
import com.yunwei.easydear.function.mainFuncations.MainActivity;
import com.yunwei.easydear.utils.ISkipActivityUtil;
import com.yunwei.easydear.utils.ISpfUtil;
import com.yunwei.easydear.utils.IStringUtils;
import com.yunwei.easydear.widget.ResetEditView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.frame.function.account
 * @Description:登录界面
 * @date 2016/11/25 14:02
 */

public class LoginActivity extends BaseActivity implements AccountContract.LoginView {

    @BindView(R.id.loginActivity_account_editView)
    ResetEditView loginActivityAccountEditView;
    @BindView(R.id.loginActivity_password_editView)
    ResetEditView loginActivityPasswordEditView;
    @BindView(R.id.loginActivity_login_button)
    Button loginActivityLoginButton;
    @BindView(R.id.loginActivity_login_vacode_edit)
    EditText mVaCode;

    private LoginPresenter mLoginPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_login);
        setToolbarVisibility(View.GONE);
        setSwipeEnabled(false);
        ButterKnife.bind(this);
        initUI();

        mLoginPresenter = new LoginPresenter(LoginRemoteRepo.newInstance(), this);
    }

    /**
     * 初始化界面
     */
    private void initUI() {
        String account = ISpfUtil.getValue(Constant.ACCOUNT_KEY, "").toString();
        String pwd = ISpfUtil.getValue(Constant.PSSWORD_KEY, "").toString();
        loginActivityAccountEditView.setText(account);
        loginActivityPasswordEditView.setText(pwd);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLoginPresenter.cancelRequest();
    }

    @OnClick({R.id.loginActivity_login_button,R.id.loginActivity_login_vacode_Button})
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.loginActivity_login_vacode_Button:
                if (TextUtils.isEmpty(loginActivityAccountEditView.getText())){
                    showToast("手机号码不能为空");
                    return;
                }

                if (!IStringUtils.isPhone(loginActivityAccountEditView.getText())){
                    showToast("请输入有效的手机号码");
                    return;
                }

                new LoginPresenter(new AccountContract.validateView() {
                    @Override
                    public void onStartSendValidateCode() {
                        loadDialog=DialogFactory.createLoadingDialog(LoginActivity.this);
                    }

                    @Override
                    public void onEndSendValidateCode() {
                        DialogFactory.dimissDialog(loadDialog);
                    }

                    @Override
                    public void getValidateCodeSuccess(String code) {
                        showToast("验证码发送成功，请注意查收手机短信");
                    }

                    @Override
                    public void getValidateCodeFailure(String error) {
                        showToast("验证码发送失败");
                    }

                    @Override
                    public String getMobile() {
                        return loginActivityAccountEditView.getText().toLowerCase();
                    }
                }).sendValidateCode();
                break;
            case R.id.loginActivity_login_button:
                loginAction();
                break;
        }
    }

    private void loginAction() {
        if (TextUtils.isEmpty(loginActivityAccountEditView.getText())) {
            showToast(R.string.account_not_null);
            return;
        }
        if (TextUtils.isEmpty(mVaCode.getText())) {
            showToast("验证码不能为空");
            return;
        }
        mLoginPresenter.login();
    }

    @Override
    public void showDialog() {
        loadDialog = DialogFactory.createLoadingDialog(this, R.string.login_dialog);
    }

    @Override
    public void dismissDialog() {
        DialogFactory.dimissDialog(loadDialog);
    }

    @Override
    public void loginSuccess(UserInfoEntity entity) {
        ISkipActivityUtil.startIntent(this, MainActivity.class);
        finish();
    }

    @Override
    public void loginFailure(String error) {
        showToast(error);
    }

    @Override
    public String getAccount() {
        return loginActivityAccountEditView.getText();
    }

    @Override
    public String getPassword() {
        return mVaCode.getText().toString();
    }
}
