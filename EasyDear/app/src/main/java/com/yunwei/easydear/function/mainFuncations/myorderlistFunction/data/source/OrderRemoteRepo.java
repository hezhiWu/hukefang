package com.yunwei.easydear.function.mainFuncations.myorderlistFunction.data.source;

import com.yunwei.easydear.common.Constant;
import com.yunwei.easydear.common.retrofit.RetrofitManager;
import com.yunwei.easydear.entity.ResponseModel;
import com.yunwei.easydear.function.mainFuncations.myorderlistFunction.data.OrderEntity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.easyDear.function.mainFuncations.myorderlistFunction.data.source
 * @Description:
 * @date 2017/1/21 11:02
 */

public class OrderRemoteRepo implements OrderDatasource {

    Call<ResponseModel<List<OrderEntity>>> call;

    private static OrderRemoteRepo remoteRepo;
    public static OrderRemoteRepo newInstance(){
        if (remoteRepo==null){
            remoteRepo=new OrderRemoteRepo();
        }
        return remoteRepo;
    }

    @Override
    public void reqOrderList(final OrderCallBack callBack) {
        call = RetrofitManager.getInstance().getService().reqOrderList(callBack.getUserNo(), callBack.getPageSize(), callBack.getPageCount());
        call.enqueue(new Callback<ResponseModel<List<OrderEntity>>>() {
            @Override
            public void onResponse(Call<ResponseModel<List<OrderEntity>>> call, Response<ResponseModel<List<OrderEntity>>> response) {
                if (response.isSuccessful() && response.body().getCode() == Constant.HTTP_SUCESS_CODE) {
                    callBack.getOrderSuccess(response.body().getData());
                } else {
                    callBack.getOrderFailure("获取订单失败");
                }
            }

            @Override
            public void onFailure(Call<ResponseModel<List<OrderEntity>>> call, Throwable t) {
                callBack.getOrderFailure("获取订单失败!");
            }
        });
    }
}
