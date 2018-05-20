package com.yunwei.easydear.function.dynamic.data.soure;

import com.yunwei.easydear.common.retrofit.RetrofitManager;
import com.yunwei.easydear.entity.ResponseModel;
import com.yunwei.easydear.function.dynamic.data.DynamicDetailEntity;
import com.yunwei.easydear.function.dynamic.data.DynamicEntity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2018/5/20.
 */

public class DynamicRemoteRepo implements DynamicDataSource {

    private static DynamicRemoteRepo remoteRepo;

    public static DynamicRemoteRepo getRemoteRepo() {
        if (remoteRepo == null)
            remoteRepo= new DynamicRemoteRepo();

        return remoteRepo;
    }

    @Override
    public void queryDynamicList(int pageSize, int pageCount, String keywords, String type, String province, String city, String area, final QueryDynamicListCallBack callBack) {
        Call<ResponseModel<List<DynamicEntity>>> call = RetrofitManager.getInstance().getService().queryDynamicList(pageSize
                , pageCount
                , keywords
                , type
                , province
                , city
                , area);

        call.enqueue(new Callback<ResponseModel<List<DynamicEntity>>>() {
            @Override
            public void onResponse(Call<ResponseModel<List<DynamicEntity>>> call, Response<ResponseModel<List<DynamicEntity>>> response) {
                if (response.isSuccessful()){
                    if (response.body().getCode()==200){
                        callBack.queryDynamicListSuccess(response.body().getData());
                    }else {
                        callBack.queryDynamicListFailure(response.body().getCode(),response.body().getMessage());
                    }
                }else {
                    callBack.queryDynamicListFailure(response.code(),response.message());
                }

                callBack.queryDynamicListEnd();
            }

            @Override
            public void onFailure(Call<ResponseModel<List<DynamicEntity>>> call, Throwable t) {
                callBack.queryDynamicListFailure(9000,"请求失败");
            }
        });
    }

    @Override
    public void queryDynamicDetails(int articleId, final QueryDynamicDetailsCallBack callBack) {
        callBack.queryDynamicDetailsStart();

        Call<ResponseModel<DynamicDetailEntity>> call=RetrofitManager.getInstance().getService().queryDynamicDetails(articleId);
        call.enqueue(new Callback<ResponseModel<DynamicDetailEntity>>() {
            @Override
            public void onResponse(Call<ResponseModel<DynamicDetailEntity>> call, Response<ResponseModel<DynamicDetailEntity>> response) {
                if (response.isSuccessful()){
                    if (response.body().getCode()==200){
                        callBack.queryDynamicDetailsSuccess(response.body().getData());
                    }else {
                        callBack.queryDynamicDetailsFailure(response.body().getCode(),response.body().getMessage());
                    }
                }else {
                    callBack.queryDynamicDetailsFailure(response.code(),response.message());
                }

                callBack.queryDynamicDetailsEnd();
            }

            @Override
            public void onFailure(Call<ResponseModel<DynamicDetailEntity>> call, Throwable t) {
                callBack.queryDynamicDetailsFailure(9000,"请求失败");

                callBack.queryDynamicDetailsEnd();
            }
        });
    }
}
