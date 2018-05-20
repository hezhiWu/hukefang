package com.yunwei.easydear.function.mainFuncations.searchFunction;


import com.yunwei.easydear.common.Constant;
import com.yunwei.easydear.common.retrofit.RetrofitManager;
import com.yunwei.easydear.entity.ResponseModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by LJH on 2017/3/4.
 */
public class SearchRemoteRepo implements SearchDataSource {

    private static SearchRemoteRepo instance;

    public static SearchRemoteRepo getInstance() {
        if (instance == null) {
            instance = new SearchRemoteRepo();
        }
        return instance;
    }

    @Override
    public void reqHotSearch(final SearchCallBack callBack) {
        Call<ResponseModel<List<SearchEntity>>> call = RetrofitManager.getInstance().getService().reqHotSearch();
        call.enqueue(new Callback<ResponseModel<List<SearchEntity>>>() {
            @Override
            public void onResponse(Call<ResponseModel<List<SearchEntity>>> call, Response<ResponseModel<List<SearchEntity>>> response) {
                if (response.isSuccessful() && response.body().getCode() == Constant.HTTP_SUCESS_CODE) {
                    callBack.getHotSearchSuccess(response.body().getData());
                } else {
                    callBack.getHotSearchFailure(response.message());
                }
            }

            @Override
            public void onFailure(Call<ResponseModel<List<SearchEntity>>> call, Throwable t) {
                callBack.getHotSearchFailure("获取热门搜索失败");
            }
        });

    }

    @Override
    public void reqKeyMatch(final SearchCallBack callBack) {
        Call<ResponseModel<List<SearchEntity>>> call = RetrofitManager.getInstance().getService().reqKeyMatch(callBack.getUserNo(), callBack.getKey());
        call.enqueue(new Callback<ResponseModel<List<SearchEntity>>>() {
            @Override
            public void onResponse(Call<ResponseModel<List<SearchEntity>>> call, Response<ResponseModel<List<SearchEntity>>> response) {
                if (response.isSuccessful() && response.body().getCode() == Constant.HTTP_SUCESS_CODE) {
                    callBack.getMatchedKeySuccess(response.body().getData());
                } else {
                    callBack.getMatchedKeyFailure(response.message());
                }
            }

            @Override
            public void onFailure(Call<ResponseModel<List<SearchEntity>>> call, Throwable t) {
                callBack.getMatchedKeyFailure("获取关键字匹配失败");
            }
        });
    }
}
