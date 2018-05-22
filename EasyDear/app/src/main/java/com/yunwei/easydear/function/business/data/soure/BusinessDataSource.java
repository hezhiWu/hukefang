package com.yunwei.easydear.function.business.data.soure;

import com.yunwei.easydear.function.business.data.BusinessDetailEntity;

/**
 * Created by Administrator on 2018/5/21.
 */

public interface BusinessDataSource {

    interface QueryBusinessDetailCallBack{
        void queryBusinessDetailStart();

        void queryBusinessDetailSuccess(BusinessDetailEntity entity);

        void queryBusinessDetailFailure(int code,String msg);

        void queryBusinessDetailEnd();
    }

    void queryBusinessDetail(String businessNo,QueryBusinessDetailCallBack callBack);
}
