package com.yunwei.easydear.function.mainFuncations.businessFunction;

import com.yunwei.easydear.common.Constant;
import com.yunwei.easydear.common.retrofit.RetrofitManager;
import com.yunwei.easydear.entity.ResponseModel;
import com.yunwei.easydear.function.mainFuncations.articleFunction.ArticleItemEntity;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by LJH on 2017/2/11.
 */
public class BusinessRemoteRepo implements BusinessDataSource {

    private static BusinessRemoteRepo instance;

    public static BusinessRemoteRepo getInstance() {
        if (instance == null) {
            instance = new BusinessRemoteRepo();
        }
        return instance;
    }

    public void reqBusDetail(final BusDetailCallBack callBack) {
        Call<ResponseModel<BusinessDetailEntity>> call = RetrofitManager.getInstance().getService().requestBusinessDetail(callBack.getBusinessNo(), callBack.getUserNo());
        call.enqueue(new Callback<ResponseModel<BusinessDetailEntity>>() {
            @Override
            public void onResponse(Call<ResponseModel<BusinessDetailEntity>> call, Response<ResponseModel<BusinessDetailEntity>> response) {
                if (response.isSuccessful() && response.body().getCode() == Constant.HTTP_SUCESS_CODE) {
                    callBack.onReqBusDetailSuccess(response.body().getData());
                } else {
                    callBack.onReqBusDetailFailure(response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<ResponseModel<BusinessDetailEntity>> call, Throwable t) {

            }
        });
    }

    @Override
    public void reqBusinessCardList(final BusDetailCallBack callBack) {

        Call<ResponseModel<ArrayList<CardItemEntity>>> call = RetrofitManager.getInstance().getService().requestLatestCardInfo(callBack.getBusinessNo(), 1, 5);
        call.enqueue(new Callback<ResponseModel<ArrayList<CardItemEntity>>>() {
            @Override
            public void onResponse(Call<ResponseModel<ArrayList<CardItemEntity>>> call, Response<ResponseModel<ArrayList<CardItemEntity>>> response) {

                if (response.isSuccessful() && response.body().getCode() == Constant.HTTP_SUCESS_CODE) {
                    callBack.onReqCardListSuccess(response.body().getData());
                } else {
                    callBack.onReqCardListFailure(response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<ResponseModel<ArrayList<CardItemEntity>>> call, Throwable t) {
                callBack.onReqCardListFailure("获取卡券列表失败");
            }
        });
    }

    @Override
    public void reqBusArticles(final BusDetailCallBack callBack) {

        Call<ResponseModel<ArrayList<ArticleItemEntity>>> call = RetrofitManager.getInstance().getService().requestBusinessArticles(callBack.getBusinessNo(), 1, 5);
        call.enqueue(new Callback<ResponseModel<ArrayList<ArticleItemEntity>>>() {
            @Override
            public void onResponse(Call<ResponseModel<ArrayList<ArticleItemEntity>>> call, Response<ResponseModel<ArrayList<ArticleItemEntity>>> response) {

                if (response.isSuccessful() && response.body().getCode() == Constant.HTTP_SUCESS_CODE)
                    callBack.onBusinessArticlesSuccess(response.body().getData());
                else {
                    callBack.onReqBusinessArticlesFailure(response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<ResponseModel<ArrayList<ArticleItemEntity>>> call, Throwable t) {
                callBack.onReqBusinessArticlesFailure("获取商家文章列表失败");
            }
        });

    }

}
