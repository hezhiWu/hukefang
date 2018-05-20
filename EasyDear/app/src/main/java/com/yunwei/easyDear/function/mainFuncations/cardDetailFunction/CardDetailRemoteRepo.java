package com.yunwei.easydear.function.mainFuncations.cardDetailFunction;

import com.yunwei.easydear.common.Constant;
import com.yunwei.easydear.common.retrofit.RetrofitManager;
import com.yunwei.easydear.entity.ResponseModel;
import com.yunwei.easydear.function.mainFuncations.businessFunction.CardItemEntity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by LJH on 2017/1/21.
 */

public class CardDetailRemoteRepo implements CardDetailDataSource {

    private static CardDetailRemoteRepo mInstance;

    public static CardDetailRemoteRepo getInstance() {
        if (mInstance == null) {
            mInstance = new CardDetailRemoteRepo();
        }
        return mInstance;
    }

    @Override
    public void requestCardDetail(String cardNo, final CardDetailCallBack callBack) {
        Call<ResponseModel<CardItemEntity>> call = RetrofitManager.getInstance().getService().requestCardDetail(cardNo);
        call.enqueue(new Callback<ResponseModel<CardItemEntity>>() {
            @Override
            public void onResponse(Call<ResponseModel<CardItemEntity>> call, Response<ResponseModel<CardItemEntity>> response) {

                if (response.isSuccessful() && response.body().getCode() == Constant.HTTP_SUCESS_CODE) {
                    callBack.onReqCardDetailSuccess(response.body().getData());
                } else {
                    callBack.onReqCardDetailFailure(response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<ResponseModel<CardItemEntity>> call, Throwable t) {
                callBack.onReqCardDetailFailure("获取券详情失败");
            }
        });

    }
}
