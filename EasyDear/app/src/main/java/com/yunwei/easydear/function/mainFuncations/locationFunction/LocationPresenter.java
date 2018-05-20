package com.yunwei.easydear.function.mainFuncations.locationFunction;

import java.util.List;

/**
 * Created by LJH on 2017/3/4.
 */
public class LocationPresenter implements LocationContact.Presenter, LocationDataSource.LocationCallBack {

    private LocationContact.LocationView mLocationView;
    private LocationRemoteRepo mRemoteRepo;

    public LocationPresenter(LocationRemoteRepo remoteRepo, LocationContact.LocationView locationView) {
        mRemoteRepo = remoteRepo;
        mLocationView = locationView;
    }

    @Override
    public void requestCurrentCityDistrict() {
        mRemoteRepo.reqCurCityDistrict(this);
    }

    @Override
    public void requestAllCity() {
        mRemoteRepo.reqAllCity(this);
    }

    @Override
    public String getCityCode() {
        return mLocationView.getCityCode();
    }

    @Override
    public void onGetDistrictSuccess(List<LocationEntity> entities) {
        mLocationView.onGetDistrictSuccess(entities);
    }

    @Override
    public void onGetDistrictFailure(String msg) {
        mLocationView.onGetDistrictFailure(msg);
    }

    @Override
    public void onGetAllCitySuccess(List<LocationEntity> entities) {
        mLocationView.onGetAllCitySuccess(entities);
    }

    @Override
    public void onGetAllCityFailure(String msg) {
        mLocationView.onGetAllCityFailure(msg);
    }
}
