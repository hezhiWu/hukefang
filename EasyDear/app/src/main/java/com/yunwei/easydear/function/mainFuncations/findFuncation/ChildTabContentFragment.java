package com.yunwei.easydear.function.mainFuncations.findFuncation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yunwei.easydear.R;
import com.yunwei.easydear.base.BaseFragment;
import com.yunwei.easydear.base.BaseRecyclerViewAdapter;
import com.yunwei.easydear.common.dialog.ToastUtil;
import com.yunwei.easydear.function.mainFuncations.articleFunction.ArticleActivity;
import com.yunwei.easydear.function.mainFuncations.articleFunction.ArticleItemEntity;
import com.yunwei.easydear.function.mainFuncations.findFuncation.data.source.ChildTabRemoteRepo;
import com.yunwei.easydear.utils.ISkipActivityUtil;
import com.yunwei.easydear.view.PullToRefreshRecyclerView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Describe:选项卡内容模块
 * Author: hezhiWu
 * Date: 2016-12-25
 * Time: 12:0
 * Version:1.0
 */

public class ChildTabContentFragment extends BaseFragment implements ChildTabContact.ChileTabView, PullToRefreshRecyclerView.PullToRefreshRecyclerViewListener, BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener {

    private final String TAG = getClass().getSimpleName();

    private int defaultPageSize = 1;

    @BindView(R.id.tab_child_recyclerView)
    PullToRefreshRecyclerView mRecyclerView;
    @BindView((R.id.tab_child_empty_textView))
    TextView emptyTextView;

    private ChildTabContentAdapter adapter;
    private ChildTabPresenter mChildTabPresenter;

    private String type;
    private boolean isRefresh = true;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        type = getArguments().getString("type");
        mChildTabPresenter = new ChildTabPresenter(ChildTabRemoteRepo.getInstance(), this);
        adapter = new ChildTabContentAdapter(getActivity());
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab_child_layout, null);
        ButterKnife.bind(this, rootView);
        initRecyclerView();
        return rootView;
    }

    /**
     * 初始化RecyclerView
     */
    private void initRecyclerView() {
        mRecyclerView.setRecyclerViewAdapter(adapter);
        mRecyclerView.setMode(PullToRefreshRecyclerView.Mode.BOTH);
        mRecyclerView.setPullToRefreshListener(this);
        adapter.setOnItemClickListener(this);
        mRecyclerView.startUpRefresh();
    }


    @Override
    public void onDownRefresh() {
        isRefresh = true;
        defaultPageSize = 1;
        mChildTabPresenter.requestRecyclerArticles();
    }

    @Override
    public void onPullRefresh() {
        isRefresh = false;
        defaultPageSize++;
        mChildTabPresenter.requestRecyclerArticles();
    }

    @Override
    public void onArticleListSuccess(ArrayList<ArticleItemEntity> items) {
        if (isRefresh) {
            adapter.clearList();
            adapter.addItems(items);
        } else {
            adapter.addItems(items, adapter.getItemCount() - 1);
        }
    }

    @Override
    public void onArticleListStart() {

    }

    @Override
    public void onArticleListEnd() {
        mRecyclerView.closeDownRefresh();
        mRecyclerView.setLoading(false);
    }

    @Override
    public void onArticleListFailure(int code, String error) {
        if (code == 404) {
            if (adapter.getItemCount() <= 0) {
                mRecyclerView.setVisibility(View.GONE);
                emptyTextView.setVisibility(View.VISIBLE);
            } else {
                mRecyclerView.onLoadMoreFinish();
            }
        } else {
            ToastUtil.showToast(getActivity(), error);
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
    public String getKey() {
        return "";
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public String getProvince() {
        return "";
    }

    @Override
    public String getCity() {
        return "";
    }

    @Override
    public String getArea() {
        return "";
    }

    @Override
    public void onItemClick(View view, Object data, int position) {
        ISkipActivityUtil.startIntent(getActivity(), ArticleActivity.class);
    }
}
