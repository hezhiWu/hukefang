package com.yunwei.easydear.function.cards.data.source;

import com.yunwei.easydear.common.retrofit.RetrofitManager;
import com.yunwei.easydear.entity.ResponseModel;
import com.yunwei.easydear.function.cards.data.CardEntity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2018/5/22.
 */

public class CardsRemoteRepo implements CardsDataSource {

    private static CardsRemoteRepo remoteRepo;

    public static CardsRemoteRepo getRemoteRepo(){
        if (remoteRepo==null)
            remoteRepo=new CardsRemoteRepo();

        return remoteRepo;
    }
    @Override
    public void queryCardList(int pageSize, int pageCount, String keywords, final QueryCardListCallBack callBack) {

        Call<ResponseModel<List<CardEntity>>> call= RetrofitManager.getInstance().getService().queryCards(pageSize,pageCount,keywords);
        call.enqueue(new Callback<ResponseModel<List<CardEntity>>>() {
            @Override
            public void onResponse(Call<ResponseModel<List<CardEntity>>> call, Response<ResponseModel<List<CardEntity>>> response) {
                if (response.isSuccessful()){
                    if (response.body().getCode()==200){
                        callBack.queryCardListSuccess(response.body().getData());
                    }else {
                        callBack.queryCardListFailure(response.body().getCode(),response.body().getMessage());
                    }
                }else {
                    callBack.queryCardListFailure(response.code(),response.message());
                }
            }

            @Override
            public void onFailure(Call<ResponseModel<List<CardEntity>>> call, Throwable t) {
                callBack.queryCardListFailure(3000,"请求失败");
            }
        });
    }
}
