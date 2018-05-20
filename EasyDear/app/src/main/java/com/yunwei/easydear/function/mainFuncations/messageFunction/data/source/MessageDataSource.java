package com.yunwei.easydear.function.mainFuncations.messageFunction.data.source;

import com.yunwei.easydear.function.mainFuncations.messageFunction.data.MessageItemEntity;
import com.yunwei.easydear.function.mainFuncations.messageFunction.data.MessageDetailEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LJH on 2017/1/22.
 */
public interface MessageDataSource {

    void requestTuiMessages(String useNo, TuiMsgCallBack callBack);

    void requestBusMessages(String useNo, BusMsgCallBack callBack);

    interface TuiMsgCallBack {
        void onReqTuiMessagesSuccess(ArrayList<MessageItemEntity> data);

        void onReqTuiMessagesFailure(String message);
    }

    interface BusMsgCallBack {
        void onReqBusMessagesSuccess(ArrayList<MessageItemEntity> data);

        void onReqBusMessagesFailure(String message);
    }

    interface MsgDetailCallBack {

        void getMsgSuccess(List<MessageDetailEntity> list);

        void getMsgFailure(int code,String error);

        int getPageSize();

        int getPageCount();

        String getUserNo();

        String getBusinessNo();
    }

    void reqMsgDetail(MsgDetailCallBack callBack);
}
