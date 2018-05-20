package com.yunwei.easydear.function.mainFuncations;

import com.amap.api.location.AMapLocation;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.cmcc.ui.mainFunctions
 * @Description:
 * @date 2016/11/16 16:58
 */

public interface MainContract {

    interface MainView {
        void locationSuccess(AMapLocation location);
    }

    interface Presenter {
        /*获取七牛Token*/
        void reqQiNiuToken();
        /*启动定位*/
        void startLocation();
    }

}
