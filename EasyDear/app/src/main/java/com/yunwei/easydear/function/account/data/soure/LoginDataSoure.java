package com.yunwei.easydear.function.account.data.soure;

import com.yunwei.easydear.function.account.data.UserInfoEntity;
import com.yunwei.easydear.base.BaseDataSourse;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.frame.function.account.data.soure
 * @Description:
 * @date 2016/11/29 15:02
 */

public interface LoginDataSoure extends BaseDataSourse {

    interface LoginCallBack {

        void onLoginSuccess(UserInfoEntity entity);

        void onLoginFailure(String error);

    }

    interface RigestCallBack {
        void onRigestSuccess(UserInfoEntity entity);

        void onRigestFailure(String error);

        String getMobile();

        String getPassword();

        String getMobileKey();

        String getCode();
    }

    interface ValidateCallBack {
        void onValidateSuccess(String code);

        void onValidateFailure(String error);

        String getSendMobile();
    }

    interface UpdatePasswordCallBack {
        void onUpdatePwdSuccess(String msg);

        void onUpdateFailure(String code);

        String getUserNo();

        String getOldPwd();

        String getNewPwd();
    }

    interface UpdateNickNameCallBack {
        String getUserNoToNickName();

        String getNickName();

        void onUpdateNickNameSuccess(String msg);

        void onUpdateNickNameFailure(String error);
    }

    void login(String account, String password, LoginCallBack callBack);

    void rigest(RigestCallBack callBack);

    void sendValidateCode(ValidateCallBack callBack);

    void updatePassword(UpdatePasswordCallBack callBack);

    void updateNickName(UpdateNickNameCallBack callBack);
}
