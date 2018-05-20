package com.yunwei.easydear.function.mainFuncations.mineFuncation.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yunwei.easydear.R;
import com.yunwei.easydear.base.BaseFragment;
import com.yunwei.easydear.common.dialog.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Describe:
 * Author: hezhiWu
 * Date: 2017-02-18
 * Time: 11:45
 * Version:1.0
 */

public class ModiflyPasswordFragment extends BaseFragment {
    public static final String MODIFLY_PASSWORD_FLAG = "modifly_pwd";

    private static ModiflyPasswordFragment fragment;
    @BindView(R.id.account)
    TextView account;
    @BindView(R.id.pwd_edit_old_edit)
    EditText pwdEditOldEdit;
    @BindView(R.id.account_close_ib)
    ImageButton accountCloseIb;
    @BindView(R.id.pwd)
    TextView pwd;
    @BindView(R.id.pwd_edit_new_edit)
    EditText pwdEditNewEdit;
    @BindView(R.id.pwd_close_ib)
    ImageButton pwdCloseIb;
    @BindView(R.id.comfir_pwd)
    TextView comfirPwd;
    @BindView(R.id.pwd_edit_comfir_edit)
    EditText pwdEditComfirEdit;
    @BindView(R.id.login_layout)
    LinearLayout loginLayout;

    public static ModiflyPasswordFragment newInstance() {
        if (fragment == null) {
            fragment = new ModiflyPasswordFragment();
        }
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_pwd_edit, null);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @OnClick({R.id.account_close_ib, R.id.pwd_close_ib})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.account_close_ib:
                break;
            case R.id.pwd_close_ib:
                break;
        }
    }

    public String newPasswordText() {
        if (TextUtils.isEmpty(pwdEditOldEdit.getText().toString())) {
            ToastUtil.showToast(getActivity(), "原密码不能为空");
            return "";
        }

        if (TextUtils.isEmpty(pwdEditNewEdit.getText().toString())) {
            ToastUtil.showToast(getActivity(), "新密码不能为空");
            return "";
        }

        if (!pwdEditComfirEdit.getText().toString().equals(pwdEditNewEdit.getText().toString())) {
            ToastUtil.showToast(getActivity(), "再次输入的新密码不一致");
            return "";
        }
        return pwdEditComfirEdit.getText().toString();
    }
}
