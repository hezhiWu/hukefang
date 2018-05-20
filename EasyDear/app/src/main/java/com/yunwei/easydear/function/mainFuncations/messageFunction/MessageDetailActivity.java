package com.yunwei.easydear.function.mainFuncations.messageFunction;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.yunwei.easydear.R;
import com.yunwei.easydear.base.BaseActivity;
import com.yunwei.easydear.base.BaseRecyclerViewAdapter;
import com.yunwei.easydear.base.DataApplication;
import com.yunwei.easydear.common.dialog.ToastUtil;
import com.yunwei.easydear.function.mainFuncations.messageFunction.data.MessageDetailEntity;
import com.yunwei.easydear.function.mainFuncations.messageFunction.data.source.MessageRemoteRepo;
import com.yunwei.easydear.view.PullToRefreshRecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by LJH on 2017/1/16.
 * 消息详情列表
 */

public class MessageDetailActivity extends BaseActivity implements MessageContact.MessageDetailView, PullToRefreshRecyclerView.PullToRefreshRecyclerViewListener {

    private final String TAG = this.getClass().getSimpleName();

    private int defaultPageSize = 1;
    private boolean isRefresh = true;

    private String businessName = null;
    private String businessNo = null;

    @BindView(R.id.message_detail_recyclerView)
    PullToRefreshRecyclerView mRecyclerView;

    private MessageDetailAdapter adapter;

    private MessagePresenter messagePresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_message_detail);
        businessName = getIntent().getStringExtra("businessName");
        businessNo = getIntent().getStringExtra("businessNo");
        resetBusinessName();
        setToolbarTitle(businessName);
        ButterKnife.bind(this);
        initPresenter();
        initRecyclerView();
    }

    // businessNo 为空，为系统消息
    private void resetBusinessName() {
        if (businessNo == null) {
            businessName = "易兑科技";
        }
    }

    private void initRecyclerView() {
        adapter = new MessageDetailAdapter(this);
        mRecyclerView.setRecyclerViewAdapter(adapter);
        mRecyclerView.setPullToRefreshListener(this);
        mRecyclerView.startUpRefresh();
    }

    private void initPresenter() {
        messagePresenter = new MessagePresenter(MessageRemoteRepo.getInstance(), this);
    }

    @Override
    public void onDownRefresh() {
        defaultPageSize = 1;
        messagePresenter.reqMsgDetail();
    }

    @Override
    public void onPullRefresh() {
        defaultPageSize++;
        messagePresenter.reqMsgDetail();
    }

    @Override
    public void onMsgStart() {

    }

    @Override
    public void onMsgEnd() {
        mRecyclerView.closeDownRefresh();
        mRecyclerView.setLoading(false);
    }

    @Override
    public void onMsgSuccess(List<MessageDetailEntity> list) {
        if (isRefresh) {
            adapter.clearList();
            adapter.addItems(list);
        } else {
            adapter.addItems(list, adapter.getItemCount() - 1);
        }
    }

    @Override
    public void onMsgFailure(int code, String error) {
        if (code == 404) {
            if (adapter.getItemCount() <= 0) {
                mRecyclerView.setVisibility(View.GONE);
//                emptyTextView.setVisibility(View.VISIBLE);
            } else {
                mRecyclerView.onLoadMoreFinish();
            }
        } else {
            ToastUtil.showToast(this, error);
        }
    }

    @Override
    public int getPageSize() {
        return defaultPageSize;
    }

    @Override
    public int getPageCount() {
        return BaseRecyclerViewAdapter.DEFAULT_LOAD_SIZE;
    }

    @Override
    public String getUserNo() {
        return DataApplication.getInstance().getUserInfoEntity().getUserNo();
    }

    @Override
    public String getBusinessNo() {
        return businessNo;
    }
}
