package com.yunwei.easydear.function.mainFuncations.data.soure;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.yunwei.easydear.base.DataApplication;
import com.yunwei.easydear.common.Constant;
import com.yunwei.easydear.common.retrofit.RetrofitManager;
import com.yunwei.easydear.entity.ResponseModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.frame.function.mainFuncations.data.soure
 * @Description:
 * @date 2016/11/30 13:59
 */

public class MainRemoteRepo implements MainDataSource {
    private final String TAG = getClass().getSimpleName();

    private static MainRemoteRepo instance;

    public static MainRemoteRepo newInstance() {
        if (instance == null) {
            instance = new MainRemoteRepo();
        }
        return instance;
    }

    @Override
    public void reqQiNiuToken(final RequestQiNiuTokenCallBack callBack) {
        Call<ResponseModel<String>> call = RetrofitManager.getInstance().getService().reqQiniuToken();
        call.enqueue(new Callback<ResponseModel<String>>() {
            @Override
            public void onResponse(Call<ResponseModel<String>> call, Response<ResponseModel<String>> response) {
                if (response.code() == Constant.HTTP_SUCESS_CODE) {
                    if (response.isSuccessful()) {
                        callBack.getQiNiuTokenSuccess(call.toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseModel<String>> call, Throwable t) {

            }
        });
    }

    @Override
    public void startLocationAction(final StartLocationCallBack callBack) {
        final AMapLocationClient mLocationClient=new AMapLocationClient(DataApplication.getInstance().getApplicationContext());
        AMapLocationClientOption locationClientOption=new AMapLocationClientOption();
        locationClientOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);//高精度
        locationClientOption.setNeedAddress(true);
        locationClientOption.setInterval(4 * 1000);
        mLocationClient.setLocationOption(locationClientOption);
        mLocationClient.startLocation();
        mLocationClient.setLocationListener(new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation aMapLocation) {
                if (aMapLocation != null) {
                    if (aMapLocation.getErrorCode() == 0) {
                        callBack.getLocation(aMapLocation);
                        mLocationClient.stopLocation();
                    }
                }
            }
        });
    }
}
