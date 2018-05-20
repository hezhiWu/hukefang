package com.yunwei.easydear.function.mainFuncations.messageFunction.data.source;

import com.yunwei.easydear.common.Constant;
import com.yunwei.easydear.common.retrofit.RetrofitManager;
import com.yunwei.easydear.entity.ResponseModel;
import com.yunwei.easydear.function.mainFuncations.messageFunction.data.MessageItemEntity;
import com.yunwei.easydear.function.mainFuncations.messageFunction.data.MessageDetailEntity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by LJH on 2017/1/22.
 */

public class MessageRemoteRepo implements MessageDataSource {

    private final String TAG = getClass().getSimpleName();

    private static MessageRemoteRepo instance;

    private Call<ResponseModel<List<MessageDetailEntity>>> msgDetailCall;

    public static MessageRemoteRepo getInstance() {
        if (instance == null) {
            instance = new MessageRemoteRepo();
        }
        return instance;
    }

    @Override
    public void requestTuiMessages(String useNo, final TuiMsgCallBack callBack) {

        Call<ResponseModel<ArrayList<MessageItemEntity>>> call = RetrofitManager.getInstance().getService().requestTuiMessages(useNo, 1, 1);
        call.enqueue(new Callback<ResponseModel<ArrayList<MessageItemEntity>>>() {
            @Override
            public void onResponse(Call<ResponseModel<ArrayList<MessageItemEntity>>> call, Response<ResponseModel<ArrayList<MessageItemEntity>>> response) {

                if (response.isSuccessful() && response.body().getCode() == Constant.HTTP_SUCESS_CODE) {
                    callBack.onReqTuiMessagesSuccess(response.body().getData());
                } else {
                    callBack.onReqTuiMessagesFailure("获取失败");
                }
            }

            @Override
            public void onFailure(Call<ResponseModel<ArrayList<MessageItemEntity>>> call, Throwable t) {

            }
        });

    }

    @Override
    public void requestBusMessages(String useNo, final BusMsgCallBack callBack) {

        Call<ResponseModel<ArrayList<MessageItemEntity>>> call = RetrofitManager.getInstance().getService().requestBusMessages(useNo, 1, 5);
        call.enqueue(new Callback<ResponseModel<ArrayList<MessageItemEntity>>>() {
            @Override
            public void onResponse(Call<ResponseModel<ArrayList<MessageItemEntity>>> call, Response<ResponseModel<ArrayList<MessageItemEntity>>> response) {

                if (response.isSuccessful() && response.body().getCode() == Constant.HTTP_SUCESS_CODE) {
                    callBack.onReqBusMessagesSuccess(response.body().getData());
                } else {
                    callBack.onReqBusMessagesFailure("获取失败");
                }
            }

            @Override
            public void onFailure(Call<ResponseModel<ArrayList<MessageItemEntity>>> call, Throwable t) {

            }
        });

    }

    @Override
    public void reqMsgDetail(final MsgDetailCallBack callBack) {
        if (callBack.getBusinessNo() == null) {
            msgDetailCall = RetrofitManager.getInstance().getService().reqTuiMessageDetail(callBack.getUserNo(), callBack.getPageSize(), callBack.getPageCount());
        } else {
            msgDetailCall = RetrofitManager.getInstance().getService().reqMessageDetail(callBack.getUserNo(), callBack.getBusinessNo(), callBack.getPageSize(), callBack.getPageCount());
        }
        msgDetailCall.enqueue(new Callback<ResponseModel<List<MessageDetailEntity>>>() {
            @Override
            public void onResponse(Call<ResponseModel<List<MessageDetailEntity>>> call, Response<ResponseModel<List<MessageDetailEntity>>> response) {
                if (response.isSuccessful() && response.body().getCode() == Constant.HTTP_SUCESS_CODE) {
                    callBack.getMsgSuccess(response.body().getData());
                } else {
                    callBack.getMsgFailure(response.body().getCode(), "获取失败");
                }
            }

            @Override
            public void onFailure(Call<ResponseModel<List<MessageDetailEntity>>> call, Throwable t) {
                callBack.getMsgFailure(201, "数据加载失败");
            }
        });
    }
}
