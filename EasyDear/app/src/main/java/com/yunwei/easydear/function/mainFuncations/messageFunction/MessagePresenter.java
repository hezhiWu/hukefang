package com.yunwei.easydear.function.mainFuncations.messageFunction;

import com.yunwei.easydear.function.mainFuncations.messageFunction.data.MessageItemEntity;
import com.yunwei.easydear.function.mainFuncations.messageFunction.data.MessageDetailEntity;
import com.yunwei.easydear.function.mainFuncations.messageFunction.data.source.MessageDataSource;
import com.yunwei.easydear.function.mainFuncations.messageFunction.data.source.MessageRemoteRepo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LJH on 2017/1/22.
 */

public class MessagePresenter implements MessageContact.Presenter, MessageDataSource.BusMsgCallBack, MessageDataSource.TuiMsgCallBack, MessageDataSource.MsgDetailCallBack {

    private MessageContact.MessageView mMessageView;
    private MessageContact.MessageDetailView messageDetailView;
    private MessageRemoteRepo mRemoteRepo;

    public MessagePresenter(MessageRemoteRepo remoteRepo, MessageFragment messageView) {
        mMessageView = messageView;
        mRemoteRepo = remoteRepo;
    }

    public MessagePresenter(MessageRemoteRepo remoteRepo, MessageContact.MessageDetailView messageDetailView) {
        this.messageDetailView = messageDetailView;
        this.mRemoteRepo = remoteRepo;
    }

    @Override
    public void requestTuiMessages(String useNo) {
        mRemoteRepo.requestTuiMessages(useNo, this);
    }

    @Override
    public void requestBusMessages(String useNo) {
        mRemoteRepo.requestBusMessages(useNo, this);
    }

    @Override
    public void reqMsgDetail() {
        messageDetailView.onMsgStart();
        mRemoteRepo.reqMsgDetail(this);
    }

    @Override
    public void onReqTuiMessagesSuccess(ArrayList<MessageItemEntity> data) {
        mMessageView.setTuiMessages(data);
    }

    @Override
    public void onReqTuiMessagesFailure(String message) {

    }

    @Override
    public void onReqBusMessagesSuccess(ArrayList<MessageItemEntity> data) {
        mMessageView.setBusinessMessages(data);
    }

    @Override
    public void onReqBusMessagesFailure(String message) {
    }

    @Override
    public void getMsgSuccess(List<MessageDetailEntity> list) {
        messageDetailView.onMsgSuccess(list);
        messageDetailView.onMsgEnd();
    }

    @Override
    public void getMsgFailure(int code, String error) {
        messageDetailView.onMsgFailure(code, error);
        messageDetailView.onMsgEnd();
    }

    @Override
    public int getPageSize() {
        return messageDetailView.getPageSize();
    }

    @Override
    public int getPageCount() {
        return messageDetailView.getPageCount();
    }

    @Override
    public String getUserNo() {
        return messageDetailView.getUserNo();
    }

    @Override
    public String getBusinessNo() {
        return messageDetailView.getBusinessNo();
    }

}
