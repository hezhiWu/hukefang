package com.yunwei.easydear.function.mainFuncations.mycardlistFunction.data.source;

import com.yunwei.easydear.common.Constant;
import com.yunwei.easydear.common.retrofit.RetrofitManager;
import com.yunwei.easydear.entity.ResponseModel;
import com.yunwei.easydear.function.mainFuncations.mycardlistFunction.data.CardEntity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.easyDear.function.mainFuncations.mycardlistFunction.data.source
 * @Description:
 * @date 2017/1/21 17:01
 */

public class CardRemoteRepo implements CardDataSource {

    private Call<ResponseModel<List<CardEntity>>> cardCall;

    private static CardRemoteRepo remoteRepo;

    public static CardRemoteRepo newInstance() {
        if (remoteRepo == null) {
            remoteRepo = new CardRemoteRepo();
        }
        return remoteRepo;
    }

    @Override
    public void reqCardList(final CardCallBack callBack) {
        cardCall = RetrofitManager.getInstance().getService().reqCardList(callBack.getUserNo());
        cardCall.enqueue(new Callback<ResponseModel<List<CardEntity>>>() {
            @Override
            public void onResponse(Call<ResponseModel<List<CardEntity>>> call, Response<ResponseModel<List<CardEntity>>> response) {
                if (response.isSuccessful() && response.body().getCode() == Constant.HTTP_SUCESS_CODE) {
                    callBack.getCardSuccess(response.body().getData());
                } else {
                    callBack.getCardFaiulre(response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<ResponseModel<List<CardEntity>>> call, Throwable t) {
                callBack.getCardFaiulre("获取失败");
            }
        });
    }
}
