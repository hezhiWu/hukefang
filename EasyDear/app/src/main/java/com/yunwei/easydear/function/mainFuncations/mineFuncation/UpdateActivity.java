package com.yunwei.easydear.function.mainFuncations.mineFuncation;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.yunwei.easydear.R;
import com.yunwei.easydear.base.BaseActivity;
import com.yunwei.easydear.base.DataApplication;
import com.yunwei.easydear.common.Constant;
import com.yunwei.easydear.common.dialog.DialogFactory;
import com.yunwei.easydear.function.account.AccountContract;
import com.yunwei.easydear.function.account.LoginPresenter;
import com.yunwei.easydear.function.account.data.UserInfoEntity;
import com.yunwei.easydear.function.mainFuncations.mineFuncation.fragment.ModiflyNickFragment;
import com.yunwei.easydear.function.mainFuncations.mineFuncation.fragment.ModiflyPasswordFragment;
import com.yunwei.easydear.utils.ISkipActivityUtil;
import com.yunwei.easydear.utils.ISpfUtil;

/**
 * Describe:
 * Author: hezhiWu
 * Date: 2017-02-18
 * Time: 11:40
 * Version:1.0
 */

public class UpdateActivity extends BaseActivity implements AccountContract.UpdatePasswordView{
    public final static String FROM_FLAG = "from_flag";
    public final static String TITLE_FLAG = "title_flag";

    private String fromValue;

    private LoginPresenter updatePwdPresenter;

    public static void startIntent(Activity context, String fromFlag, String titleText, int requestCode) {
        Bundle bundle = new Bundle();
        bundle.putString(FROM_FLAG, fromFlag);
        bundle.putString(TITLE_FLAG, titleText);
        ISkipActivityUtil.startIntentForResult(context, UpdateActivity.class, bundle, requestCode);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_update_info);
        setToolbarTitle(getIntent().getStringExtra(TITLE_FLAG));
        fromValue = getIntent().getStringExtra(FROM_FLAG);
        initView();
        updatePwdPresenter=new LoginPresenter(this);
    }

    private void initView() {
        if (TextUtils.isEmpty(fromValue)) {
            return;
        }
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        if (fromValue.equals(ModiflyNickFragment.MODIFLY_NICK_FLAG)) {
            setToolbarRightText("修改");
            transaction.replace(R.id.activity_update_container, ModiflyNickFragment.newInstance());
        } else if (fromValue.equals(ModiflyPasswordFragment.MODIFLY_PASSWORD_FLAG)) {
            setToolbarRightText("修改");
            transaction.replace(R.id.activity_update_container, ModiflyPasswordFragment.newInstance());
        }
        transaction.commit();
    }

    @Override
    public void onClickToolbarRightLayout() {
        super.onClickToolbarRightLayout();
        if (fromValue.equals(ModiflyNickFragment.MODIFLY_NICK_FLAG)) {//昵称修改返回
            final String nick = ModiflyNickFragment.newInstance().getEditViewText();
            if (!TextUtils.isEmpty(nick)) {
               new LoginPresenter(new AccountContract.UpdateNickNameView() {
                   @Override
                   public void onShowDialog() {
                       loadDialog= DialogFactory.createLoadingDialog(UpdateActivity.this,"昵称修改...");
                   }

                   @Override
                   public void onDissmisDialog() {
                       DialogFactory.dimissDialog(loadDialog);
                   }

                   @Override
                   public String getUserNo() {
                       return DataApplication.getInstance().getUserInfoEntity().getUserNo();
                   }

                   @Override
                   public String getNickName() {
                       return nick;
                   }

                   @Override
                   public void onUpdateNickNameSuccess(String msg) {
                       showToast(msg);
                       UserInfoEntity entity=DataApplication.getInstance().getUserInfoEntity();
                       entity.setNickName(nick);
                       ISpfUtil.setValue(Constant.USERINFO_KEY,new Gson().toJson(entity));
                       finish();
                   }

                   @Override
                   public void onUpdateNickNameFaiulure(String error) {
                       showToast(error);
                   }
               }).updateNickName();
            }else {
                showToast("昵称不能为空");
            }
        } else if (fromValue.equals(ModiflyPasswordFragment.MODIFLY_PASSWORD_FLAG)) {//密码修改返回
            String newPwd = ModiflyPasswordFragment.newInstance().newPasswordText();
            if (!TextUtils.isEmpty(newPwd)) {
//                showToast(newPwd);
                updatePwdPresenter.updatePassword();
            }
        }
    }

    @Override
    public void onUpdatePasswordFialure(String error) {
        showToast(error);
    }

    @Override
    public void onShowDialog() {
        loadDialog= DialogFactory.createLoadingDialog(this,"密码修改...");
    }

    @Override
    public void onDissmisDialog() {
        DialogFactory.dimissDialog(loadDialog);
    }

    @Override
    public String getUserNo() {
        return DataApplication.getInstance().getUserInfoEntity().getUserNo();
    }

    @Override
    public String getOldPwd() {
        return ISpfUtil.getValue(Constant.PSSWORD_KEY,"").toString();
    }

    @Override
    public String getNewPwd() {
        return ModiflyPasswordFragment.newInstance().newPasswordText();
    }

    @Override
    public void onUpdatePasswordSuccess(String msg) {
        showToast(msg);
        ISpfUtil.setValue(Constant.PSSWORD_KEY,ModiflyPasswordFragment.newInstance().newPasswordText());
        finish();
    }
}
