package com.yunwei.easydear.function.account;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.EditText;
import android.widget.TextView;

import com.yunwei.easydear.R;
import com.yunwei.easydear.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Describe:
 * Author: hezhiWu
 * Date: 2017-01-08
 * Time: 10:41
 * Version:1.0
 */

public class RestPasswordActivity extends BaseActivity {

    @BindView(R.id.restPassword_old_pwd_et)
    TextView restPasswordOldPwdEt;
    @BindView(R.id.restPassword_new_pwd_et)
    EditText restPasswordNewPwdEt;
    @BindView(R.id.restPassword_confirm_pwd_et)
    EditText restPasswordConfirmPwdEt;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_reset_password);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.restPassword_submit_button)
    public void onClick() {
    }
}
