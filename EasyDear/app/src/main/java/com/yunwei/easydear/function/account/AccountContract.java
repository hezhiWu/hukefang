package com.yunwei.easydear.function.account;

import com.yunwei.easydear.function.account.data.UserInfoEntity;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.frame.function.account
 * @Description:定义协议
 * @date 2016/11/29 14:54
 */

public interface AccountContract {
    /**
     * 登录
     */
    interface LoginView {
        void showDialog();

        void dismissDialog();

        void loginSuccess(UserInfoEntity entity);

        void loginFailure(String error);

        String getAccount();

        String getPassword();
    }

    interface RegistView {
        void showDialog();

        void dismissDialog();

        void registSuccess();

        void registFailure(String error);

        String getMobile();

        String getPassword();

        String getMobileKey();

        String getCode();
    }

    interface validateView {
        void onStartSendValidateCode();

        void onEndSendValidateCode();

        void getValidateCodeSuccess(String code);

        void getValidateCodeFailure(String error);

        String getMobile();
    }

    interface UpdatePasswordView {
        void onShowDialog();

        void onDissmisDialog();

        String getUserNo();

        String getOldPwd();

        String getNewPwd();

        void onUpdatePasswordSuccess(String msg);

        void onUpdatePasswordFialure(String error);
    }

    interface UpdateNickNameView {
        void onShowDialog();

        void onDissmisDialog();

        String getUserNo();

        String getNickName();

        void onUpdateNickNameSuccess(String msg);

        void onUpdateNickNameFaiulure(String error);
    }

    interface Presenter {
        void login();

        void regist();

        void sendValidateCode();

        void updatePassword();

        void updateNickName();
    }
}
