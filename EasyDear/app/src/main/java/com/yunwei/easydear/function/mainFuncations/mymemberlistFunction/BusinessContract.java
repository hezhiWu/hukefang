package com.yunwei.easydear.function.mainFuncations.mymemberlistFunction;

import com.yunwei.easydear.function.mainFuncations.mymemberlistFunction.data.BusinessEntity;

import java.util.List;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.easyDear.function.mainFuncations.mymemberlistFunction
 * @Description:
 * @date 2017/1/21 14:49
 */

public interface BusinessContract {

    interface BusinessView {
        void onBusinessStart();

        void onBusinessEnd();

        void onBusinessSuccess(List<BusinessEntity> list);

        void onBusinessFaliure(String error);

        String getUserNo();

        int getPageSize();

        int getPageCount();
    }

    interface Present{
        void reqBusinessListAction();
    }
}
