package com.yunwei.easydear.function.mainFuncations.mymemberlistFunction.data.source;

import com.yunwei.easydear.common.Constant;
import com.yunwei.easydear.common.retrofit.RetrofitManager;
import com.yunwei.easydear.entity.ResponseModel;
import com.yunwei.easydear.function.mainFuncations.mymemberlistFunction.data.BusinessEntity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.easyDear.function.mainFuncations.mymemberlistFunction.data.source
 * @Description:
 * @date 2017/1/21 14:49
 */

public class MemberBusinessRemoteRepo implements BusinessDataSource {

    private static MemberBusinessRemoteRepo remoteRepo;

    private Call<ResponseModel<List<BusinessEntity>>> call;

    public static MemberBusinessRemoteRepo newInstance() {
        if (remoteRepo == null) {
            remoteRepo = new MemberBusinessRemoteRepo();
        }
        return remoteRepo;
    }

    @Override
    public void reqBusinessList(final BusinessCallBack callBack) {
        call = RetrofitManager.getInstance().getService().reqBusinessList(callBack.getUserNo(), callBack.getPageSize(), callBack.getPageCount());
        call.enqueue(new Callback<ResponseModel<List<BusinessEntity>>>() {
            @Override
            public void onResponse(Call<ResponseModel<List<BusinessEntity>>> call, Response<ResponseModel<List<BusinessEntity>>> response) {
                if (response.isSuccessful() && response.body().getCode() == Constant.HTTP_SUCESS_CODE) {
                    callBack.getBusinessSuccess(response.body().getData());
                } else {
                    callBack.getBusinessFailure("获取失败");
                }
            }

            @Override
            public void onFailure(Call<ResponseModel<List<BusinessEntity>>> call, Throwable t) {
                callBack.getBusinessFailure("获取失败");
            }
        });
    }
}
