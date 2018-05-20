package com.yunwei.easydear.function.mainFuncations.messageFunction;

import com.yunwei.easydear.function.mainFuncations.messageFunction.data.MessageItemEntity;
import com.yunwei.easydear.function.mainFuncations.messageFunction.data.MessageDetailEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LJH on 2017/1/22.
 */

public interface MessageContact {

    interface MessageView {
        void setTuiMessages(ArrayList<MessageItemEntity> data);

        void setBusinessMessages(ArrayList<MessageItemEntity> data);
    }

    interface MessageDetailView {
        void onMsgStart();

        void onMsgEnd();

        void onMsgSuccess(List<MessageDetailEntity> list);

        void onMsgFailure(int code, String error);

        int getPageSize();

        int getPageCount();

        String getUserNo();

        String getBusinessNo();
    }

    interface Presenter {
        void requestBusMessages(String useNo);

        void requestTuiMessages(String useNo);

        void reqMsgDetail();
    }
}
