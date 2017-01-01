package com.jingan.easydearbusiness.function.accountFunction.data.source;


import com.jingan.easydearbusiness.R;
import com.jingan.easydearbusiness.base.DataApplication;
import com.jingan.easydearbusiness.common.Constant;
import com.jingan.easydearbusiness.function.accountFunction.data.UserInfoEntity;
import com.jingan.easydearbusiness.utils.ILog;
import com.jingan.easydearbusiness.utils.INetWorkUtil;
import com.jingan.easydearbusiness.utils.IUtil;
import com.jingan.easydearbusiness.vender.retrofit.RetrofitManager;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.frame.function.account.data.soure
 * @Description:登录业务请求
 * @date 2016/11/29 15:19
 */

public class LoginRemoteRepo implements LoginDataSoure {
    private final String TAG = getClass().getSimpleName();

    private static LoginRemoteRepo remoteRepo;
    private Call<UserInfoEntity> call;

    public static LoginRemoteRepo newInstance() {
        if (remoteRepo == null) {
            remoteRepo = new LoginRemoteRepo();
        }
        return remoteRepo;
    }

    @Override
    public void login(String account, String password, final LoginCallBack callBack) {
        if (!INetWorkUtil.isNetworkAvailable(DataApplication.getInstance())) {
            callBack.onLoginFailure(IUtil.getStrToRes(R.string.invalid_network));
            return;
        }
        String body = "grant_type=password&client_id=wt&client_secret=123456&username=" + account + "&password=" + password;
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), body);
        call = RetrofitManager.getInstance().getService().loginRepo(requestBody);
        call.enqueue(new Callback<UserInfoEntity>() {
            @Override
            public void onResponse(Call<UserInfoEntity> call, Response<UserInfoEntity> response) {
                if (response.code() == Constant.HTTP_SUCESS_CODE) {
                    callBack.onLoginSuccess(response.body());
                } else if (response.code() == Constant.HTTP_PASSWORD_ERROR_CODE) {
                    callBack.onLoginFailure(IUtil.getStrToRes(R.string.account_pwd_error));
                } else {
                    callBack.onLoginFailure(IUtil.getStrToRes(R.string.login_failure));
                }
            }

            @Override
            public void onFailure(Call<UserInfoEntity> call, Throwable t) {
                callBack.onLoginFailure(IUtil.getStrToRes(R.string.login_failure));
            }
        });
    }

    /**
     * 取消请求
     */
    @Override
    public void cancelRequest() {
        if (call != null && !call.isCanceled()) {
            call.cancel();
            ILog.d(TAG,"isCanceled=="+call.isCanceled());
        }
    }
}
