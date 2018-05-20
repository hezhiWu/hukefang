package com.yunwei.easydear.function.mainFuncations.data.soure;

import com.amap.api.location.AMapLocation;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.cmcc.ui.mainFunctions.data.source
 * @Description:
 * @date 2016/11/16 17:09
 */

public interface MainDataSource {
    /**
     * 七牛Token
     */
    interface RequestQiNiuTokenCallBack {
        void getQiNiuTokenSuccess(String token);
    }

    /**
     * 启动定位
     */
    interface StartLocationCallBack{
        void getLocation(AMapLocation location);
    }

    void reqQiNiuToken(RequestQiNiuTokenCallBack callBack);

    void startLocationAction(StartLocationCallBack callBack);
}
