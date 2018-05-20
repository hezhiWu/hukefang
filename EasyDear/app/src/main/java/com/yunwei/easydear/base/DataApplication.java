package com.yunwei.easydear.base;

import android.app.Application;

import com.amap.api.location.AMapLocation;
import com.google.gson.Gson;
import com.yunwei.easydear.common.Constant;
import com.yunwei.easydear.function.account.data.UserInfoEntity;
import com.yunwei.easydear.function.mainFuncations.MainContract;
import com.yunwei.easydear.function.mainFuncations.MainPresenter;
import com.yunwei.easydear.function.mainFuncations.data.soure.MainRemoteRepo;
import com.yunwei.easydear.utils.ISpfUtil;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.frame.function.base
 * @Description:Application类
 * @date 2016/11/22 14:59
 */

public class DataApplication extends Application implements MainContract.MainView{

    private static DataApplication instance;

    private AMapLocation location;

    private UserInfoEntity userInfoEntity;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        //启动获取当前位置信息
        new MainPresenter(MainRemoteRepo.newInstance(), this).startLocation();
    }

    public static DataApplication getInstance() {
        return instance;
    }

    public AMapLocation getLocation() {
        return location;
    }

    public void setLocation(AMapLocation location) {
        this.location = location;
    }

    /**
     * 获取用户信息
     *
     * @return
     */
    public UserInfoEntity getUserInfoEntity() {
//        if (userInfoEntity == null) {
            userInfoEntity = new Gson().fromJson(ISpfUtil.getValue(Constant.USERINFO_KEY, "").toString(), UserInfoEntity.class);
//        }
        return userInfoEntity;
    }

    @Override
    public void locationSuccess(AMapLocation location) {
        if ("".equals(ISpfUtil.getValue(Constant.AMAP_LOCATION_CITY,"").toString())){
            ISpfUtil.setValue(Constant.AMAP_LOCATION_CITY, location.getCity());
            ISpfUtil.setValue(Constant.AMAP_LOCATION_ADCODE, location.getCityCode());
        }
        setLocation(location);
    }
}
