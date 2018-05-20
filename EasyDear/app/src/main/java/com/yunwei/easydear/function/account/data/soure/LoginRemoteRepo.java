package com.yunwei.easydear.function.account.data.soure;

import com.yunwei.easydear.R;
import com.yunwei.easydear.common.Constant;
import com.yunwei.easydear.common.retrofit.RetrofitManager;
import com.yunwei.easydear.entity.ResponseModel;
import com.yunwei.easydear.function.account.data.UserInfoEntity;
import com.yunwei.easydear.base.DataApplication;
import com.yunwei.easydear.utils.INetWorkUtil;
import com.yunwei.easydear.utils.ISpfUtil;
import com.yunwei.easydear.utils.IUtil;

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
    private Call<ResponseModel<UserInfoEntity>> call;
    private Call<ResponseModel<UserInfoEntity>> rigestCall;
    private Call<ResponseModel<Void>> validateCall;
    private Call<ResponseModel> updatePwdCall;
    private Call<ResponseModel> updateNickNameCall;

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
        call = RetrofitManager.getInstance().getService().loginRepo(account, password);
        call.enqueue(new Callback<ResponseModel<UserInfoEntity>>() {
            @Override
            public void onResponse(Call<ResponseModel<UserInfoEntity>> call, Response<ResponseModel<UserInfoEntity>> response) {
                if (response.isSuccessful() && response.body().getCode() == Constant.HTTP_SUCESS_CODE) {
                    //token 缓存
                    ISpfUtil.setValue(Constant.ACCESS_TOKEN_KEY,response.body().getToken());

                    callBack.onLoginSuccess(response.body().getData());
                } else {
                    callBack.onLoginFailure(response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<ResponseModel<UserInfoEntity>> call, Throwable t) {
                callBack.onLoginFailure(IUtil.getStrToRes(R.string.login_failure));
            }
        });
    }

    @Override
    public void rigest(final RigestCallBack callBack) {
        if (!INetWorkUtil.isNetworkAvailable(DataApplication.getInstance())) {
            callBack.onRigestFailure(IUtil.getStrToRes(R.string.invalid_network));
            return;
        }
        rigestCall = RetrofitManager.getInstance().getService().registRepo(callBack.getMobile(), callBack.getPassword(), callBack.getCode(), callBack.getMobileKey());
        rigestCall.enqueue(new Callback<ResponseModel<UserInfoEntity>>() {
            @Override
            public void onResponse(Call<ResponseModel<UserInfoEntity>> call, Response<ResponseModel<UserInfoEntity>> response) {
                if (response.isSuccessful() && response.body().getCode() == Constant.HTTP_SUCESS_CODE) {
                    callBack.onRigestSuccess(response.body().getData());
                } else {
                    callBack.onRigestFailure(response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<ResponseModel<UserInfoEntity>> call, Throwable t) {
                callBack.onRigestFailure("注册失败");
            }
        });
    }

    @Override
    public void sendValidateCode(final ValidateCallBack callBack) {
        validateCall = RetrofitManager.getInstance().getService().sendValidateCode(callBack.getSendMobile());
        validateCall.enqueue(new Callback<ResponseModel<Void>>() {
            @Override
            public void onResponse(Call<ResponseModel<Void>> call, Response<ResponseModel<Void>> response) {
                if (response.isSuccessful() && response.body().getCode() == Constant.HTTP_SUCESS_CODE) {
                    callBack.onValidateSuccess(response.body().getCode()+"");
                } else {
                    callBack.onValidateFailure(response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<ResponseModel<Void>> call, Throwable t) {
                callBack.onValidateFailure("获取失败");
            }
        });
    }

    @Override
    public void updatePassword(final UpdatePasswordCallBack callBack) {
        updatePwdCall = RetrofitManager.getInstance().getService().updatePassword(callBack.getUserNo(), callBack.getOldPwd(), callBack.getNewPwd());
        updatePwdCall.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                if (response.isSuccessful() && response.body().getCode() == Constant.HTTP_SUCESS_CODE) {
                    callBack.onUpdatePwdSuccess(response.body().getMessage());
                } else {
                    callBack.onUpdateFailure(response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                callBack.onUpdateFailure("修改失败");
            }
        });
    }

    @Override
    public void updateNickName(final UpdateNickNameCallBack callBack) {
        updateNickNameCall=RetrofitManager.getInstance().getService().updateNickName(callBack.getNickName(),callBack.getUserNoToNickName());
        updateNickNameCall.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                if (response.isSuccessful() && response.body().getCode() == Constant.HTTP_SUCESS_CODE) {
                    callBack.onUpdateNickNameSuccess(response.body().getMessage());
                } else {
                    callBack.onUpdateNickNameFailure(response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                callBack.onUpdateNickNameFailure("修改失败");
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
        }
        if (rigestCall != null && !rigestCall.isCanceled()) {
            rigestCall.cancel();
        }

        if (validateCall != null && !validateCall.isCanceled()) {
            validateCall.cancel();
        }
    }
}
