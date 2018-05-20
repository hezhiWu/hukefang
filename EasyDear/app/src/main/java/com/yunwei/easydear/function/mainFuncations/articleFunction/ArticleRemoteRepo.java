package com.yunwei.easydear.function.mainFuncations.articleFunction;

import com.yunwei.easydear.common.Constant;
import com.yunwei.easydear.common.retrofit.RetrofitManager;
import com.yunwei.easydear.entity.ResponseModel;
import com.yunwei.easydear.function.mainFuncations.businessFunction.CardItemEntity;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by LJH on 2017/1/21.
 */

public class ArticleRemoteRepo implements ArticleDataSource {

    private final String TAG = getClass().getSimpleName();

    private static ArticleRemoteRepo instance;

    public static ArticleRemoteRepo getInstance() {
        if (instance == null) {
            instance = new ArticleRemoteRepo();
        }
        return instance;
    }

    @Override
    public void requestArticleDetail(String articleId, final ArticleDetailCallBack callBack) {

        Call<ResponseModel<ArticleItemEntity>> call = RetrofitManager.getInstance().getService().requestArticleDetail(articleId);
        call.enqueue(new Callback<ResponseModel<ArticleItemEntity>>() {
            @Override
            public void onResponse(Call<ResponseModel<ArticleItemEntity>> call, Response<ResponseModel<ArticleItemEntity>> response) {

                if (response.isSuccessful() && response.body().getCode() == Constant.HTTP_SUCESS_CODE) {
                    callBack.onReqArticleSuccess(response.body().getData());
                } else {
                    callBack.onReqArticleFailure(response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<ResponseModel<ArticleItemEntity>> call, Throwable t) {

            }
        });
    }

    @Override
    public void requestLatestCardInfo(String businessNo, final LatestCardCallBack callBack) {

        Call<ResponseModel<ArrayList<CardItemEntity>>> call = RetrofitManager.getInstance().getService().requestLatestCardInfo(businessNo, 1, 5);
        call.enqueue(new Callback<ResponseModel<ArrayList<CardItemEntity>>>() {
            @Override
            public void onResponse(Call<ResponseModel<ArrayList<CardItemEntity>>> call, Response<ResponseModel<ArrayList<CardItemEntity>>> response) {

                if (response.isSuccessful() && response.body().getCode() == Constant.HTTP_SUCESS_CODE) {
                    callBack.onReqLatestCardSuccess(response.body().getData());
                } else {
                    callBack.onReqLatestCardFailure(response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<ResponseModel<ArrayList<CardItemEntity>>> call, Throwable t) {
                callBack.onReqLatestCardFailure("获取最新卡券信息失败");
            }
        });
    }

    @Override
    public void requestBusinessArticles(String businessNo, final BusinessArticleListCallBack callBack) {

        Call<ResponseModel<ArrayList<ArticleItemEntity>>> call = RetrofitManager.getInstance().getService().requestBusinessArticles(businessNo, 1, 5);
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
                callBack.onReqBusinessArticlesFailure("获取商家软文列表失败");
            }
        });
    }
}
