package com.yunwei.easydear.function.business.data.soure;

import com.yunwei.easydear.common.retrofit.RetrofitManager;
import com.yunwei.easydear.entity.ResponseModel;
import com.yunwei.easydear.function.business.data.BusinessDetailEntity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2018/5/21.
 */

public class BusinessRemoreRepo implements BusinessDataSource {

    private static BusinessRemoreRepo remoreRepo;

    public static BusinessRemoreRepo getRemoreRepo(){
        if (remoreRepo==null)
            remoreRepo=new BusinessRemoreRepo();

        return remoreRepo;
    }
    @Override
    public void queryBusinessDetail(String businessNo, final QueryBusinessDetailCallBack callBack) {
        callBack.queryBusinessDetailStart();

        Call<ResponseModel<BusinessDetailEntity>> call= RetrofitManager.getInstance().getService().queryBuniessDetail(businessNo);
        call.enqueue(new Callback<ResponseModel<BusinessDetailEntity>>() {
            @Override
            public void onResponse(Call<ResponseModel<BusinessDetailEntity>> call, Response<ResponseModel<BusinessDetailEntity>> response) {
                if (response.isSuccessful()){
                    if (response.body().getCode()==200){
                        callBack.queryBusinessDetailSuccess(response.body().getData());
                    }else {
                        callBack.queryBusinessDetailFailure(response.body().getCode(),response.body().getMessage());
                    }
                }else {
                    callBack.queryBusinessDetailFailure(response.code(),response.message());
                }

                callBack.queryBusinessDetailEnd();
            }

            @Override
            public void onFailure(Call<ResponseModel<BusinessDetailEntity>> call, Throwable t) {
                callBack.queryBusinessDetailFailure(9999,"查询失败");

                callBack.queryBusinessDetailEnd();
            }
        });
    }
}
