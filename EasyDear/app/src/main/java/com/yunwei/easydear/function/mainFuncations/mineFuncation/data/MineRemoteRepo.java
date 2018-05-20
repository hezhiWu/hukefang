package com.yunwei.easydear.function.mainFuncations.mineFuncation.data;

import com.yunwei.easydear.common.Constant;
import com.yunwei.easydear.common.retrofit.RetrofitManager;
import com.yunwei.easydear.entity.ResponseModel;
import com.yunwei.easydear.function.mainFuncations.membershipFuncation.data.CardEntity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by LJH on 2017/1/8.
 */

public class MineRemoteRepo implements MineDataSource {

    private static MineRemoteRepo instance;

    public static MineRemoteRepo getInstance() {
        if (instance == null) {
            instance = new MineRemoteRepo();
        }
        return instance;
    }

    @Override
    public void reqMyCardAmount(final MineCallBack callBack) {

        Call<ResponseModel<CardEntity>> call = RetrofitManager.getInstance().getService().reqCardCount(callBack.getUserNo());
        call.enqueue(new Callback<ResponseModel<CardEntity>>() {
            @Override
            public void onResponse(Call<ResponseModel<CardEntity>> call, Response<ResponseModel<CardEntity>> response) {
                if (response.isSuccessful() && response.body().getCode() == Constant.HTTP_SUCESS_CODE) {
                    callBack.getCardCountSuccess(response.body().getData());
                } else {
                    callBack.getCardCountFailure("获取卡券数量失败");
                }
            }

            @Override
            public void onFailure(Call<ResponseModel<CardEntity>> call, Throwable t) {
                callBack.getCardCountFailure("获取卡券数量失败");
            }
        });
    }

    @Override
    public void reqMyBusinessAmount(final MineCallBack callBack) {

        Call<ResponseModel<com.yunwei.easydear.function.mainFuncations.membershipFuncation.data.BusinessEntity>> call = RetrofitManager.getInstance().getService().reqBusinessCount(callBack.getUserNo());
        call.enqueue(new Callback<ResponseModel<com.yunwei.easydear.function.mainFuncations.membershipFuncation.data.BusinessEntity>>() {
            @Override
            public void onResponse(Call<ResponseModel<com.yunwei.easydear.function.mainFuncations.membershipFuncation.data.BusinessEntity>> call,
                                   Response<ResponseModel<com.yunwei.easydear.function.mainFuncations.membershipFuncation.data.BusinessEntity>> response) {
                if (response.isSuccessful() && response.body().getCode() == Constant.HTTP_SUCESS_CODE) {
                    callBack.getBusinessCountSuccess(response.body().getData());
                } else {
                    callBack.getBusinessCountFailure("获取商家数量失败");
                }
            }

            @Override
            public void onFailure(Call<ResponseModel<com.yunwei.easydear.function.mainFuncations.membershipFuncation.data.BusinessEntity>> call, Throwable t) {
                callBack.getCardCountFailure("获取商家数量失败");
            }
        });
    }
}
