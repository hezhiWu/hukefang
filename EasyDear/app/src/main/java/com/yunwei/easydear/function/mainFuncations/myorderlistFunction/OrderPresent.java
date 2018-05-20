package com.yunwei.easydear.function.mainFuncations.myorderlistFunction;

import com.yunwei.easydear.function.mainFuncations.myorderlistFunction.data.OrderEntity;
import com.yunwei.easydear.function.mainFuncations.myorderlistFunction.data.source.OrderDatasource;
import com.yunwei.easydear.function.mainFuncations.myorderlistFunction.data.source.OrderRemoteRepo;

import java.util.List;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.easyDear.function.mainFuncations.myorderlistFunction
 * @Description:
 * @date 2017/1/21 11:09
 */

public class OrderPresent implements OrderDatasource.OrderCallBack, OrderContract.Presenter {

    private OrderRemoteRepo remoteRepo;
    private OrderContract.OrderView orderView;

    public OrderPresent(OrderContract.OrderView orderView) {
        this.remoteRepo = OrderRemoteRepo.newInstance();
        this.orderView = orderView;
    }

    @Override
    public void reqOrderList() {
        orderView.onStartOrder();
        remoteRepo.reqOrderList(this);
    }

    @Override
    public void getOrderSuccess(List<OrderEntity> list) {
        orderView.onOrderSuccess(list);
        orderView.onEndOrder();
    }

    @Override
    public void getOrderFailure(String error) {
        orderView.onOrderFailure(error);
        orderView.onEndOrder();
    }

    @Override
    public String getUserNo() {
        return orderView.getUserNo();
    }

    @Override
    public int getPageSize() {
        return orderView.getPageSize();
    }

    @Override
    public int getPageCount() {
        return orderView.getPageCount();
    }
}
