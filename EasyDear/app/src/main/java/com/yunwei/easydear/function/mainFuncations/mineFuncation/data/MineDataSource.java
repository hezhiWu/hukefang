package com.yunwei.easydear.function.mainFuncations.mineFuncation.data;

import com.yunwei.easydear.function.mainFuncations.membershipFuncation.data.CardEntity;

/**
 * Created by LJH on 2017/1/8.
 */

public interface MineDataSource {


    void reqMyCardAmount(MineCallBack callBack);

    void reqMyBusinessAmount(MineCallBack callBack);

    interface MineCallBack {
        void getCardCountSuccess(CardEntity data);

        void getCardCountFailure(String message);

        void getBusinessCountSuccess(com.yunwei.easydear.function.mainFuncations.membershipFuncation.data.BusinessEntity data);

        void getBusinessCountFailure(String message);

        String getUserNo();

        int getPageSize();

        int getPageCount();
    }

}
