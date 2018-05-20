package com.yunwei.easydear.function.mainFuncations.mycardlistFunction;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.yunwei.easydear.R;
import com.yunwei.easydear.base.BaseActivity;
import com.yunwei.easydear.base.DataApplication;
import com.yunwei.easydear.common.dialog.ToastUtil;
import com.yunwei.easydear.function.mainFuncations.mycardlistFunction.data.CardEntity;
import com.yunwei.easydear.view.PullToRefreshRecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by LJH on 2017/1/15.
 * 我的券
 */

public class MyCardActivity extends BaseActivity implements CardContract.CardView, PullToRefreshRecyclerView.PullToRefreshRecyclerViewListener {

    private final String TAG = this.getClass().getSimpleName();

    private MyCardAdapter adapter;

    private int defaultPageSize = 1;

    @BindView(R.id.mycard_recyclerView)
    PullToRefreshRecyclerView mRecyclerView;

    private CardPresenter cardPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_mycard);
        setToolbarTitle("消费券");
        ButterKnife.bind(this);

        cardPresenter = new CardPresenter(this);
        initRecyclerView();
    }

    /**
     * 初始化RecyclerView
     */
    private void initRecyclerView() {
        adapter = new MyCardAdapter(this);
        mRecyclerView.setRecyclerViewAdapter(adapter);
        mRecyclerView.setPullToRefreshListener(this);

        mRecyclerView.startUpRefresh();
    }

    @Override
    public void onDownRefresh() {
        defaultPageSize=1;
        cardPresenter.reqCardListAction();
    }

    @Override
    public void onPullRefresh() {
        defaultPageSize++;
        cardPresenter.reqCardListAction();
    }

    @Override
    public void onCardStart() {

    }

    @Override
    public void onCardEnd() {
        mRecyclerView.closeDownRefresh();
        mRecyclerView.onLoadMoreFinish();
    }

    @Override
    public void onCardSuccess(List<CardEntity> list) {
        adapter.addItems(list);
    }

    @Override
    public void onCardFailure(String error) {
        ToastUtil.showToast(this, error);
    }

    @Override
    public String getUserNo() {
        return DataApplication.getInstance().getUserInfoEntity().getUserNo();
    }

    @Override
    public int getPageSize() {
        return defaultPageSize;
    }

    @Override
    public int getPageCount() {
        return 20;
    }
}
