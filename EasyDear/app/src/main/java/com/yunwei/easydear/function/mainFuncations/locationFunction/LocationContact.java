package com.yunwei.easydear.function.mainFuncations.locationFunction;

import java.util.List;

/**
 * Created by LJH on 2017/3/4.
 */

public interface LocationContact {

    interface LocationView {
        String getCityCode();

        void onGetDistrictSuccess(List<LocationEntity> entities);

        void onGetDistrictFailure(String msg);

        void onGetAllCitySuccess(List<LocationEntity> entities);

        void onGetAllCityFailure(String msg);
    }

    interface Presenter {
        void requestCurrentCityDistrict();

        void requestAllCity();
    }
}
