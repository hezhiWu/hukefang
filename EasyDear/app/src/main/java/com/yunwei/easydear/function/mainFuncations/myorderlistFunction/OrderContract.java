package com.yunwei.easydear.function.mainFuncations.myorderlistFunction;

import com.yunwei.easydear.function.mainFuncations.myorderlistFunction.data.OrderEntity;

import java.util.List;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.easyDear.function.mainFuncations.myorderlistFunction
 * @Description:
 * @date 2017/1/21 10:54
 */

public interface OrderContract {

    interface OrderView {
        void onStartOrder();

        void onEndOrder();

        void onOrderSuccess(List<OrderEntity> list);

        void onOrderFailure(String error);

        String getUserNo();

        int getPageSize();

        int getPageCount();
    }

    interface Presenter {
        void reqOrderList();
    }
}
