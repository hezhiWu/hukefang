package com.yunwei.easydear.function.mainFuncations.messageFunction;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yunwei.easydear.R;
import com.yunwei.easydear.base.BaseFragment;
import com.yunwei.easydear.base.DataApplication;
import com.yunwei.easydear.function.mainFuncations.messageFunction.data.MessageItemEntity;
import com.yunwei.easydear.function.mainFuncations.messageFunction.data.source.MessageRemoteRepo;
import com.yunwei.easydear.view.PullToRefreshRecyclerView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.frame.function.mainFuncations.homeFuncation
 * @Description:消息模块
 * @date 2016/11/22 18:12
 */

public class MessageFragment extends BaseFragment implements MessageContact.MessageView {

    @BindView(R.id.msg_recyclerView)
    PullToRefreshRecyclerView mRecyclerView;

    private MessageContentAdapter adapter;

    private static MessageFragment fragment;

    private MessagePresenter mMessagePresenter;

    public static MessageFragment newInstance() {
        if (fragment == null) {
            fragment = new MessageFragment();
        }
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.main_fragment_record, null);
        ButterKnife.bind(this, rootView);
        initPresenter();
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        adapter = new MessageContentAdapter(getActivity());
        mRecyclerView.setRecyclerViewAdapter(adapter);
        requestTuiMessageList();
        requestBusinessMessageList();
    }

    private void initPresenter() {
        mMessagePresenter = new MessagePresenter(MessageRemoteRepo.getInstance(), this);
    }

    /**
     * 获取系统消息列表
     */
    private void requestTuiMessageList() {
        String useNo = DataApplication.getInstance().getUserInfoEntity().getUserNo();
        mMessagePresenter.requestTuiMessages(useNo);
    }

    /**
     * 获取商家消息列表
     */
    private void requestBusinessMessageList() {
        String useNo = DataApplication.getInstance().getUserInfoEntity().getUserNo();
        mMessagePresenter.requestBusMessages(useNo);
    }

    @Override
    public void setTuiMessages(ArrayList<MessageItemEntity> tuiMsgItems) {
        if (tuiMsgItems == null) {
            return;
        }
        adapter.addItems(tuiMsgItems, 0);
    }

    @Override
    public void setBusinessMessages(ArrayList<MessageItemEntity> businessMsgItems) {
        if (businessMsgItems == null) {
            return;
        }
        adapter.addItems(businessMsgItems);
    }

    private void initRecyclerView(ArrayList<MessageItemEntity> businessMsgItems) {
        ArrayList<MessageItemEntity> list = new ArrayList<MessageItemEntity>();
        list.addAll(businessMsgItems);
        for (int i = 0; i < 5; i++) {
            MessageItemEntity entity = new MessageItemEntity();
            if (i < 2) {
                entity.setCreateTime("上午8:16");
                entity.setContent("您有5张一点点奶茶消费券入账，立即查看>>");
                entity.setLogo("http://cdn.duitang.com/uploads/people/201207/24/20120724112116_yksB4.jpeg");
                list.add(entity);
            } else {
                entity.setCreateTime("下午4:30");
                entity.setContent("感谢您的评论，欢迎下次光临");
                entity.setLogo("http://img4.imgtn.bdimg.com/it/u=3081843971,3565251645&fm=23&gp=0.jpg");
                list.add(entity);
            }
        }

        adapter.addItems(list);
        mRecyclerView.setRecyclerViewAdapter(adapter);
    }

}
