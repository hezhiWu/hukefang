package com.yunwei.easydear.function.mainFuncations.locationFunction;

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
public class LocationRemoteRepo implements LocationDataSource {

    private static LocationRemoteRepo instance;

    public static LocationRemoteRepo getInstance() {
        if (instance == null) {
            instance = new LocationRemoteRepo();
        }
        return instance;
    }

    @Override
    public void reqCurCityDistrict(final LocationCallBack callBack) {
        Call<ResponseModel<List<LocationEntity>>> call = RetrofitManager.getInstance().getService().reqDistrict(callBack.getCityCode());
        call.enqueue(new Callback<ResponseModel<List<LocationEntity>>>() {
            @Override
            public void onResponse(Call<ResponseModel<List<LocationEntity>>> call, Response<ResponseModel<List<LocationEntity>>> response) {
                if (response.isSuccessful() && response.body().getCode() == Constant.HTTP_SUCESS_CODE) {
                    callBack.onGetDistrictSuccess(response.body().getData());
                } else {
                    callBack.onGetDistrictFailure(response.message());
                }

            }

            @Override
            public void onFailure(Call<ResponseModel<List<LocationEntity>>> call, Throwable t) {
                callBack.onGetDistrictFailure("获取当前城市区域列表失败");
            }
        });

    }

    @Override
    public void reqAllCity(final LocationCallBack callBack) {
        Call<ResponseModel<List<LocationEntity>>> call = RetrofitManager.getInstance().getService().reqCity();
        call.enqueue(new Callback<ResponseModel<List<LocationEntity>>>() {
            @Override
            public void onResponse(Call<ResponseModel<List<LocationEntity>>> call, Response<ResponseModel<List<LocationEntity>>> response) {
                if (response.isSuccessful() && response.body().getCode() == Constant.HTTP_SUCESS_CODE) {
                    callBack.onGetAllCitySuccess(response.body().getData());
                } else {
                    callBack.onGetAllCityFailure(response.message());
                }

            }

            @Override
            public void onFailure(Call<ResponseModel<List<LocationEntity>>> call, Throwable t) {
                callBack.onGetAllCityFailure("获取当前城市区域列表失败");
            }
        });
    }
}
