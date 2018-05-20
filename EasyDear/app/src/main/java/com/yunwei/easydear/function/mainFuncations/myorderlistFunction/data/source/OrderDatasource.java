package com.yunwei.easydear.function.mainFuncations.myorderlistFunction.data.source;

import com.yunwei.easydear.function.mainFuncations.myorderlistFunction.data.OrderEntity;

import java.util.List;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.easyDear.function.mainFuncations.myorderlistFunction.data
 * @Description:
 * @date 2017/1/21 10:53
 */

public interface OrderDatasource {

    interface OrderCallBack {
        void getOrderSuccess(List<OrderEntity> list);

        void getOrderFailure(String error);

        String getUserNo();

        int getPageSize();

        int getPageCount();
    }

    void reqOrderList(OrderCallBack callBack);
}
