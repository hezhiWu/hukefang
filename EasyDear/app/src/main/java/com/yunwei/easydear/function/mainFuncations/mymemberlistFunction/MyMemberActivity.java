package com.yunwei.easydear.function.mainFuncations.mymemberlistFunction;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.yunwei.easydear.R;
import com.yunwei.easydear.base.BaseActivity;
import com.yunwei.easydear.base.DataApplication;
import com.yunwei.easydear.function.mainFuncations.mymemberlistFunction.data.BusinessEntity;
import com.yunwei.easydear.view.PullToRefreshRecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by LJH on 2017/1/15.
 * 会员商家
 */

public class MyMemberActivity extends BaseActivity implements PullToRefreshRecyclerView.PullToRefreshRecyclerViewListener, BusinessContract.BusinessView {

    private final String TAG = this.getClass().getSimpleName();

    @BindView(R.id.mymember_recyclerView)
    PullToRefreshRecyclerView mRecyclerView;

    private MyMemberAdapter adapter;

    private MemberBusinessPresenter memberBusinessPresenter;

    private int defaultPageSize = 1;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_mymember);
        setToolbarTitle("我的会员权益");
        ButterKnife.bind(this);
        memberBusinessPresenter = new MemberBusinessPresenter(this);

        initRecyclerView();
    }

    /**
     * 初始化RecyclerView
     */
    private void initRecyclerView() {
        adapter = new MyMemberAdapter(this);
        mRecyclerView.setPullToRefreshListener(this);
        mRecyclerView.setRecyclerViewAdapter(adapter);

        mRecyclerView.startUpRefresh();

    }

    @Override
    public void onDownRefresh() {
        defaultPageSize = 1;
        memberBusinessPresenter.reqBusinessListAction();
    }

    @Override
    public void onPullRefresh() {
        defaultPageSize++;
        memberBusinessPresenter.reqBusinessListAction();
    }

    @Override
    public void onBusinessStart() {

    }

    @Override
    public void onBusinessEnd() {
        mRecyclerView.closeDownRefresh();
        mRecyclerView.onLoadMoreFinish();
    }

    @Override
    public void onBusinessSuccess(List<BusinessEntity> list) {
        adapter.addItems(list);
    }

    @Override
    public void onBusinessFaliure(String error) {
        showToast(error);
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
