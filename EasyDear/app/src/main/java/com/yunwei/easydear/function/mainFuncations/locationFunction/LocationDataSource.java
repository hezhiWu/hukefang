package com.yunwei.easydear.function.mainFuncations.locationFunction;

import java.util.List;

/**
 * Created by LJH on 2017/3/4.
 */
public interface LocationDataSource {

    void reqCurCityDistrict(LocationCallBack callBack);

    void reqAllCity(LocationCallBack callBack);

    interface LocationCallBack {
        String getCityCode();

        void onGetDistrictSuccess(List<LocationEntity> data);

        void onGetDistrictFailure(String message);

        void onGetAllCitySuccess(List<LocationEntity> data);

        void onGetAllCityFailure(String message);
    }
}
