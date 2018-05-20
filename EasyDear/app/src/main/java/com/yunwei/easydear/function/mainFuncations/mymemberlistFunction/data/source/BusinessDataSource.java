package com.yunwei.easydear.function.mainFuncations.mymemberlistFunction.data.source;

import com.yunwei.easydear.function.mainFuncations.mymemberlistFunction.data.BusinessEntity;

import java.util.List;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.easyDear.function.mainFuncations.mymemberlistFunction.data.source
 * @Description:
 * @date 2017/1/21 14:47
 */

public interface BusinessDataSource {

    interface BusinessCallBack {
        void getBusinessSuccess(List<BusinessEntity> list);

        void getBusinessFailure(String error);

        String getUserNo();

        int getPageSize();

        int getPageCount();
    }

    void reqBusinessList(BusinessCallBack callBack);
}
